package framework.solution;

import framework.problem.Mover;
import framework.problem.Problem;
import framework.problem.State;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * This class provides support as the user attempts to solve a problem.
 * It tries moves indicated by the user, updates the current state as
 * necessary, keeps track of the move count, and checks whether the problem
 * has been solved.
 * @author tcolburn
 */
public class SolvingAssistant {

    /**
     * Creates a new solving assistant.
     * @param problem the problem being solved
     */
    public SolvingAssistant(Problem problem) {   
        this.problem = problem;
        mover = problem.getMover();
        problemSolved = new SimpleBooleanProperty(false);
        moveCount = 0;
    }
    
    /**
     * Tries a move on the current state of the problem, updating the
     * current state if required.
     * @param move the move to try, as a string
     */
    public void tryMove(String move) {
        moveLegal = true;
        State next = mover.doMove(move, problem.getCurrentState());
        if (next != null) {
            update(next);
        }
        else {
            moveLegal = false;
        }
    }

    /**
     * Getter for the problem this solving assistant is working on.
     * @return the problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Getter for the move count so far.
     * @return the move count
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Getter for the legality of the most recently tried move.
     * @return true if the most recently tried move is legal, false otherwise
     */
    public boolean isMoveLegal() {
        return moveLegal;
    }
    
    /**
     * Resets the problem to its initial state.
     * Also resets the move count and problem solved status.
     */
    public void reset() {
        moveCount = 0;
        problem.setCurrentState(problem.getInitialState());
        setProblemSolved(false);
    }
    
    /**
     * Updates the current state of the problem to the given state.
     * Also increments the move count and checks the problem solved status.
     * @param s 
     */
    public void update(State s) {
        moveCount++;                    
        problem.setCurrentState(s);
        if (problem.success()) {
            setProblemSolved(true);
        }
    }
    
    /**
     * A JavaFX property indicating whether the problem has been solved.
     */
    private final BooleanProperty problemSolved;
    
    public BooleanProperty problemSolvedProperty() {
        return problemSolved;
    }
    
    public boolean isProblemSolved() {
        return problemSolved.get();
    }
 
    public void setProblemSolved(boolean value) {
        problemSolved.set(value);
    }
    
    private int moveCount;
    
    private final Problem problem;
    private final Mover mover;
    private boolean moveLegal;
}
