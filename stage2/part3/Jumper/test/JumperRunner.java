import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Before;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * 
 *
 */
public class JumperRunner
{
    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }
    
    /**
     * Test if the Jumper jumps to the right location.
     */
   
	@Test
    public void testJump()
    {
	 // jump
        TestHelper testJump = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(5, 5), Location.NORTH, new Location(3, 5), Location.NORTH)
                },
                null,
		null,
                null,
                1
        );
        assertTrue (testJump.getResult());
    }

    /**
     * Test if the Jumper avoids the rocks.
     */

    @Test
    public void testRock()
    {
	 TestHelper testRock = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(5, 5), Location.NORTH, new Location(5, 5), Location.NORTHEAST)
                },
                null,
		null,
                new Location[] { new Location(3, 5) },
                1
        );
        assertTrue (testRock.getResult());
    }


	/**
     * Test if the Jumper jumps the adjacent rocks.
     */

    @Test
    public void testRockAdj()
    {
        TestHelper testRockAdj = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(5, 5), Location.NORTH, new Location(3, 5), Location.NORTH)
                },
                null,
		null,
                new Location[] {new Location(4, 5)},
                1
        );
        assertTrue (testRockAdj.getResult());
    }

	/**
     * Test if the Jumper eats flowers.
     */

    @Test
    public void testFlower()
    {
        TestHelper testFlower = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(5, 5), Location.NORTH, new Location(3, 5), Location.NORTH)
                },
                new FlowerInfo[] {
			new FlowerInfo(new Location(3, 5), false)		
		},
		null,
                null,
                1
        );
        assertTrue (testFlower.getResult());	
    }

	/**
     * Test if the Jumper turns when the next step is out of the grid.
     */

     @Test
    public void testOutOfGrid()
    {
	//out of grid
	TestHelper testOutOfGrid = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(1, 3), Location.NORTHEAST, new Location(1, 3), Location.EAST)
                },
                null,
                null,
		null,
                1
        );
        assertTrue (testOutOfGrid.getResult());
    }

	/**
     * Test if the Jumper turns when facing the edge of the grid.
     */

     @Test
    public void testFaceEdge()
    {
	//face edge
	TestHelper testFaceEdge = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(2, 9), Location.EAST, new Location(2, 9), Location.SOUTH)
                },
                null,
                null,
		null,
                2
        );
        assertTrue (testFaceEdge.getResult());
    }
 

	/**
     * Test if the Jumper deals with bugs correctly.
     */

     @Test
    public void testBug()
    {
	//another actor -- Bug
	TestHelper testBug = new TestHelper(
                new JumperInfo[] {
			//Bug will act before Jumper, so Jumper will be able to go straight
			new JumperInfo(new Location(5, 2), Location.NORTH, new Location(3, 2), Location.NORTH),   
			//Jumper will act before Bug, so Jumper can't move to Bug's location. Jumper will turn 45 degree right
                        new JumperInfo(new Location(3, 5), Location.SOUTH, new Location(3, 5), Location.SOUTHWEST),
			// Bug is in front of the Jumper, but it moves first and arrived at the location which Jumper want to jump to, so Jumper will turn 45 degree right
                        new JumperInfo(new Location(5, 8), Location.NORTH, new Location(5, 8), Location.NORTHEAST)
                },
                null,
                new BugInfo[] {
			new BugInfo(new Location(3, 2), Location.NORTH),
                        new BugInfo(new Location(5, 5), Location.SOUTH),
                        new BugInfo(new Location(4, 8), Location.NORTH)
		},
		null,
                1
        );
        assertTrue (testBug.getResult());
    }

	/**
     * Test if the Jumper jumps the adjacent jumpers.
     */

    @Test
    public void testEncJumpers1()
    {
	TestHelper testEncJumper1 = new TestHelper(
                new JumperInfo[] {
                        new JumperInfo(new Location(7, 1), Location.EAST, new Location(7, 3), Location.EAST), 
			new JumperInfo(new Location(7, 2), Location.WEST, new Location(7, 0), Location.WEST)
                },
                null,
                null,
		null,
                1
        );
        assertTrue (testEncJumper1.getResult());
    }

	/**
     * Test if the Jumper deals with other jumpers correctly.
     */

    @Test
    public void testEncJumpers2()
    {
	//jumpers encounters(two cells away)
	TestHelper testEncJumper2 = new TestHelper(
                new JumperInfo[] {
			// One Jumper on top, another on bottom, both going up(north):The top one will move first so both 
                        new JumperInfo(new Location(5, 2), Location.NORTH, new Location(3, 2), Location.NORTH),
                        new JumperInfo(new Location(3, 2), Location.NORTH, new Location(1, 2), Location.NORTH),
			// One Jumper on top, another on bottom, both going down(south):The top one will move first but stopped so it turns but the other one goes straight
                        new JumperInfo(new Location(5, 5), Location.SOUTH, new Location(7, 5), Location.SOUTH),
                        new JumperInfo(new Location(3, 5), Location.SOUTH, new Location(3, 5), Location.SOUTHWEST),
			// Two Jumpers fight for one position, the one who acts first gets it, the other turns.
                        new JumperInfo(new Location(1, 8), Location.SOUTH, new Location(3, 8), Location.SOUTH),
                        new JumperInfo(new Location(5, 8), Location.NORTH, new Location(5, 8), Location.NORTHEAST)
                },
                null,
                null,
		null,
                1
        );
        assertTrue (testEncJumper2.getResult());
    }
}

