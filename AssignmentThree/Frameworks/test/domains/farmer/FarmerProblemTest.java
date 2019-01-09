package domains.farmer;

import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class FarmerProblemTest {
    
    public FarmerProblemTest() {
        problem = new FarmerProblem();
        mover = problem.getMover();
        
        moveNames = mover.getMoveNames();
        goesAlone = moveNames.get(0);
        takesWolf = moveNames.get(1);
        takesGoat = moveNames.get(2);
        takesCabbage = moveNames.get(3);
    }

    @Test
    public void testSolution() {
        display("Welcome to the " + problem.getName() + " problem");
        display(problem.getIntroduction());
        display(problem.getCurrentState());
        assertFalse(problem.success());
        
        tryMove(takesGoat);
        assertFalse(problem.success());
        
        tryMove(goesAlone);
        assertFalse(problem.success());
        
        tryMove(takesCabbage);
        assertFalse(problem.success());
        
        tryMove(takesGoat);
        assertFalse(problem.success());
        
        tryMove(takesWolf);
        assertFalse(problem.success());
        
        tryMove(goesAlone);
        assertFalse(problem.success());
        
        tryMove(takesGoat);
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
    
    private final Problem problem;
    private final Mover mover;
    private final List<String> moveNames;
    private final String goesAlone, takesWolf, takesGoat, takesCabbage;
    
}