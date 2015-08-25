package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class OptimalMazeBug extends Bug {
    private Location next;
    private boolean isEnd = false;
    private Integer stepCount = 0;
    // final message has been shown
    private boolean hasShown = false;
    private Stack<Location> path; 

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public OptimalMazeBug() {
        setColor(Color.RED);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
            // to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        } 
    }

    /**
     * Tests whether this bug can move to an adjacent unvisited location
     * select next location to move and store in variable next
     * maintain the stack
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        final int directions[] = new int[] {
                Location.AHEAD,
                Location.LEFT,
                Location.RIGHT,
                Location.HALF_CIRCLE
        };
        Grid<Actor> gr = getGrid();
        if (path == null && gr != null) {
            boolean done = false;
            path = new Stack<Location>();
            // (K, V) -> (from, to)
            HashMap<Location, Location> visitedLocations = new HashMap<Location, Location>();
            Queue<Location> q = new LinkedList<Location>();
            q.add(getLocation());
            visitedLocations.put(getLocation(), null);
            while (!q.isEmpty() && !done) {
                Location current = q.poll();
                for (int direction : directions) {
                    Location destination = current.getAdjacentLocation(direction);
                    if (gr.isValid(destination)) {
                        Actor a = gr.get(destination);
                        // reaches the end
                        if (a instanceof Rock && a.getColor().equals(Color.RED)) {
                            while (current != null) {
                                path.add(current);
                                current = visitedLocations.get(current);
                            }
                            path.pop();
                            done = true;
                            break;
                        } else if (!(a instanceof Rock) && !visitedLocations.containsKey(destination)) {
                            q.add(destination);
                            visitedLocations.put(destination, current);
                        }
                    }
                }
            }
        }
        if (!path.isEmpty()) {
            next = path.pop();
            return true;
        } else {
            isEnd = true;
            return false;
        }
    }
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
