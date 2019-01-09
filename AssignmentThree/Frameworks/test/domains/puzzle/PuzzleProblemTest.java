package domains.puzzle;

import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan Lenz
 */
public class PuzzleProblemTest {

    /**
     * Declare private instance fields here. For example:
     */
    private final Problem problem;
    private final Mover mover;
    private final List<String> moveNames;
    private final String slide1, slide2, slide3, slide4, // move names
            slide5, slide6, slide7, slide8;

    public PuzzleProblemTest() {
        problem = new PuzzleProblem();
        mover = problem.getMover();

        moveNames = mover.getMoveNames();
        slide1 = moveNames.get(0);
        slide2 = moveNames.get(1);
        slide3 = moveNames.get(2);
        slide4 = moveNames.get(3);
        slide5 = moveNames.get(4);
        slide6 = moveNames.get(5);
        slide7 = moveNames.get(6);
        slide8 = moveNames.get(7);
    }

    @Test
    public void testSolution() {

        /**
         * Display a welcome, the problem introduction, and the initial state.
         * Perform moves until the problem is solved, making appropriate
         * assertions along the way. Use FarmerProblemTest as a model.
         */
        display("Welcome to the " + problem.getName() + " problem");
        display(problem.getIntroduction());
        display(problem.getCurrentState());
        assertFalse(problem.success());

        tryMove(slide6);
        assertFalse(problem.success());

        tryMove(slide8);
        assertFalse(problem.success());

        tryMove(slide2);
        assertFalse(problem.success());

        tryMove(slide1);
        assertFalse(problem.success());

        tryMove(slide8);
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
