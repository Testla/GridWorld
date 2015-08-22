import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public final class FlowerInfo {
    public Location location;
    public boolean supposedExistence;
    public FlowerInfo(
            Location initLocation,
            boolean initSupposedExistence
    ) {
        location = initLocation;
        supposedExistence = initSupposedExistence;
    }
}

