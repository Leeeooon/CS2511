package calculator;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Precision;

/**
 *
 * @author tcolburn
 */
public class NoPrecedenceCalculatorTest extends Application {
    
    public NoPrecedenceCalculatorTest() {
        JFXPanel fxPanel = new JFXPanel(); // to initialize JavaFX toolkit
        calculator = new NoPrecedenceCalculator();
    }

    @Test
    public void testResults() {
        calculator.handleInput("21 + 63 * 47");
        assertTrue(Precision.equals(calculator.getResult(), ((double)21 + 63) * 47));
        
        calculator.handleInput("12 - 3.6 * 47 / 99.99 + 3.14159");
        assertTrue(Precision.equals(calculator.getResult(), ((((double)12 - 3.6) * 47) / 99.99) + 3.14159)) ;
    }

    @Test
    public void testErrors() {
        calculator.handleInput("13.4 * 73.9 foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- operator or end of input expected. Found: foobar"));
        
        calculator.handleInput("13.4 * 73.9 + foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: foobar"));
        
        calculator.handleInput("13.4 * 73.9 + 17");
        assertTrue(calculator.getErrorMessage().equals(""));
        
        calculator.handleInput("13.4 * 73.9 + 17 - / 69.6");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: /"));
        
        calculator.handleInput("13.4 * 73.9 + 17 - 69.6 foobar  ");
        assertTrue(calculator.getErrorMessage().equals("Error -- operator or end of input expected. Found: foobar"));
        
        calculator.handleInput("13.4 * 73.9 + 17 - 69.6 /  ");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: end of input"));
        
        calculator.handleInput("13.4 * 73.9 + 17 - 69.6 / 3.14159 ");
        assertTrue(calculator.getErrorMessage().equals(""));
    }
    
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(calculator);
        primaryStage.setTitle("Testing Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void testLaunch() {
        Application.launch();
    }
    
    private NoPrecedenceCalculator calculator;
    
}
