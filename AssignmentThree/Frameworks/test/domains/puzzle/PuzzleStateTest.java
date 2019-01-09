package domains.puzzle;

import domains.puzzle.PuzzleState.Location;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests puzzle state representation.
 * @author tcolburn
 */
public class PuzzleStateTest {                                                                                                                                                                      
                                                                                                                                                                                                                  
    private final PuzzleState state1, state1Copy, state2;    
    
    public PuzzleStateTest() {
        state1 = new PuzzleState(
                new int[][]{new int[]{1, 2, 3}, 
                            new int[]{8, 0, 4}, 
                            new int[]{7, 6, 5}});
        
        state1Copy = new PuzzleState(
                new int[][]{new int[]{1, 2, 3}, 
                            new int[]{8, 0, 4}, 
                            new int[]{7, 6, 5}});
        
        state2 = new PuzzleState(
                new int[][]{new int[]{5, 2, 3},   // tiles 1 and 5 are swapped
                            new int[]{8, 0, 4}, 
                            new int[]{7, 6, 1}});
        
    }

    @Test
    public void testEquals() {
        assertTrue(state1 != state1Copy);
        assertTrue(state1.equals(state1Copy));
        assertFalse(state1.equals(state2));
    }

    @Test
    public void testGetLocation() {
        assertTrue(state1.getLocation(0).equals(new Location(1,1)));
        assertTrue(state1.getLocation(1).equals(new Location(0,0)));
        assertTrue(state1.getLocation(2).equals(new Location(0,1)));
        assertTrue(state1.getLocation(3).equals(new Location(0,2)));
        assertTrue(state1.getLocation(4).equals(new Location(1,2)));
        assertTrue(state1.getLocation(5).equals(new Location(2,2)));
        assertTrue(state1.getLocation(6).equals(new Location(2,1)));
        assertTrue(state1.getLocation(7).equals(new Location(2,0)));
        assertTrue(state1.getLocation(8).equals(new Location(1,0)));
    }

    @Test
    public void testSwapper() {
        Location upperLeft = new Location(0,0);
        Location lowerRight = new Location(2,2);
        PuzzleState newState = new PuzzleState(state1, upperLeft, lowerRight);
        assertTrue(newState.equals(state2));
    }

    @Test
    public void testToString() {
        assertTrue(state1.toString().equals(
                "+---+---+---+\n" +
                "| 1 | 2 | 3 |\n" +
                "+---+---+---+\n" +
                "| 8 |   | 4 |\n" +
                "+---+---+---+\n" +
                "| 7 | 6 | 5 |\n" +
                "+---+---+---+"));
    }
    
}
