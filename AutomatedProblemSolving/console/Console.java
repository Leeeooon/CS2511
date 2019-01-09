package console;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * This class simulates a simple terminal console using JavaFX components.
 * The input area is a TextField, while the display area is a TextArea,
 * laid out in a VBox.
 * @author tcolburn
 */
public class Console extends VBox {

    /**
     * Creates a console with a given title and width/height.
     * @param title a title for the console
     * @param width the console width
     * @param height the console height
     */
    public Console(String title, double width, double height) {
        
        this.title = title;
        display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setFont(new Font("Monospaced Bold", 15));
        display.setPrefSize(width, height);
        
        keyboard = new TextField();
        keyboard.setOnAction(e -> process());
        
        super.getChildren().addAll(new Label(title), 
                                   display,
                                   new Label("Input (press Enter to send):"), 
                                   keyboard);
    }
    
    /**
     * Creates a console with default title, width, and height.
     */
    public Console() {
        this("Echo Console", DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Getter for the title of this console.
     * @return the title
     */
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
    
    /**
     * Updates the text of the console's display.
     * @param text the new text
     */
    public void updateDisplay(String text) {
        display.setText(text);
    }
    
    // Private helper methods follow
    
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