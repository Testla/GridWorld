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
import java.util.Arrays;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug {
    private int steps;
    private int pattern[];

    /**
     * Constructs a box bug that turns given times determined by pattern[] at each step
     * 
     * @param initPattern
     */
    public DancingBug(int initPattern[]) {
        steps = 0;
        pattern = Arrays.copyOf(initPattern, initPattern.length);
    }

    /**
     * Moves to the next location.
     */
    public void act() {
        for (int i = 0; i < pattern[steps]; ++i) {
            turn();
        }
        if (canMove()) {
            move();
        }
        steps++;
        steps %= pattern.length;
    }
}
