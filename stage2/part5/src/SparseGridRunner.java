import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;

/**
 * This class runs a world with additional grid choices.
 */
final class SparseGridRunner {
    private SparseGridRunner() {
        // not called
    }
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("UnboundedGrid2");
        for (int i = 0; i < 100; ++i) { 
            world.add(new Critter());
            world.add(new Rock());
            world.add(new Bug());
        }
        world.show();
    }
}
