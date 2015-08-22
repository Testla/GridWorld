import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter {
    private static final double DARKENING_FACTOR = 0.05;
    private int c;
    
    public BlusterCritter(int courage) {
        c = courage;
    }
    
    /**
     * Gets the actors for processing. Implemented to return
     * all of the neighbors within two steps of its current location.<br />
     * Postcondition: The state of all actors is unchanged.
     * 
     * @return a list of actors that this critter wishes to process.
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (int dr = 0; dr <= 2; ++dr) {
            for (int dc = 0; dc <= 2; ++dc) {
                if ((dr != 0 || dc != 0)) {
                    Location targetLocation = new Location(
                            getLocation().getRow() + dr,
                            getLocation().getCol() + dc
                    );
                    if (getGrid().isValid(targetLocation)) {
                        Actor targetActor = getGrid().get(targetLocation);
                        if (targetActor != null) {
                            actors.add(targetActor);
                        }
                    }
                }
            }
        }
        return actors;
    }
    
    /**
     * Processes the elements of <code>actors</code>. New actors may be added to
     * empty locations.
     * If there are fewer than c critters, the BlusterCritter's color gets brighter
     * (color values increase). If there are c or more critters,
     * the BlusterCritter's color darkens (color values decrease).
     * 
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * 
     * @param actors
     *            the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        Color color = getColor();
        if (n < c) {
            int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        } else {
            int red = color.getRed() + 8;
            int green = color.getGreen() + 8;
            int blue = color.getBlue() + 8;
            red = Math.min(red, 255);
            green = Math.min(green, 255);
            blue = Math.min(blue, 255);
            setColor(new Color(red, green, blue));
        }
    }
}
