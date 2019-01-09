package console;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author Your name and section here
 */
public class Console extends VBox {

    public Console(String title, double width, double height) {
        
        this.title = title;
        display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setFont(new Font("Monospaced Bold", 15));
        display.setPrefSize(width, height);
        
        keyboard = new TextField();
        keyboard.setOnAction(e -> process());
        
        getChildren().addAll(new Label(title), 
                             display,
                             new Label("Input (press Enter to send):"), 
                             keyboard);
    }
    
    public Console() {
        this("Echo Console", DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public String getTitle() {
        return title;
    }
    
    /**
     * Default action is to simply echo the input to the display.
     * This method should be overridden.
     * @param input the keyboard input
     */
    public void handleInput(String input) {
        updateDisplay(input);
    }
    
    public void updateDisplay(String text) {
        display.setText(text);
    }
    
    private void process() {
        input = keyboard.getText();
        keyboard.selectAll();
        handleInput(input);
    }
    
    private String title;
    private TextArea display;
    private TextField keyboard;
    private String input;
    
    private static final double DEFAULT_WIDTH = 400;
    private static final double DEFAULT_HEIGHT = 400;
}