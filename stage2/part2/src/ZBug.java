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

import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug {
    private int steps;
    private int sideLength;
    private int state;

    /**
     * Constructs a box bug that traces a path in the shape "Z"
     * 
     * @param length
     *            the side length
     */
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        state = 0;
        // force the bug to face east
        turn();
        turn();
    }

    /**
     * Moves to the next location.
     */
    public void act() {
        if (state < 3) {
            if (steps == sideLength) {
                if (state == 0) {
                    for (int i = 0; i < 3; ++i) {
                        turn();
                    }
                } else if (state == 1) {
                    for (int i = 0; i < 5; ++i) {
                        turn();
                    }
                }
                ++state;
                steps = 0;
            }
            // state may have changed, check again
            if (state < 3 && canMove()){
                move();
                steps++;
            }
        }
    }
}
