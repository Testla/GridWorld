import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

public class RockHound extends Critter {
    /**
     * Processes the elements of <code>actors</code>. New actors may be added to
     * empty locations. Implemented to "eat" (i.e. remove) selected actors that
     * are rocks.
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * 
     * @param actors
     *            the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }
}
