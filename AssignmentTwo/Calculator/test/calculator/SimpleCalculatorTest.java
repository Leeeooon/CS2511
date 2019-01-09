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
public class SimpleCalculatorTest extends Application {
    
    public SimpleCalculatorTest() {
        JFXPanel fxPanel = new JFXPanel(); // to initialize JavaFX toolkit
        calculator = new SimpleCalculator("Simple Calculator");
    }

    @Test
    public void testResults() {
        calculator.handleInput("7");
        assertTrue(Precision.equals(calculator.getResult(), (double)7));
        
        calculator.handleInput("123.45");
        assertTrue(Precision.equals(calculator.getResult(), 123.45));
        
        calculator.handleInput("7*8");
        assertTrue(Precision.equals(calculator.getResult(), (double)7 * 8));
        
        calculator.handleInput(" 17 + 82.3 ");
        assertTrue(Precision.equals(calculator.getResult(), (double)17 + 82.3));
        
        calculator.handleInput("27.3-8");
        assertTrue(Precision.equals(calculator.getResult(), 27.3 - 8));
        
        calculator.handleInput("7/8");
        assertTrue(Precision.equals(calculator.getResult(), (double)7 / 8));
    }

    @Test
    public void testErrors() {
        calculator.handleInput("foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: foobar"));
        
        calculator.handleInput("13.4 foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- operator or end of input expected. Found: foobar"));
        
        calculator.handleInput("13.4 * ");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: end of input"));
        
        calculator.handleInput("13.4 * foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- number expected. Found: foobar"));
        
        calculator.handleInput("13.4 * 73.9 foobar");
        assertTrue(calculator.getErrorMessage().equals("Error -- end of input expected. Found: foobar"));
        
        calculator.handleInput("13.4 * 73.9");
        assertTrue(calculator.getErrorMessage().equals(""))
                ;
        calculator.handleInput("21 + 63 * 47");
        assertTrue(calculator.getErrorMessage().equals("Error -- end of input expected. Found: *"));
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
    
    private SimpleCalculator calculator;
    
}