package gui;

import gui.MatrixOperation;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tcolburn
 */
public class MatrixOperationTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MatrixOperation mo = new MatrixOperation(3, 3, 4, false);
        Scene scene = new Scene(mo);
        primaryStage.setTitle("Matrix Operation Demo");
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
