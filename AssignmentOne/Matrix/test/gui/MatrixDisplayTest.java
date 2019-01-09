package gui;

import gui.MatrixDisplay;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import matrix.Matrix;

/**
 *
 * @author tcolburn
 */
public class MatrixDisplayTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MatrixDisplay md = new MatrixDisplay(50, 4, true);
        md.setPadding(new Insets(10));
        md.update(Matrix.create(4, 3));
        Scene scene = new Scene(md);
        primaryStage.setTitle("Matrix Display Demo");
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
