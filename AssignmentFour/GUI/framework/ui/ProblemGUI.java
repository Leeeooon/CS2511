package framework.ui;

import framework.problem.Problem;
import framework.solution.SolvingAssistant;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;

/**
 * 
 * This class provides the framework for a Graphical User Interface that 
 * represents a problem solving domain
 */

public class ProblemGUI extends VBox {
    /**
    * Creates a problem GUI with a given width and height
    * @param prob the problem
    * @param width the width of the display area
    * @param height the height of the display area
    * @author Ryan Lenz
    */
    
    public ProblemGUI(Problem prob, double width, double height){
         
        problem = prob;
        solver = new SolvingAssistant(prob);
        rem = Font.font("Dejavu Sans Mono", 24).getSize();
        
        welcomeMessage = new Label(WELCOME + problem.getName());
        welcomeMessage.setFont(Font.font("Bold Dejavu Sans Mono", 24));
        welcomeMessage.setPadding(new Insets(rem));
        welcomeMessage.setAlignment(Pos.CENTER);
        Introduction = new Label(problem.getIntroduction());
        Introduction.setWrapText(true);
        Introduction.setFont(Font.font("Dejavu Sans Mono", 16));
        Introduction.setPadding(new Insets(rem));
        
        stateBox = new HBox();
        stateBox.setAlignment(Pos.CENTER);
        stateBox.setPadding(new Insets(rem));
        
        curStateBox = new VBox();
        curStateBox.setAlignment(Pos.CENTER);
        curStateBox.setPadding(new Insets(rem));
        currentState = new Label("Current State:");
        currentState.setFont(Font.font("Bold Dejavu Sans Mono", 18));
        currentState.setPadding(new Insets(rem));
        curStateString = new Label(problem.getCurrentState().toString());
        curStateString.setFont(Font.font("Deja Vu Sans Mono", 18));
        curStateString.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                                 CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        curStateString.setPadding(new Insets(rem));
        curStateBox.getChildren().addAll(currentState, curStateString);
        
        buttonLabel = new Label("Number of Moves: " + solver.getMoveCount());
        buttonLabel.setFont(Font.font("Bold Dejavu Sans Mono", 18));
        buttonLabel.setPadding(new Insets(rem));
       
        buttonBox = new VBox();
        buttonBox.setPadding(new Insets(rem));
        buttonBox.setAlignment(Pos.CENTER);
        moveNames = prob.getMover().getMoveNames();

        b1 = new Button(moveNames.get(0));
        b1.setAlignment(Pos.CENTER);
        b1.setFont(Font.font("Dejavu Sans Mono", 14));
        b1.setPadding(new Insets(rem));
        b1.setOnAction(e -> {solver.tryMove(moveNames.get(0));
                             updateDisplay();});
        
        b2 = new Button(moveNames.get(1));
        b2.setAlignment(Pos.CENTER);
        b2.setFont(Font.font("Dejavu Sans Mono", 14));
        b2.setPadding(new Insets(rem));
        b2.setOnAction(e -> {solver.tryMove(moveNames.get(1));
                       updateDisplay();});
        
        b3 = new Button(moveNames.get(2));
        b3.setAlignment(Pos.CENTER);
        b3.setFont(Font.font("Dejavu Sans Mono", 14));
        b3.setPadding(new Insets(rem));
        b3.setOnAction(e -> {solver.tryMove(moveNames.get(2));
                       updateDisplay();});
        
        b4 = new Button(moveNames.get(3));
        b4.setAlignment(Pos.CENTER);
        b4.setFont(Font.font("Dejavu Sans Mono", 14));
        b4.setPadding(new Insets(rem));
        b4.setOnAction(e -> {solver.tryMove(moveNames.get(3));
                       updateDisplay();});
        
        buttonBox.getChildren().addAll(buttonLabel, b1, b2, b3, b4);
        
        
        
        finalState = new Label("Goal State:");
        finalState.setFont(Font.font("Bold Dejavu Sans Mono", 18));
        finalState.setPadding(new Insets(rem));
        finalStateString = new Label(problem.getFinalState().toString());
        finalStateString.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                                 CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        finalStateString.setFont(Font.font("Dejavu Sans Mono", 18));
        finalStateString.setPadding(new Insets(rem));
        finalStateBox = new VBox();
        finalStateBox.setAlignment(Pos.CENTER);
        finalStateBox.setPadding(new Insets(rem));
        finalStateBox.getChildren().addAll(finalState, finalStateString);
        
        stateBox.getChildren().addAll(curStateBox, buttonBox, finalStateBox);
        
        message = new Label("");
        message.setFont(Font.font("Red Bold Dejavu Sans Mono" , 16));
        message.setPadding(new Insets(rem));
        message.setAlignment(Pos.CENTER);
        message.setTextFill(Color.RED);
        
        reset = new Button("Reset");
        reset.setFont(Font.font("Dejavu Sans Mono", 12));
        reset.setAlignment(Pos.CENTER);
        reset.setPadding(new Insets(rem));
        reset.setOnAction(e -> {solver.reset();
                                message.textProperty().setValue("");
                                updateDisplay();});
        
        super.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        super.setPadding(new Insets(rem));
        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(welcomeMessage,
                                   Introduction,
                                   stateBox,
                                   message,
                                   reset);
    }
    
    /**
     * Getter for the VBox that contains the buttons
     * @return buttonBox 
     */
    public VBox getButtons() {
        
        return buttonBox;
    }
    
    /**
     * Getter for the list of string that contains the move names
     * @return moveNames
     */
    public List<String> getMoveNames(){
        
        return moveNames;
    }
    
    /**
     * Getter for the solving assistant used to solve the problem
     * @return solver 
     */
    public SolvingAssistant getSolver(){
        
        return solver;
    }
    
    /**
     * Updates text in the display
     */
    public void updateDisplay() {
        curStateString.textProperty().setValue(problem.getCurrentState().toString());
        buttonLabel.textProperty().setValue("Number of Moves: " + solver.getMoveCount());
        message.textProperty().setValue("");
        if(solver.isProblemSolved()){
                message.textProperty().setValue("Congratulations! You have solved the problem!");
        }
       
        if(!solver.isMoveLegal()){
            message.textProperty().setValue("Illegal move, Try again.");
        }
    }
    
    private final Problem problem;
    private final SolvingAssistant solver;
    private final List<String> moveNames;
    
    private final Label welcomeMessage, Introduction, currentState, curStateString, finalState, finalStateString, buttonLabel, message;
    private final HBox stateBox;
    private final VBox curStateBox, buttonBox, finalStateBox;
    private Button b1, b2, b3, b4, reset;
    private final double rem;
    
    private static final String WELCOME = "Welcome to the ";
    private static final double DEFAULT_WIDTH = 1000;
    private static final double DEFAULT_HEIGHT = 850;    
}
