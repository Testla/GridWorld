import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;


public class KingCrab extends CrabCritter {
    /**
     * Processes the elements of <code>actors</code>. New actors may be added to
     * empty locations. Implemented to causes each actor that it processes
     * to move one location further away from the KingCrab. <br />
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
            Location actorLocation = a.getLocation(), destination;
            destination = actorLocation.getAdjacentLocation(
                           getLocation().getDirectionToward(actorLocation));
            if (getGrid().isValid(destination)) {
                a.moveTo(destination);
            } else {
                a.removeSelfFromGrid();
            }
        }
    }

}
