package framework.ui;

import framework.graph.Vertex;
import framework.problem.Problem;
import framework.problem.State;
import framework.solution.AStarSolver;
import framework.solution.Solution;
import framework.solution.SolvingAssistant;
import framework.solution.StateSpaceSolver;
import framework.solution.Solver;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Ryan Lenz
 * @course CS2511
 */
public class ProblemGUI extends VBox {
    
    public ProblemGUI(Problem prob, double width, double height){
         
        problem = prob;
        solver = new SolvingAssistant(prob);
        bfs = new StateSpaceSolver(prob, true);
        dfs = new StateSpaceSolver(prob, false);
        autoSolver = bfs;
        aStar = new AStarSolver(prob);
        rem = Font.font("Dejavu Sans Mono", 24).getSize();
        moveNames = prob.getMover().getMoveNames();
        buttonSize = (double)longestMoveName().length() * 0.8 * rem;
        
        welcomeMessage = new Label(WELCOME + problem.getName());
        welcomeMessage.setFont(Font.font("Bold Dejavu Sans Mono", 24));
        welcomeMessage.setAlignment(Pos.CENTER);
        Introduction = new Label(problem.getIntroduction());
        Introduction.setWrapText(true);
        Introduction.setFont(Font.font("Dejavu Sans Mono", 16));
        
        stateBox = makeHBox();
        
        curStateBox = makeVBox();
        
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
       
        moveBox = makeVBox();
        
        message = new Label("");
        message.setFont(Font.font("Red Bold Dejavu Sans Mono" , 14));
        message.setAlignment(Pos.CENTER);
        message.setTextFill(Color.RED);
        
        searchBox = makeVBox();
        
        typeOfSearch = new Label("Search Type:");
        typeOfSearch.setFont(Font.font("Bold Dejavu Sans Mono", 14));
        
        searchType = new ChoiceBox();
        searchType.getItems().addAll("Breadth-First Search", "Depth-First Search", "A* Search");
        searchType.setValue((Object)"Breadth-First Search");
        ObjectProperty selectedSearch = searchType.valueProperty();
        selectedSearch.addListener(arg_0 -> {
          switch ((String)selectedSearch.getValue()) {
            case "Breadth-First Search": {
                autoSolver = bfs;
                break;
            }
            case "Depth-First Search": {
                autoSolver = dfs;
                break;
            }
            case "A* Search": {
                autoSolver = aStar;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid search option: " + (String)selectedSearch.getValue());
            }
        } 
        });
        
        searchBox.getChildren().addAll(typeOfSearch, searchType);
        
        statsBox = makeVBox();
        
        stats = new Label("Statistics");
        stats.setFont(Font.font("Bold Dejavu Sans Mono", 14));
        
        statString = new Label("");
        statString.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                                 CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        statString.setFont(Font.font("Dejavu Sans Mono", 12));
        statString.setPadding(new Insets(rem));
        
        statsBox.getChildren().addAll(stats, statString);
        
        solve = makeSolve();
        
        b1 = makeButton(moveNames.get(0)); 
        b2 = makeButton(moveNames.get(1));
        b3 = makeButton(moveNames.get(2));
        b4 = makeButton(moveNames.get(3));
        
        moveBox.getChildren().addAll(buttonLabel, b1, b2, b3, b4);
        
        optionBox = makeVBox();
        
        next = makeNext();
        
        reset = makeReset();
        
        optionBox.getChildren().addAll(solve, next, reset);
        
        autoBox = makeHBox();
        autoBox.getChildren().addAll(optionBox, searchBox, statsBox);
        
        finalState = new Label("Goal State:");
        finalState.setFont(Font.font("Bold Dejavu Sans Mono", 18));
        finalState.setPadding(new Insets(rem));
        finalStateString = new Label(problem.getFinalState().toString());
        finalStateString.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                                 CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        finalStateString.setFont(Font.font("Dejavu Sans Mono", 18));
        finalStateString.setPadding(new Insets(rem));
        
        finalStateBox = makeVBox();
        finalStateBox.getChildren().addAll(finalState, finalStateString);
        
        stateBox.getChildren().addAll(curStateBox, moveBox, finalStateBox);
        
        setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setPadding(new Insets(rem));
        setAlignment(Pos.CENTER);
        getChildren().addAll(welcomeMessage,
                                   Introduction,
                                   stateBox,
                                   message,
                                   autoBox);
    }
    
