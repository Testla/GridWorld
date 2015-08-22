import info.gridworld.grid.Location;

import java.util.ArrayList;


public class QuickCrab extends CrabCritter {
    /**
     * @return list of empty locations that are two spaces to its right or left,
     * if that location and the intervening location are both empty.
     * If such location doesn't exitst, return locations that are
     * immediately to the right and to the left
     */
    @Override
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> near = new ArrayList<Location>()
                            , far = new ArrayList<Location>();
        int[] dirs = { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                near.add(loc);
            }
        }
        for (Location loc : near) {
            for (int d : dirs) {
                Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
                if (getGrid().isValid(neighborLoc)
                        && getGrid().get(neighborLoc) == null) {
                    far.add(neighborLoc);
                }
            }
        }
        if (!far.isEmpty()) {
            return far;
        } else {
            return near;
        }
    }

}
