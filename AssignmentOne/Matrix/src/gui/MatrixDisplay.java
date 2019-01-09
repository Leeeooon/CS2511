package gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import matrix.Matrix;
import static gui.MatrixControl.MAX_DIMENSION;

/**
 * This class provides a display area for elements of a matrix, or for the
 * results of matrix operations.
 * @author tcolburn
 */
public class MatrixDisplay extends GridPane {
    
    /**
     * Creates a matrix display.
     * @param elementSize the size of the display area for a matrix element in pixels
     * @param maxDimension the maximum number of rows and columns for this display
     * @param bigFontFlag boolean flag indicating whether to use a larger font than normal
     */
    public MatrixDisplay(int elementSize, int maxDimension, boolean bigFontFlag) {
        this.elementSize = elementSize;
        super.setPrefSize(maxDimension*elementSize*1.2, 
                          maxDimension*elementSize*1.2);
        setGridLinesVisible(true);
        font = bigFontFlag ? Font.font(FONT_FAMILY, FontWeight.BOLD, elementSize/3) : 
                Font.font(FONT_FAMILY, FontWeight.BOLD, elementSize/4);
        fontForBoolean = Font.font(FONT_FAMILY, FontWeight.BOLD, elementSize);
        setBorder(BORDER);
        edited = new SimpleBooleanProperty(false);
    }
    
    /**
     * Creates a matrix display.
     * @param elementSize the size of the display area for a matrix element in pixels
     * @param maxDimension the maximum number of rows and columns for this display
     */
    public MatrixDisplay(int elementSize, int maxDimension) {
        this(elementSize, maxDimension, false);
    }
    
    /**
     * Creates a matrix display using default values for element size
     * and maximum dimension.
     */
    public MatrixDisplay() {
        this(DEFAULT_ELEMENT_SIZE, MAX_DIMENSION);
    }

    /**
     * Getter for the matrix being displayed.
     * @return the matrix being displayed
     */
    public Matrix getM() {
        return m;
    }
    
    /**
     * Clears the display area.
     */
    public void clear() {
        getChildren().clear();
    }
    
    /**
     * Updates the display area with elements from the provided matrix.
     * @param m the matrix to display
     */
    public void update(Matrix m) {
        this.m = m;
        clear();
        addHeaders();
        addElements();
    }
    
    /**
     * Updates the display area with a "True" or "False" label.
     * This is for displaying the results of matrix equality tests.
     * @param trueOrFalse whether to display "True" or "False"
     */
    public void updateBoolean(boolean trueOrFalse) {
        clear();
        Label label = new Label(trueOrFalse ? "True" : "False");
        label.setFont(fontForBoolean);
        add(label, 0, 0);
    }

    /**
     * Getter for a JavaFX boolean property indicating whether the matrix
     * being displayed has been edited by the user.
     * This property toggles between true and false each time an edit occurs.
     * It can be used as a change listener for the matrix being displayed.
     * @return whether the matrix has been edited
     */
    public SimpleBooleanProperty getEdited() {
        return edited;
    }
    
    /**
     * Adds row and column numbers as headers for this display.
     */
    private void addHeaders() {
        add(new Header(" "), 0, 0);
        for (int c = 0; c < m.getNumColumns(); c++) {
            add(new Header(Integer.toString(c)), c+1, 0);
        }
        for (int r = 0; r < m.getNumRows(); r++) {
            add(new Header(Integer.toString(r)), 0, r+1);
        }
    }
    
    /**
     * Adds the actual matrix elements to this display.
     */
    private void addElements() {
        for (int r = 0; r < m.getNumRows(); r++) {
            for (int c = 0; c < m.getNumColumns(); c++) {
                add(new Element(Integer.toString(m.get(r, c)), r, c), c+1, r+1);
            }
        }
    }
            
    /**
     * This class represents a row or column header as a label presented in black.
     */
    private class Header extends Label {
        
        public Header(String element) {
            super(element);
            super.setPrefSize(elementSize, elementSize);
            setAlignment(Pos.CENTER);
            setFont(font);
            setTextFill(Color.BLACK);
        }
    }
    
    /**
     * This class represents an editable matrix element as a text field presented
     * in red.
     */
    private class Element extends TextField {
        
        public Element(String element, int r, int c) {
            super(element);
            super.setPrefSize(elementSize, elementSize);
            setAlignment(Pos.CENTER);
            setFont(font);
            setStyle("-fx-text-fill: red");
            textProperty().addListener(o -> {                 
                String newElement = textProperty().get();
                try {     // parse and set element 
                    m.set(r, c, Integer.parseInt(newElement));   //System.out.println("\n"+m.toString()+"\n");
                    edited.set(!edited.get());
                }
                catch(NumberFormatException ex) { // retain original element 
                    textProperty().set(Integer.toString(m.get(r, c)));
                }
            });
        }
        
    }
    
    private Matrix m;
    private int elementSize;
    private Font font;
    private Font fontForBoolean;
    
    private SimpleBooleanProperty edited;
    
    public final static String FONT_FAMILY = "DejaVu Serif";
    public final static int DEFAULT_ELEMENT_SIZE = 50;
    
    private final static Border BORDER = new Border(new BorderStroke(null, 
                                                        BorderStrokeStyle.SOLID, 
                                                        null, 
                                                        BorderStroke.MEDIUM));
}
