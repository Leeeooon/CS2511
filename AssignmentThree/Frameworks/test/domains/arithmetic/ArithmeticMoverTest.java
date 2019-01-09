package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests moves in the Arithmetic problem.
 * @author tcolburn
 */
public class ArithmeticMoverTest {
    
    private final State state;
    private final Mover mover;
    
    public ArithmeticMoverTest() {
        state = new ArithmeticState(6);
        mover = new ArithmeticMover();
    }

    @Test
    public void testMoves() {
        State next = mover.doMove("Add 3", state);
        assertTrue(next.equals(new ArithmeticState(9)));
        
        next = mover.doMove("Subtract 5", next);
        assertTrue(next.equals(new ArithmeticState(4)));
        
        next = mover.doMove("Multiply by 2", next);
        assertTrue(next.equals(new ArithmeticState(8)));
        
        next = mover.doMove("Divide by 2", next);
        assertTrue(next.equals(new ArithmeticState(4)));
    }
    
}
