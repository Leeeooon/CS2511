package domains.farmer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class FarmerStateTest {

    public FarmerStateTest() {
        
        initial =     new FarmerState("West",   // Farmer
                                      "West",   // Wolf
                                      "West",   // Goat
                                      "West");  // Cabbage
        
        initialString = "   |  |   \n" +
                        " F |  |   \n" +
                        " W |  |   \n" +
                        " G |  |   \n" +
                        " C |  |   \n" +
                        "   |  |   ";
        
        initialCopy = new FarmerState("West", 
                                      "West", 
                                      "West", 
                                      "West");
        
        goal =        new FarmerState("East", 
                                      "East", 
                                      "East", 
                                      "East");
        
        goalString =    "   |  |   \n" +
                        "   |  | F \n" +
                        "   |  | W \n" +
                        "   |  | G \n" +
                        "   |  | C \n" +
                        "   |  |   ";
        
        intermediate =        
                      new FarmerState("East", 
                                      "West", 
                                      "East", 
                                      "West");
        
        intermediateString =    
                        "   |  |   \n" +
                        "   |  | F \n" +
                        " W |  |   \n" +
                        "   |  | G \n" +
                        " C |  |   \n" +
                        "   |  |   ";
    }

    /**
     * Test of equals method, of class FarmerState.
     */
    @Test
    public void testEquals() {
        
        assertTrue(initial.equals(initial));
        assertTrue(initial.equals(initialCopy));
        assertFalse(initial.equals(goal));
        assertFalse(initial.equals(intermediate));
        assertFalse(intermediate.equals(goal));
        
        Object nil = null;
        
        assertFalse(initial.equals(nil));
    }

    /**
     * Test of toString method, of class FarmerState.
     */
    @Test
    public void testToString() {
        
        assertTrue(initial.toString().equals(initialString));
        assertTrue(initial.toString().equals(initialCopy.toString()));
        assertTrue(goal.toString().equals(goalString));
        assertTrue(intermediate.toString().equals(intermediateString));
        assertFalse(initial.toString().equals(intermediate.toString()));
        assertFalse(initial.toString().equals(goal.toString()));
    }
    
    private final FarmerState initial;
    private final FarmerState initialCopy;
    private final FarmerState goal;
    private final FarmerState intermediate;
    
    private final String initialString;
    private final String intermediateString;
    private final String goalString;
    
}