package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	/* 
	 * the one on the top stores the visited neighbors of the current location
	 * note that the first element is the location it comes from(null for start)
	 * thus the bug has to finally return to the start to find out if the maze has no solution 
	 */
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	// west, north, east, south
	private int directionWeight[] = new int[4], directionWalkedCountSum = 0;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		ArrayList<Location> startList = new ArrayList<Location>();
		startList.add(null);
		crossLocation.add(startList);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
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
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
        final int directions[] = new int[] {
                Location.AHEAD,
                Location.LEFT,
                Location.RIGHT,
                Location.HALF_CIRCLE
        };
        ArrayList<Location> canGoLocation = new ArrayList<Location>();
        Location currentLocation = getLocation();
        for (int direction : directions) {
            Location destination = currentLocation.getAdjacentLocation(direction);
            if (gr.isValid(destination) && !crossLocation.peek().contains(destination)) {
                Actor a = gr.get(destination);
                if (a == null) {
                    canGoLocation.add(destination);
                } else if (a instanceof Rock && a.getColor().equals(Color.RED)) {
                    // reaches the end
                    isEnd = true;
                    return null;
                }
            }
        }
        // nowhere to go
        if (canGoLocation.isEmpty()) {
            // test if it is the start
            if (crossLocation.peek().get(0) != null) {
                canGoLocation.add(crossLocation.peek().get(0));
            } else {
                isEnd = true;
                return null;
            }
        }
        return canGoLocation;
	}

	/**
	 * Tests whether this bug can move to an adjacent unvisited location
	 * select next location to move and store in variable next
	 * maintain the stack
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
	    Random random = new Random();
	    ArrayList<Location> validLocations = getValid();
	    if (validLocations != null) {
	        
//	        boolean selected[] = new boolean[directionWeight.length];
//	        Arrays.fill(selected, false);
//	        int maxIndex, maxWeight;
//	        for (int i = 0, sum = 0, choice = random.nextInt(directionWalkedCountSum); i < directionWeight.length; ++i) {
//	            sum += directionWeight[i];
//	            if (sum )
//	        }
//            next = validLocations.get();
	        next = validLocations.get(random.nextInt(validLocations.size()));
//	        int directionWalkedCountIndex = (getLocation().getDirectionToward(next) + 90) / 90;
	        if (next.equals(crossLocation.peek().get(0))) {
//	            --directionWeight[directionWalkedCountIndex];
	            crossLocation.pop();
	        } else {
//                ++directionWeight[directionWalkedCountIndex];
	            crossLocation.peek().add(next);
	            ArrayList<Location> nextList = new ArrayList<Location>();
	            nextList.add(getLocation());
	            crossLocation.add(nextList);
	        }
	        return true;
	    } else {
	        return false;
	    }
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
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
