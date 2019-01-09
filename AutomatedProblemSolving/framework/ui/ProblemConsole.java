package framework.ui;

import console.Console;
import framework.problem.Problem;
import framework.solution.SolvingAssistant;
import java.util.List;

/**
 * This class represents a problem solving console for any appropriate 
 * problem domain.
 * It extends the Console class and overrides its handleInput method to
 * get move options from the user, check move legality, and update the
 * display.
 * The display is entirely text-based, using strings.
 * @author tcolburn
 */
public class ProblemConsole extends Console {

    /**
     * Creates a problem console with a given width and height.
     * @param problem the problem
     * @param width the width of the display area
     * @param height the height of the display area
     */
    public ProblemConsole(Problem problem, double width, double height) {
        super(problem.getName(), width, height);
        solver = new SolvingAssistant(problem);
        this.problem = problem;
        preamble = makePreamble();
        current = makeCurrent();
        options = makeOptions();
        clearMessage();
        super.updateDisplay(preamble + SKIP_LINE + 
                            current + SKIP_LINE +
                            options + SKIP_LINE +
                            message);
    }
    
    /**
     * Creates a problem solving console with a default width and height.
     * @param problem the problem
     */
    public ProblemConsole(Problem problem) {
        this(problem, 600, 600);
    }

    /**
     * Getter for the preamble, that is, the welcome string and introduction
     * string, for the problem
     * @return the preamble
     */
    public String getPreamble() {
        return preamble;
    }

    /**
     * Getter for the string representation of the current state area of the problem.
     * This includes an identifying label, the state depiction, and the current
     * move count
     * @return the current state area
     */
    public String getCurrent() {
        return makeCurrent();
    }

    /**
     * Getter for the options string.
     * This includes a numbered list of the move names for the problem,
     * as well as a prompt for the move.
     * @return the options string
     */
    public String getOptions() {
        return options;
    }

    /**
     * Getter for the message string for the problem.
     * Messages can include error indications and congratulatory messages.
     * @return the message string
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Getter for the entire string making up the display in this problem
     * console.
     * @return the entire string displayed in the console
     */
    public String getAll() {
        return getPreamble() + SKIP_LINE + 
               getCurrent() + SKIP_LINE +
               getOptions() + SKIP_LINE +
               getMessage();
    }
    
    /**
     * Handles the console's input from the user.
     * Checks for the legality of the input and updates the display where
     * appropriate.
     * Much of the response to the input is handled by a solving assistant.
     * @param input the move input from the user
     */
    @Override
    public void handleInput(String input) {
        clearMessage();
        int option = -1;
        try {
            option = Integer.parseInt(input);
        }
        catch(NumberFormatException ex) {
            message = NOT_INTEGER;
            updateDisplay(getAll());
            return;
        }
        if (option == 0) {
            solver.reset();
            updateDisplay(getAll());
            return;
        }
        List<String> moveNames = problem.getMover().getMoveNames();
        if (option < 1 || option > moveNames.size()) {
            message = NOT_IN_RANGE;
            updateDisplay(getAll());
            return;
        }
        String moveName = moveNames.get(option-1);
        solver.tryMove(moveName);
        if (!solver.isMoveLegal()) {
            message = ILLEGAL_MOVE;
            updateDisplay(getAll());
            return;
        }
        if (solver.isProblemSolved()) {
            message = CONGRATS;
        }
        updateDisplay(getAll());
    }
    
    /**
     * Returns the number of a move option as a string.
     * This method is just used for testing.
     * @param moveName the name of a move option
     * @return the number of the move option as a string
     */
    public String optionFor(String moveName) {
        int index = problem.getMover().getMoveNames().indexOf(moveName);
        if (index == -1) {
            throw new RuntimeException("Bad move name in ProblemConsole: " + moveName);
        }
        else {
            return Integer.toString(index+1);
        }
    }
    
    // Private helper methods follow.
    
    private String makePreamble() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to the ");
        builder.append(problem.getName());
        builder.append(" problem.");
        builder.append(SKIP_LINE);
        builder.append(problem.getIntroduction());
        return builder.toString();
    }
    
    private String makeCurrent() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here is the current state:");
        builder.append(SKIP_LINE);
        builder.append(problem.getCurrentState());
        builder.append(SKIP_LINE);
        builder.append("Number of moves: ");
        builder.append(solver.getMoveCount());
        return builder.toString();
    }
    
    private String makeOptions() {
        StringBuilder builder = new StringBuilder();
        builder.append("Options:");
        builder.append(NEW_LINE);
        int n = 0;
        for (String move : problem.getMover().getMoveNames()) {
            builder.append("   ");
            builder.append(Integer.toString(++n));
            builder.append(". ");
            builder.append(move);
            builder.append(NEW_LINE);
        }
        builder.append(NEW_LINE);
        builder.append("Enter 1 - ");
        builder.append(Integer.toString(n));
        builder.append(" (zero to reset)");
        return builder.toString();
    }
    
    private void clearMessage() {
        message = BLANK_LINE;
    }

    private final Problem problem;
    private final SolvingAssistant solver;
    private String preamble;
    private String options;
    private String current;
    private String message;
    
    public static final String NOT_INTEGER = "Option not an integer. Try again.";
    public static final String NOT_IN_RANGE = "Option not in range. Try again.";
    public static final String ILLEGAL_MOVE = "Illegal move. Try again.";
    public static final String CONGRATS = "You solved the problem. Congratulations.";
    
    private static final String SKIP_LINE = "\n\n";
    private static final String NEW_LINE = "\n";
    private static final String BLANK_LINE = "";
    
}
