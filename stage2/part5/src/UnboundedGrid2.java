/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number
 * of rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    // the array storing the grid elements
    private Object[][] occupantArray;
    private int size;
    
    private static final int INIT_SIZE = 16;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2() {
        size = INIT_SIZE;
        occupantArray = new Object[size][size];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc) {
        return (loc.getRow() >= 0 && loc.getCol() >= 0);
    }
    
    private boolean outOfBound(Location loc) {
        return loc.getRow() >= size || loc.getCol() >= size;
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (outOfBound(loc)) {
            return null;
        } else {
            return (E) occupantArray[loc.getRow()][loc.getCol()]; 
            // unavoidable warning
        }
    }

    public E put(Location loc, E obj) {
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (outOfBound(loc)) {
            while (outOfBound(loc)) {
                size *= 2;
            }
            Object[][] newArray = new Object[size][size];
            for (int i = 0; i < occupantArray.length; ++i) {
                for (int j = 0; j < occupantArray.length; ++j) {
                    newArray[i][j] = occupantArray[i][j];
                }
            }
            occupantArray = newArray;
        }
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // Remove the object from the grid.
        E r = get(loc);
        if (!outOfBound(loc)) {
            occupantArray[loc.getRow()][loc.getCol()] = null;
        }
        return r;
    }
}