    public VBox getButtons() {
        
        return moveBox;
    }
    
    public List<String> getMoveNames(){
        
        return moveNames;
    }
    
    public SolvingAssistant getSolver(){
        
        return solver;
    }
    
    public void updateDisplay() {
        curStateString.textProperty().setValue(problem.getCurrentState().toString());
        buttonLabel.textProperty().setValue("Number of Moves: " + solver.getMoveCount());
        message.textProperty().setValue(curMessage);
    }
    
    private VBox makeVBox(){
        
        VBox vbox = new VBox(rem);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
    
    private HBox makeHBox(){
        
        HBox hbox = new HBox(2.0 * rem);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }
    
    public Button makeButton(String moveName){
        
        Button newButton = new Button(moveName);
        newButton.setPrefWidth(buttonSize);
        newButton.setOnAction(e -> {
           curMessage = "";
           solver.tryMove(moveName);
           if(!solver.isMoveLegal()){
               curMessage = "Illegal move. Try again.";
           }
           else if(solver.isProblemSolved()){
               curMessage = "You solved the problem. Congratulations!";
           }
           updateDisplay();
        });
        newButton.disableProperty().bind((ObservableValue)solve.disableProperty());
        return newButton;
    }
    
    private Button makeSolve(){
        
        Button solveButton = new Button("Solve");
        solveButton.setAlignment(Pos.CENTER);
        solveButton.setFont(Font.font("Dejavu Sans Mono", 14));
        solveButton.setOnAction(e -> {
            solveButton.setDisable(true);
            State cur = problem.getCurrentState();
            autoSolver.solve();
            solution = autoSolver.getSolution();
            solution.next();
            statString.setText(this.autoSolver.getStatistics().toString());
            updateDisplay();
            curStateString.setText(cur.toString());
            System.out.println(this.autoSolver.getStatistics().toString());
        });
        return solveButton;
    }
    
    private Button makeNext(){
       
        Button nextButton = new Button("Next");
        nextButton.setAlignment(Pos.CENTER);
        nextButton.setFont(Font.font("Dejavu Sans Mono", 14));
        nextButton.setDisable(true);
        nextButton.disableProperty().bind((ObservableValue)Bindings.not((ObservableBooleanValue)solve.disableProperty()));
        nextButton.setOnAction(e -> {
           if(solution.hasNext()){
               Vertex v = solution.next();
               State s = (State)v.getData();
               solver.update(s);
               if(solver.isProblemSolved()){
                   curMessage = "You solved the problem. Congratulations!";
               }
               updateDisplay();
           } 
        });
        return nextButton;
    }
    
    private Button makeReset(){
        
        Button resetButton = new Button("Reset");
        resetButton.setFont(Font.font("Dejavu Sans Mono", 12));
        resetButton.setAlignment(Pos.CENTER);
        resetButton.setOnAction(e -> {reset();
                                solve.setDisable(false);});
        return resetButton;
    }
    
    private String longestMoveName(){
        
        Optional<String> longest = moveNames.stream().max((first, second) -> Integer.compare(first.length(), second.length()));
        if(!longest.isPresent()) return "";
        return longest.get();
    }
    
    private void reset() {
        
        solver.reset();
        curMessage = "";
        updateDisplay();
    }
    
    private final Problem problem;
    private final SolvingAssistant solver;
    private final List<String> moveNames;
    private final StateSpaceSolver bfs, dfs;
    private final AStarSolver aStar;
    private Solver autoSolver;
    private Solution solution;
    
    
    private final Label welcomeMessage, Introduction, currentState, curStateString, finalState, finalStateString, buttonLabel, message, typeOfSearch, stats, statString;
    private final HBox stateBox, autoBox;
    private final VBox curStateBox, moveBox, finalStateBox, optionBox, searchBox, statsBox;
    private Button b1, b2, b3, b4, reset, solve, next;
    private ChoiceBox searchType;
    private final double rem, buttonSize;
    private String curMessage;
    
    private static final String WELCOME = "Welcome to the ";
    private static final double DEFAULT_WIDTH = 1000;
    private static final double DEFAULT_HEIGHT = 850;
    
}
