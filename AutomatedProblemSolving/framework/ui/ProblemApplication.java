package framework.ui;

import domains.arithmetic.ArithmeticGUI;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import domains.farmer.FarmerGUI;
import domains.puzzle.PuzzleGUI;
import domains.dummy.DummyGUI;

/**
 * This class presents problem solving domains in a tabbed pane.
 */
public class ProblemApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        
	/* Add tabs using the following */
        
        Tab dummy = new Tab();
        dummy.setText("Dummy Problem");
        dummy.setContent(new DummyGUI());
        tabPane.getTabs().add(dummy);

	Tab fwgc = new Tab();
        fwgc.setText("Farmer, Wolf, Goat, Cabbage Problem");
        fwgc.setContent(new FarmerGUI());
        tabPane.getTabs().add(fwgc);
        
        Tab arithmetic = new Tab();
        arithmetic.setText("Arithmetic Problem");
        arithmetic.setContent(new ArithmeticGUI());
        tabPane.getTabs().add(arithmetic);
        
        Tab puzzle = new Tab();
        puzzle.setText("Puzzle Problem");
        puzzle.setContent(new PuzzleGUI());
        tabPane.getTabs().add(puzzle);
        
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle("Problem Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
