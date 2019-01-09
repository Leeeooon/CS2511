package domains.arithmetic;

import framework.ui.ProblemConsole;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArithmeticConsole extends Application
{
  public void start(Stage primaryStage)
  {
    Scene scene = new Scene(new ProblemConsole(new ArithmeticProblem()));
    primaryStage.setTitle("Testing Arithmetic Console");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
