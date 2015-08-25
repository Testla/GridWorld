package info.gridworld.maze;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
final class MazeBugRunner {
    private MazeBugRunner() {
        // not called
    }
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(0,0), new MazeBug());
        world.addOccupantClass("info.gridworld.maze.AdvancedMazeBug");
        world.addOccupantClass("info.gridworld.maze.OptimalMazeBug");
        world.add(new Location(1,1),new Rock());
        world.show();
    }
}
