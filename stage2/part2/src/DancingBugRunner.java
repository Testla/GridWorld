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
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner {
    private DancingBugRunner() {
        // not called
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        DancingBug alice = new DancingBug(new int[] { 1 });
        world.add(new Location(12, 12), alice);
        DancingBug bob = new DancingBug(new int[] { 1, 0, 2, 3, 0, 2, 0 });
        bob.setColor(Color.BLUE);
        world.add(new Location(10, 10), bob);
        DancingBug cat = new DancingBug(new int[] { 2, 9, 0, 5, 5, 3, 0, 8, 4 });
        cat.setColor(Color.WHITE);
        world.add(new Location(7, 7), cat);
        world.show();
    }
}