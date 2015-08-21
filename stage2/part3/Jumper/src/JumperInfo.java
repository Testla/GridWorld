import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public final class JumperInfo {
    public Location beginLocation;
    public int beginDirection;
    public Location endLocation;
    public int endDirection;
    public JumperInfo(
            Location initBeginLocation,
            int initBeginDirection,
            Location initEndLocation,
            int initEndDirection
    ) {
        beginLocation = initBeginLocation;
        beginDirection = initBeginDirection;
        endLocation = initEndLocation;
        endDirection = initEndDirection;
    }
}

