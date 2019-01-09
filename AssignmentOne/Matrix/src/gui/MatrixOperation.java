package gui;

import gui.MatrixControl;
import gui.MatrixDisplay;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import matrix.Matrix;
import static gui.MatrixControl.GAP;

/**
 * 
 * @author tcolburn
 */
public class MatrixOperation extends HBox {
    
    public MatrixOperation(int rows, int columns, int maxDim, boolean bigFontFlag) {
        super(GAP);
        mc1 = new MatrixControl(rows, columns, E_SIZE, maxDim, bigFontFlag);
        mc2 = new MatrixControl(rows, columns, E_SIZE, maxDim, bigFontFlag);
        result = new MatrixDisplay(E_SIZE, maxDim, bigFontFlag);
        
        opChoices = new ChoiceBox<>();
        operations = opChoices.getItems();
        operations.addAll(CHOOSE, EQUALS);
        updateOperations();
        
        super.getChildren().addAll(new LabeledNode("Matrix 1", mc1), 
                                   new LabeledNode("Matrix 2", mc2), 
                                   new LabeledNode("Operations", opChoices), 
                                   new LabeledNode("Result", result));
        setAlignment(Pos.CENTER);
        setPadding(new Insets(GAP));
        addListeners();
    }
    
    private void addListeners() {
        // update operations when either operand matrix changes
        mc1.matrixProperty().addListener(o -> updateOperations());
        mc2.matrixProperty().addListener(o -> updateOperations());
        
        // clear out the result display when either matrix is edited
        mc1.getEditedProperty().addListener(o -> clear());
        mc2.getEditedProperty().addListener(o -> clear());
        
        // clear out the result display after an action on either operand
        mc1.getActionProperty().addListener(o -> clear());
        mc2.getActionProperty().addListener(o -> clear());
        
        // perform a matrix operation
        opChoices.valueProperty().addListener(e -> {
            Matrix m1 = mc1.getMatrix();
            Matrix m2 = mc2.getMatrix();
            String op = (String)opChoices.valueProperty().getValue();
            if (op.equals(ADD)) {
                result.update(m1.add(m2));
            }
            else if (op.equals(MUL)) { 
                result.update(m1.multiply(m2));
            }
            else if (op.equals(EQUALS)) {
                result.updateBoolean(m1.equals(m2));
            }
            else if (op.equals(CHOOSE)) {
                //    do nothing
            }
            else {    // shouldn't happen
                throw new RuntimeException("Bad op in addListeners");
            }
        });
    }
    
    private void clear() {
        result.clear(); opChoices.setValue(CHOOSE);
    }
    
    private void updateOperations() {
        if (compatibleForAdd()) {
            if (!operations.contains(ADD)) {
                operations.add(ADD);
            }
        }
        else {
            operations.remove(ADD);
        }
        if (compatibleForMultiply()) {
            if (!operations.contains(MUL)) {
                operations.add(MUL);
            }
        }
        else {
            operations.remove(MUL);
        }
        opChoices.setValue(CHOOSE);
        result.clear();
    }
    
    private boolean compatibleForAdd() {
        return mc1.getMatrix().getNumRows() == mc2.getMatrix().getNumRows() &&
               mc1.getMatrix().getNumColumns() == mc2.getMatrix().getNumColumns();
    }
    
    private boolean compatibleForMultiply() {
        return mc1.getMatrix().getNumColumns() == mc2.getMatrix().getNumRows();
    }
    
    private class LabeledNode extends VBox {
        
        public LabeledNode(String label, Node node) {
            super(GAP, new Label(label), node);
            setAlignment(Pos.CENTER);
        }
    }
    
    private final MatrixControl mc1;
    private final MatrixControl mc2;
    private final MatrixDisplay result;
    
    private final ObservableList<String> operations;
    private final ChoiceBox opChoices;
    
    private final static int E_SIZE = 50;
    
    private final static String CHOOSE = "Choose";
    private final static String EQUALS = "Equals";
    private final static String ADD = "Add";
    private final static String MUL = "Multiply";
    
}
