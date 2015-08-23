import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

final class OccupantInCol {
    public Object getOccupant() {
        return occupant;
    }
    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }
    public int getCol() {
        return col;
    }
    private Object occupant;
    private int col;
    public OccupantInCol(Object occupant, int col) {
        this.occupant = occupant;
        this.col = col;
    }
}

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of rows
 * and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {

    private int numCols;
    // the array storing the grid elements
    private ArrayList<LinkedList<OccupantInCol>> occupantArray;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows
     *            number of rows in BoundedGrid
     * @param cols
     *            number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        occupantArray = new ArrayList<LinkedList<OccupantInCol>>();
        for (int i = 0; i < rows; ++i) {
            occupantArray.add(new LinkedList<OccupantInCol>());
        }
        numCols = cols;
    }

    public int getNumRows() {
        return occupantArray.size();
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean isValid(Location loc) {
        return (0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols());
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
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
        for (OccupantInCol occupantInCol : occupantArray.get(loc.getRow())) {
            if (occupantInCol.getCol() == loc.getCol()) {
                // unavoidable warning
                return (E) occupantInCol.getOccupant();
            }
        }
        return null;
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        if (oldOccupant == null) {
            occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        } else {
            for (OccupantInCol occupantInCol : occupantArray.get(loc.getRow())) {
                if (occupantInCol.getCol() == loc.getCol()) {
                    // unavoidable warning
                    occupantInCol.setOccupant(obj);
                    break;
                }
            }
        }
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        // Remove the object from the grid.
        E r = get(loc);
        for (OccupantInCol occupantInCol : occupantArray.get(loc.getRow())) {
            if (occupantInCol.getCol() == loc.getCol()) {
                occupantArray.get(loc.getRow()).remove(occupantInCol);
                // unavoidable warning
                return (E) r;
            }
        }
        // actually not reachable, but for default return value
        return null;
    }
}
