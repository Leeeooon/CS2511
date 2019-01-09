package gui;

import gui.MatrixDisplay;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import matrix.Matrix;
import matrix.MatrixException;
import static gui.MatrixDisplay.DEFAULT_ELEMENT_SIZE;

/**
 * TODO: 
 * @author tcolburn
 */
public class MatrixControl extends VBox {
    
    public MatrixControl() {
        this(ROWS_DEFAULT, COLUMNS_DEFAULT, DEFAULT_ELEMENT_SIZE, MAX_DIMENSION, false);
    }
    
    public MatrixControl(int rows, int columns, int elementSize, int maxDimension, boolean bigFontFlag) {
        if (rows > maxDimension || columns > maxDimension) {
            throw new MatrixException("Number of rows or columns cannot exceed max dimension");
        }
        this.maxDimension = maxDimension;
        Matrix m = Matrix.create(rows, columns);
        matrix = new SimpleObjectProperty<>(m);
        display = new MatrixDisplay(elementSize, maxDimension, bigFontFlag);
        display.update(matrix.getValue());
        
        // Make and listen for matrix actions
        actionChoices = new ChoiceBox<>();
        actions = actionChoices.getItems();
        actions.addAll(CHOOSE, CREATE, ROW_WISE, COLUMN_WISE, CLEAR, TRANSPOSE);
        if (m.getNumRows() == m.getNumColumns()) {
            actions.add(IDENTITY);
        }
        actionChoices.setValue(CHOOSE);
        
        actionChoices.valueProperty().addListener(e -> {
            ObservableValue<String> selectedAction = actionChoices.valueProperty();
            String selectedValue = selectedAction.getValue();
            doAction(selectedValue);
            display.update(matrix.getValue());
            actionChoices.setValue(CHOOSE);
        });
        
        VBox actionBox = new VBox(new Label("Actions"), actionChoices);
        actionBox.setAlignment(Pos.CENTER);
        super.getChildren().addAll(display, actionBox);
        
        // For controlling the dimensions of newly created matrices
        rowSpinner = new LabeledSpinner("Rows", rows);
        columnSpinner = new LabeledSpinner("Columns", columns);
        dimensionControls = new HBox(GAP, rowSpinner, columnSpinner);
        dimensionControls.setAlignment(Pos.CENTER);
    }
    
    public Property<Matrix> matrixProperty() {
        return matrix;
    }

    public Matrix getMatrix() {
        return matrix.getValue();
    }
    
    public ObservableValue getEditedProperty() {
        return display.getEdited();
    }
    
    public ObservableValue getActionProperty() {
        return actionChoices.valueProperty();
    }
    
    private void updateActions() {
        Matrix m = matrix.getValue();
        if (m.getNumRows() == m.getNumColumns()) {
            if (!actions.contains(IDENTITY)) {
                actions.add(IDENTITY);
            }
        }
        else {
            actions.remove(IDENTITY);
        }
    }
    
    private void doAction(String action) {
        Matrix m = matrix.getValue();
        if (action.equals(CREATE)) {
            promptForNew();
        }
        else if (action.equals(ROW_WISE)) {
            m.fillRowWise();
        }
        else if (action.equals(COLUMN_WISE)) {
            m.fillColumnWise();
        }
        else if (action.equals(CLEAR)) {
            m.clear();
        }
        else if (action.equals(TRANSPOSE)) {
            m = m.transpose();
            matrix.setValue(m);
            rowSpinner.setValue(m.getNumRows());
            columnSpinner.setValue(m.getNumColumns());
        }
        else if (action.equals(IDENTITY)) {
            m.makeIdentity();
        }
        else if (action.equals(CHOOSE)) {
            //    do nothing
        }
        else {  // shouldn't happen
            throw new RuntimeException("bad matrix action in doAction");
        }
    }
    
    private void promptForNew() {
        rowSpinner.setValue(3);
        columnSpinner.setValue(3);
        Alert prompt = new Alert(Alert.AlertType.INFORMATION);
        prompt.getDialogPane().setContent(dimensionControls);
        prompt.showAndWait();
        matrix.setValue(Matrix.create(rowSpinner.getValue(), columnSpinner.getValue()));
        updateActions();
    }
    
    private class LabeledSpinner extends VBox {
        
        public LabeledSpinner(String text, int value) {
            theLabel = new Label(text);
            theSpinner = new Spinner<>(MIN_DIMENSION, maxDimension, value);
            theSpinner.setPrefWidth(SPINNER_WIDTH);
            super.getChildren().addAll(theLabel, theSpinner);
            super.setAlignment(Pos.CENTER);
        }
        
        public void setValue(int value) {
            theSpinner.getValueFactory().setValue(value);
        }
        
        public int getValue() {
            return theSpinner.valueProperty().getValue();
        }
        
        private final Label theLabel;
        private final Spinner<Integer> theSpinner;
        
    }
    
    private int maxDimension;
    
    private Property<Matrix> matrix;
    
    private MatrixDisplay display;
    private final LabeledSpinner rowSpinner;
    private final LabeledSpinner columnSpinner;
    
    private HBox dimensionControls;
    
    private final static int ROWS_DEFAULT = 3;
    private final static int COLUMNS_DEFAULT = 4;
    private final static int MIN_DIMENSION = 1;
    public final static int MAX_DIMENSION = 10;
    public final static int GAP = 10;
    private final static int SPINNER_WIDTH = 75;
    
    private final static String CHOOSE = "Choose";
    private final static String CREATE = "Create New";
    private final static String ROW_WISE = "Fill Row-wise";
    private final static String COLUMN_WISE = "Fill Column-wise";
    private final static String CLEAR = "Clear Matrix";
    private final static String TRANSPOSE = "Transpose Matrix";
    private final static String IDENTITY = "Make Identity";
    
    private ObservableList<String> actions;
    private ChoiceBox actionChoices;
}
