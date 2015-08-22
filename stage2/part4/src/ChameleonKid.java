import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * @author Administrator
 * 
 */
public class ChameleonKid extends ChameleonCritter {
    /**
     * Gets the actors for processing. Implemented to return the actors that are
     * immediately in front or behind. Postcondition: The state of all actors is
     * unchanged.
     * 
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) != null) {
                actors.add(getGrid().get(loc));
            }
        }
        return actors;
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * 
     * @param directions
     *            - an array of directions (which are relative to the current
     *            direction)
     * @return a set of valid locations that are neighbors of the current
     *         location in the given directions
     */

    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();

        for (int d : directions) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }
}
