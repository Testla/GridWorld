import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TwistCritter extends Critter {
    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: The state of all actors is unchanged.
     * 
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        return getGrid().getNeighbors(getLocation());
    }
    
    /**
     * Processes the elements of <code>actors</code>. New actors may be added to
     * empty locations. Implemented to Rotate them by 90 degrees. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * 
     * @param actors
     *            the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation(), newLocation;
        boolean flag0 = true, flag1 = true;
        ArrayList<Location> newLocations1 = new ArrayList<Location>();
        ArrayList<Location> newLocations0 = new ArrayList<Location>();
        ArrayList<Location> oldLocations1 = new ArrayList<Location>();
        ArrayList<Location> oldLocations0 = new ArrayList<Location>();
        ArrayList<Actor> actors0 = new ArrayList<Actor>();
        ArrayList<Actor> actors1 = new ArrayList<Actor>();
        for (Actor a : actors) {
            newLocation = new Location(
                    loc.getRow() + a.getLocation().getCol() - loc.getCol(),
                    loc.getCol() - a.getLocation().getRow() + loc.getRow()
            );
            if (a.getLocation().getCol() == loc.getCol()
                    || a.getLocation().getRow() == loc.getRow()) {
                newLocations1.add(newLocation);
                oldLocations1.add(a.getLocation());
                actors1.add(a);
                if (!gr.isValid(newLocation)) {
                    flag1 = false;
                }
            } else {
                newLocations0.add(newLocation);
                oldLocations0.add(a.getLocation());
                actors0.add(a);
                if (!gr.isValid(newLocation)) {
                    flag0 = false;
                }
            }
            a.removeSelfFromGrid();
        }
        
        if (flag1) {
            for (Location loc1 : newLocations1) {
                actors1.get(newLocations1.indexOf(loc1)).putSelfInGrid(gr, loc1);
            }
        } else {
            for (Location loc1 : oldLocations1) {
                actors1.get(oldLocations1.indexOf(loc1)).putSelfInGrid(gr, loc1);
            }
        }
        if (flag0) {
            for (Location loc1 : newLocations0) {
                actors0.get(newLocations0.indexOf(loc1)).putSelfInGrid(gr, loc1);
            }
        } else {
            for (Location loc1 : oldLocations0) {
                actors0.get(oldLocations0.indexOf(loc1)).putSelfInGrid(gr, loc1);
            }
        }
    }
}
