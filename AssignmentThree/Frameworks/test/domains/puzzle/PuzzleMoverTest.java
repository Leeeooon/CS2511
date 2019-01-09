package domains.puzzle;

import domains.puzzle.PuzzleState.Location;
import framework.problem.Mover;
import framework.problem.State;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan Lenz
 */
public class PuzzleMoverTest {

    /**
     * Declare private instance fields here. For example:
     */
    private final State start;
    private final Mover mover;
    private final String slide1, slide2, slide3, slide4, // move names
            slide5, slide6, slide7, slide8;
    private final List<String> moveNames;

    public PuzzleMoverTest() {

        /**
         * Initialize instance fields here
         */
        start = new PuzzleState(
                new int[][]{new int[]{5, 2, 3},
                new int[]{8, 0, 4},
                new int[]{7, 6, 1}});

        mover = new PuzzleMover();

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

    /**
     * Test all moves in the methods below by replacing the calls to fail.
     */
    @Test
    public void testSlide1() {
        doMoveIllegal(slide1);
    }

    @Test
    public void testSlide2() {
        doMoveLegal(slide2, 2);
    }

    @Test
    public void testSlide3() {
        doMoveIllegal(slide3);
    }

    @Test
    public void testSlide4() {
        doMoveLegal(slide4, 4);
    }

    @Test
    public void testSlide5() {
        doMoveIllegal(slide5);
    }

    @Test
    public void testSlide6() {
        doMoveLegal(slide6, 6);
    }

    @Test
    public void testSlide7() {
        doMoveIllegal(slide7);
    }

    @Test
    public void testSlide8() {
        doMoveLegal(slide8, 8);
    }

    private void doMoveIllegal(String moveName) {
        State doMove = mover.doMove(moveName, start);
        assertTrue(doMove == null);
    }

    private void doMoveLegal(String moveName, int tileMoving) {
        PuzzleState doMove = (PuzzleState) mover.doMove(moveName, start);
        PuzzleState ps = (PuzzleState) start;
        assertTrue(doMove != null);
        assertTrue(!doMove.equals(start));
        assertTrue(ps.getLocation(tileMoving) != doMove.getLocation(tileMoving));

        Location tileFirst = ps.getLocation(tileMoving);
        Location blankFirst = ps.getLocation(0);
        Location tileSecond = doMove.getLocation(tileMoving);
        Location blankSecond = doMove.getLocation(0);

        assertTrue(tileFirst.equals(blankSecond) && tileSecond.equals(blankFirst));
    }

}
