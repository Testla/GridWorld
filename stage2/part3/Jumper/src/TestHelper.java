import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public final class TestHelper {
    private ActorWorld world;
    private boolean runned, result;
    private int steps;
    
    private class JumperToTest {
        Jumper jumper;
        Location endLocation;
        int endDirection;
        public JumperToTest(JumperInfo jumperInfo) {
            jumper = new Jumper();
            jumper.setDirection(jumperInfo.beginDirection);
            world.add(jumperInfo.beginLocation, jumper);
            endLocation = jumperInfo.endLocation;
            endDirection = jumperInfo.endDirection;
        }
        boolean check() {
            return (jumper.getLocation().equals(endLocation)
                    && jumper.getDirection() == endDirection);
        }
    }
    
    private class FlowerToTest {
        public Flower flower;
        public boolean supposedExistence;
        public FlowerToTest(FlowerInfo flowerInfo) {
            flower = new Flower();
            world.add(flowerInfo.location, flower);
            supposedExistence = flowerInfo.supposedExistence;
        }
        boolean check() {
            return ((flower.getGrid() != null) == supposedExistence);
        }
    }

    private JumperToTest jumperToTest[];
    private FlowerToTest flowerToTest[];

    /**
     * Test if the world begining with the begin state
     * satisfies given end state in given steps
     * @param jumperInfo
     * @param flowerInfo    null means no flower
     * @param rockLocations null means no rock
     * @param steps         steps from begin to end
     */
    public TestHelper(
            JumperInfo[] jumperInfo,
            FlowerInfo[] flowerInfo,
            BugInfo[] bugInfo,
            Location rockLocations[],
            int initSteps
    ) {
        world = new ActorWorld();
        runned = false;
        steps = initSteps;
        
        // initialize actors
        jumperToTest = new JumperToTest[jumperInfo.length];
        for (int i = 0; i < jumperInfo.length; ++i) {
            jumperToTest[i] = new JumperToTest(jumperInfo[i]);
        }
        
        if (flowerInfo != null) {
            flowerToTest = new FlowerToTest[flowerInfo.length];
            for (int i = 0; i < flowerInfo.length; ++i) {
                flowerToTest[i] = new FlowerToTest(flowerInfo[i]);
            }
        }
        
        if (bugInfo != null) {
            for (int i = 0; i < bugInfo.length; ++i) {
                Bug newBug = new Bug();
                newBug.setDirection(bugInfo[i].beginDirection);
                world.add(bugInfo[i].beginLocation, newBug);
            }
        }
        
        if (rockLocations != null) {
            for (int i = 0; i < rockLocations.length; ++i)
                world.add(rockLocations[i], new Rock());
        }
    }
    
    public boolean getResult() {
        if (!runned) {
            runned = true;
            for (int i = 0; i < steps; ++i)
                world.step();
            result = false;

            for (int i = 0; i < jumperToTest.length; ++i)
                if (!jumperToTest[i].check())
                    return false;
            if (flowerToTest != null) {
                for (int i = 0; i < flowerToTest.length; ++i)
                    if (!flowerToTest[i].check())
                        return false;
            }

            result = true;
        }
        return result;
    }
}
