package framework.problem;

/**
 * This class represents problems in a problem solving domain.
 * Getters and setters are provided for the problem name and
 * introduction, the initial, current, and final States of the
 * problem, and the problem's associated Mover object.
 * This class can be subclassed, especially if the success method
 * needs to be overridden.
 * @author tcolburn
 */
public class Problem {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public State getFinalState() {
        return finalState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }

    public Mover getMover() {
        return mover;
    }

    public void setMover(Mover mover) {
        this.mover = mover;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
    
    /**
     * Checks if the current state solves the problem.
     * This method may need to be overridden if the domain does not
     * have a unique final state.
     * @return whether the current state solves the problem
     */
    public boolean success() {
        return getCurrentState().equals(getFinalState());
    }
    
    private String name;
    private String introduction;
    private State initialState;
    private State currentState;
    private State finalState;
    private Mover mover;
}
