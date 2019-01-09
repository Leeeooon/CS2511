package gui;

import gui.MatrixControl;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tcolburn
 */
public class MatrixControlTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MatrixControl mc = new MatrixControl(3, 3, 50, 6, true);
        Scene scene = new Scene(mc);
        primaryStage.setTitle("Matrix Control Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
