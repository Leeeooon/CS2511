package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a data representation for the AbstractMatrix
 * implementation of the Matrix API.
 * It uses a list of list of integers to store matrix elements.
 * @author tcolburn
 */
public class ArrayListImplementation extends AbstractMatrix {

    /**
     * Creates a list representation of a matrix of integers.
     * Elements of the list are initialized to zero.
     * @param numRows the number of rows in the matrix
     * @param numColumns the number of columns in the matrix
     * @throws MatrixException if dimensions are not positive
     */
    public ArrayListImplementation(int numRows, int numColumns) {
        super.setNumRows(numRows);
        super.setNumColumns(numColumns);
        elements = new ArrayList<>(numRows);
        for(int i=0;i<numRows;i++)
        {
         elements.add(i,new ArrayList<>(numRows));
        
            for(int g=0;g<numColumns;g++)
             elements.get(i).add(g,0);
        }
             super.clear();
    }
    
    /**
     * Gets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @return the element at the indicated row and column
     * @throws MatrixException if row or column is out of range
     */
    @Override
    public int get(int row, int column) {
         checkBounds(row, column);
         return elements.get(row).get(column);
     }

    /**
     * Sets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @param element the value to set in the matrix
     * @throws MatrixException if row or column is out of range
     */
    @Override
    public void set(int row, int column, int element) {
        checkBounds(row, column);
        elements.get(row).set(column,element);
    }
    
    private List<List<Integer>> elements;
    
    
}
