package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Arithmetic problem by creating the problem and solving it.
 * @author tcolburn
 */
public class ArithmeticProblemTest {
    
    private final Problem problem;
    private final Mover mover;
    
    public ArithmeticProblemTest() {
        problem = new ArithmeticProblem();
        mover = problem.getMover();
    }

    @Test
    public void testIntro() {
        assertTrue(problem.getIntroduction().equals(
             "The starting value is zero. " +
             "Your options are to add 3, subtract 5, " +
             "divide by 2, or multiply by 2. " +
             "Find a sequence of operations that results in the value 17."));
    }

    @Test
    public void testInit() {
        assertTrue(problem.getInitialState().equals(new ArithmeticState(0)));
        assertTrue(problem.getCurrentState().equals(new ArithmeticState(0)));
        assertTrue(problem.getFinalState().equals(new ArithmeticState(17)));
    }

    @Test
    public void testSolve() {
        assertFalse(problem.success());
        
        tryMove("Add 3");
        assertFalse(problem.success());
        
        tryMove("Add 3");
        assertFalse(problem.success());
        
        tryMove("Multiply by 2");
        assertFalse(problem.success());
        
        tryMove("Subtract 5");
        assertFalse(problem.success());
        
        tryMove("Multiply by 2");
        assertFalse(problem.success());
        
        tryMove("Multiply by 2");
        assertFalse(problem.success());
        
        tryMove("Add 3");
        assertFalse(problem.success());
        
        tryMove("Add 3");
        assertFalse(problem.success());
        
        tryMove("Divide by 2");
        assertTrue(problem.success());
    }
    
    private void tryMove(String move) {
        State next = mover.doMove(move, problem.getCurrentState());
        assertTrue(next != null);
        problem.setCurrentState(next);
        display(problem.getCurrentState());
    }
    
    private void display(Object o) {
        System.out.println(o + "\n");
    }
    
}