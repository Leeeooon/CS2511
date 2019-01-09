
package matrix;

/**
 * This class provides a data representation for the AbstractMatrix
 * implementation of the Matrix API.
 * It uses a two-dimensional array of integers to store matrix elements.
 * @author tcolburn
 */
public class ArrayImplementation extends AbstractMatrix {
    
    /**
     * Creates an array representation of a matrix of integers.
     * Elements of the array are initialized to zero.
     * @param numRows the number of rows in the matrix
     * @param numColumns the number of columns in the matrix
     * @throws MatrixException if dimensions are not positive
     */
    public ArrayImplementation (int numRows, int numColumns) {
        super.setNumRows(numRows);
        super.setNumColumns(numColumns);
        elements = new int[numRows][numColumns];
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
        return elements[row][column]; 
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
        elements[row][column] = element; 
    }
    
    private int[][] elements;
    
}