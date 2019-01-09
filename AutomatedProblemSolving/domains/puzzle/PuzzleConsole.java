/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.puzzle;

import framework.ui.ProblemConsole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ryan Lenz
 * 
 */
public class PuzzleConsole extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new ProblemConsole(new PuzzleProblem(), 450, 550));
        primaryStage.setTitle("Testing Puzzle Console");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
