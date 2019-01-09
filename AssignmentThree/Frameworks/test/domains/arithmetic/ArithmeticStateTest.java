package domains.arithmetic;

import framework.problem.State;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests states of the Arithmetic problem.
 * @author tcolburn
 */
public class ArithmeticStateTest {
    
    private final State init, initCopy, goal, other;
    
    public ArithmeticStateTest() {
        init = new ArithmeticState(0);
        initCopy = new ArithmeticState(0);
        goal = new ArithmeticState(17);
        other = new ArithmeticState(-13);
    }

    @Test
    public void testEquals() {
        System.out.println("Testing equals");
        assertTrue(init == init);
        assertFalse(init == initCopy);
        assertTrue(init.equals(initCopy));
        assertFalse(init.equals(goal));
        assertFalse(other.equals(goal));
    }

    @Test
    public void testToString() {
        System.out.println("Testing toString");
        assertTrue(init.toString().equals("The value is: 0"));
        assertTrue(initCopy.toString().equals("The value is: 0"));
        assertTrue(goal.toString().equals("The value is: 17"));
        assertTrue(other.toString().equals("The value is: -13"));
    }
    
}
