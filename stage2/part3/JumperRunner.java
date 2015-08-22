/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner {
    private JumperRunner() {
        // not called
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        
        // Specify a
//        Jumper jumper = new Jumper();
//        world.add(new Location(5, 5), jumper);
//        world.add(new Location(3, 5), new Rock());
        
        Test testSpecifyA = new Test(
                new JumperInfo[] {
                        new JumperInfo(new Location(5, 5), Location.NORTH, new Location(5, 5), Location.NORTHEAST)
                },
                null,
                new Location[] { new Location(3, 5) }
        );

        if (testSpecifyA.getResult()) {
            System.out.println("test passed");
        }
        
        // Specify b
//        Jumper jumper = new Jumper();
//        world.add(new Location(1, 5), jumper);
        
        // Specify c
//        Jumper jumper = new Jumper();
//        world.add(new Location(0, 5), jumper);
        
        // Specify d
//        Jumper jumperA = new Jumper(), jumperB = new Jumper(Color.RED);
//        world.add(new Location(3, 2), jumperA);
//        world.add(new Location(4, 2), new Rock());
//        world.add(new Location(5, 2), jumperB);
//
//        Jumper jumperC = new Jumper(), jumperD = new Jumper(Color.RED);
//        jumperC.setDirection(Location.SOUTH);
//        jumperD.setDirection(Location.SOUTH);
//        world.add(new Location(5, 5), jumperC);
//        world.add(new Location(4, 5), new Rock());
//        world.add(new Location(3, 5), jumperD);
//
//        Jumper jumperE = new Jumper(), jumperF = new Jumper(Color.RED);
//        world.add(new Location(3, 8), jumperE);
//        world.add(new Location(5, 8), jumperF);

        // Specify e
//        Jumper jumperA = new Jumper(), jumperB = new Jumper(Color.RED);
//        world.add(new Location(3, 2), jumperA);
//        world.add(new Location(4, 2), jumperB);
//
//        Jumper jumperC = new Jumper(), jumperD = new Jumper(Color.RED);
//        jumperC.setDirection(Location.SOUTH);
//        jumperD.setDirection(Location.SOUTH);
//        world.add(new Location(4, 5), jumperC);
//        world.add(new Location(3, 5), jumperD);

        // two Actors in the front
//        Jumper jumperA = new Jumper();
//        world.add(new Location(5, 2), jumperA);
//        world.add(new Location(4, 2), new Flower());
//        world.add(new Location(3, 2), new Flower());
//        
//        Jumper jumperB = new Jumper();
//        world.add(new Location(5, 5), jumperB);
//        world.add(new Location(4, 5), new Rock());
//        world.add(new Location(3, 5), new Rock());
//        
//        Jumper jumperC = new Jumper();
//        world.add(new Location(5, 8), jumperC);
//        world.add(new Location(4, 8), new Rock());
//        world.add(new Location(3, 8), new Flower());
        
        // near edge
//        Jumper jumperA = new Jumper();
//        world.add(new Location(1, 3), jumperA);
//        
//        Jumper jumperB = new Jumper();
//        world.add(new Location(0, 6), jumperB);
        
        
        world.show();
    }
}

final class JumperInfo {
    Location beginLocation;
    int beginDirection;
    Location endLocation;
    int endDirection;
    public JumperInfo(
            Location initBeginLocation,
            int initBeginDirection,
            Location initEndLocation,
            int initEndDirection
    ) {
        beginLocation = initBeginLocation;
        beginDirection = initBeginDirection;
        endLocation = initEndLocation;
        endDirection = initEndDirection;
    }
}

final class FlowerInfo {
    Location location;
    boolean supposedExistence;
    public FlowerInfo(
            Location initLocation,
            boolean initSupposedExistence
    ) {
        location = initLocation;
        supposedExistence = initSupposedExistence;
    }
}

final class Test {
    private ActorWorld world;
    private boolean runned, result;
    
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
        Flower flower;
        boolean supposedExistence;
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
     * Test if the jumper meets the requirement of given test data
     * 
     * @param jumperBeginLocations
     * @param jumperBeginDirections
     * @param jumperEndLocations
     * @param jumperEndDirections
     * @param flowerLocations          nullable
     * @param flowerSupposedExistences nullable
     * @param rockLocations            nullable
     * @throws Exception
     */
    public Test(
            JumperInfo[] jumperInfo,
            FlowerInfo[] flowerInfo,
            Location rockLocations[]
    ) {
        world = new ActorWorld();
        runned = false;
        
        // initialize actors
        jumperToTest = new JumperToTest[jumperInfo.length];
        for (int i = 0; i < jumperInfo.length; ++i) {
            jumperToTest[i] = new JumperToTest(jumperInfo[i]);
        }
        
        if (flowerToTest != null) {
            flowerToTest = new FlowerToTest[flowerInfo.length];
            for (int i = 0; i < flowerInfo.length; ++i) {
                flowerToTest[i] = new FlowerToTest(flowerInfo[i]);
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
            world.step();
            result = false;

            for (int i = 0; i < jumperToTest.length; ++i)
                if (jumperToTest[i].check() == false)
                    return false;
            if (flowerToTest != null) {
                for (int i = 0; i < flowerToTest.length; ++i)
                    if (flowerToTest[i].check() == false)
                        return false;
            }
            result = true;
        }
        return result;
    }
}
