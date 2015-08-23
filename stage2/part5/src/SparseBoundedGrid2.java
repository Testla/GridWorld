import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of rows
 * and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
/**
 * @author Administrator
 *
 * @param <E>
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
    private int numRows, numCols;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid2(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        numRows = rows;
        numCols = cols;
        occupantMap = new HashMap<Location, E>();
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public int getNumCols() {
        return numCols;
    }

    @Override
    public boolean isValid(Location loc) {
        return (0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols());
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    @Override
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        return occupantMap.get(loc);
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        // Add the object to the grid.
        return occupantMap.put(loc, obj);
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        // Remove the object from the grid.
        return occupantMap.remove(loc);
    }
}
