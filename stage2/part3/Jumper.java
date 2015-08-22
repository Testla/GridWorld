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

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>Jumper</code> that always jumps if possible and turns when
 * encountering barrier <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor {
    /**
     * Constructs a green jumper.
     */
    public Jumper() {
        setColor(Color.GREEN);
    }
    
    /**
     * Constructs a jumper of a given color.
     * 
     * @param jumperColorColor
     *            the color for this jumperColor
     */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection())
                            .getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }
    
    /**
     * Tests whether this jumper can jump forward into a location that is empty
     * 
     * @return
     */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection())
                            .getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null || neighbor instanceof Flower);
        // ok to move into empty location
        // not ok to move onto any actor
    }

    /**
     * Moves to the next location.
     */
    public void act() {
        if (canJump()) {
            jump();
        } else {
            turn();
        }
    }
}