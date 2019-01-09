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
public class ParenthesisCalculatorTest extends Application {
    
    public ParenthesisCalculatorTest() {
        JFXPanel fxPanel = new JFXPanel(); // to initialize JavaFX toolkit
        calculator = new ParenthesisCalculator();
    }
    
    @Test
    public void testResults() {
        calculator.handleInput("21 + 63 * 47");
        assertTrue(Precision.equals(calculator.getResult(), (double)21 + 63 * 47));
        
        calculator.handleInput("(21 + 63 * 47)");
        assertTrue(Precision.equals(calculator.getResult(), (double)(21 + 63 * 47)));
        
        calculator.handleInput("21 + (63 * 47)");
        assertTrue(Precision.equals(calculator.getResult(), (double)21 + 63 * 47));
        
        calculator.handleInput("(21 + 63) * 47");
        assertTrue(Precision.equals(calculator.getResult(), ((double)21 + 63) * 47));
        
        calculator.handleInput("((2 + 3) * 4)");
        assertTrue(Precision.equals(calculator.getResult(), ((double)2 + 3) * 4));
        
        calculator.handleInput("((2 + (3)) * (((4))))");
        assertTrue(Precision.equals(calculator.getResult(), ((double)2 + 3) * 4));
        
        calculator.handleInput("((2+(3+(4*5))))");
        assertTrue(Precision.equals(calculator.getResult(), (((double)2+(3+(4*5))))));
        
        calculator.handleInput("1 * 2 * 3 * (4 + 5) * 6 * 7");
        assertTrue(Precision.equals(calculator.getResult(), 1 * 2 * 3 * (4 + 5) * 6 * 7));
        
        calculator.handleInput("12 - 3.6 * 47 / 99.99 + 3.14159");
        assertTrue(Precision.equals(calculator.getResult(), (double)12 - 3.6 * 47 / 99.99 + 3.14159)) ;
        
        calculator.handleInput("(12 - 3.6) * 47 / (99.99 + 3.14159)");
        assertTrue(Precision.equals(calculator.getResult(), ((double)12 - 3.6) * 47 / (99.99 + 3.14159))) ;
    }

    @Test
    public void testErrors() {
        calculator.handleInput("( 2 + 3 ");
        assertTrue(calculator.getErrorMessage().equals("Error -- mismatched parentheses"));
        
        calculator.handleInput("2 + 3 )");
        assertTrue(calculator.getErrorMessage().equals("Error -- mismatched parentheses"));
        
        calculator.handleInput("2 + ((3 + 4) * 5");
        assertTrue(calculator.getErrorMessage().equals("Error -- mismatched parentheses"));
        
        calculator.handleInput("2 + ((3 + 4) * 5))");
        assertTrue(calculator.getErrorMessage().equals("Error -- mismatched parentheses"));
        
        calculator.handleInput("2 + ((3 + 4) * 5)(");
        assertTrue(calculator.getErrorMessage().equals("Error -- operator expected. Found: ("));
        
        calculator.handleInput("2 + () + 3");
        assertTrue(calculator.getErrorMessage().equals("Error -- number or left parenthesis expected. Found: )"));
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
    
    private ParenthesisCalculator calculator;
    
}
