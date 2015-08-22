import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public final class BugInfo {
    public Location beginLocation;
    public int beginDirection;
    public BugInfo(
            Location initBeginLocation,
            int initBeginDirection
    ) {
        beginLocation = initBeginLocation;
        beginDirection = initBeginDirection;
    }
}
