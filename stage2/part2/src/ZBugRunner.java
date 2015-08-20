/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ZBugRunner {
    private ZBugRunner() {
        // not called
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid(19, 19));
        Bug bugs[] = new Bug[] {
            addZBug(world, 3, Color.BLUE, new Location(0, 0), new Location(-1, -1)),
            addZBug(world, 3, Color.GREEN, new Location(5, 0), new Location(5, 2)),
            addZBug(world, 3, Color.YELLOW, new Location(10, 0), new Location(10, 3)),
            addZBug(world, 3, Color.ORANGE, new Location(15, 0), new Location(18, 0)),
            addZBug(world, 3, Color.BLACK, new Location(15, 5), new Location(18, 8)),
            addZBug(world, 3, Color.CYAN, new Location(16, 10), new Location(-1, -1)),
            addZBug(world, 3, Color.MAGENTA, new Location(15, 16), new Location(-1, -1)),
            addZBug(world, 12, Color.GRAY, new Location(0, 5), new Location(-1, -1))
        };
        world.show();
    }
    /**
     * adds a bug to the world
     * @param color         color of the bug
     * @param bugLocation
     * @param rockLocation  (-1, -1) means do not add
     */
    private static ZBug addZBug(
        ActorWorld world,
        int sideLength,
        Color color,
        Location bugLocation,
        Location rockLocation
    ) {
        ZBug newBug = new ZBug(sideLength);
        newBug.setColor(color);
        world.add(bugLocation, newBug);
        if (!rockLocation.equals(new Location(-1, -1))) {
            world.add(rockLocation, new Rock());
        }
        return newBug;
    }
}