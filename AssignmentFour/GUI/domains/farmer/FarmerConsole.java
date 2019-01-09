package domains.farmer;

import framework.ui.ProblemConsole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FarmerConsole extends Application
{
  public void start(Stage primaryStage)
  {
    Scene scene = new Scene(new ProblemConsole(new FarmerProblem(), 600.0D, 600.0D));
    primaryStage.setTitle("Testing FWGC Console");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}