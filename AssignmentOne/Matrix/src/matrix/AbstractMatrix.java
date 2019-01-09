package matrix;

import static matrix.Matrix.create;

/**
 * This abstract class partially implements the Matrix API.
 * Only the number of rows and columns are stored. A subclass
 * should create the data structure used to store matrix elements.
 *
 * @author tcolburn
 */
public abstract class AbstractMatrix implements Matrix {

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @Override
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Sets the number of rows in this matrix.
     * @param numRows the number of rows in this matrix
     * @throws MatrixException if numRows is not positive
     */
    @Override
    public void setNumRows(int numRows) {
        if (numRows <= 0) {
            throw new MatrixException(String.format("numRows (%s) must be positive", numRows));
        }
        this.numRows = numRows;
    }

    /**
     * Sets the number of columns in this matrix.
     * @param numColumns the number of columns in this matrix
     * @throws MatrixException if numColumns is not positive
     */
    @Override
    public void setNumColumns(int numColumns) {
        if (numColumns <= 0) {
            throw new MatrixException(String.format("numColumns (%s) must be positive", numColumns));
        }
        this.numColumns = numColumns;
    }

    /**
     * Creates a visual representation of this matrix as a string. This method
     * can be used for debugging. This is a template method; it uses a method
     * (get) that must be implemented by a subclass. This method overrides a
     * method in the Object class.
     *
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                builder.append(String.format("%6s", get(r, c)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Tests for equality of this matrix with another. Matrices are equal if
     * they have the same dimensions and all elements are equal by ==. This is a
     * template method; it uses a method (get) that must be implemented by a
     * subclass. This method overrides a method in the Object class, so it must
     * type check and cast its argument to the correct type.
     *
     * @param obj the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix,
     * <b>false</b> otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Matrix){
        
        Matrix other = (Matrix)obj;
        
        if((other.getNumRows() != getNumRows())||(other.getNumColumns() != getNumColumns()))
        //throw new MatrixException(String.format("Matrix does not have equal dimensions")); 
            return false;
         for (int r = 0; r < getNumRows(); r++) 
         {
            for (int c = 0; c < getNumColumns(); c++)
            {
            if ((this.get(r, c))!=(other.get(r,c)))
                    return false;
            }
         }
        return true;
        }
        else //throw new MatrixException(String.format("Object was of wrong type"));
            return false;
        }

    /**
     * Adds this matrix to another.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    @Override
    public Matrix add(Matrix other) {
        Matrix A = this;
        
        if((A.getNumRows() != other.getNumRows())||(A.getNumColumns() != other.getNumColumns())){
        throw new MatrixException(String.format("Matrix does not have equal dimensions"));
        }
        
        Matrix C = create(getNumRows(), getNumColumns());
         
        for (int r = 0; r < getNumColumns(); r++) 
        {
            for (int c = 0; c < getNumRows(); c++)
            {
            C.set(r,c,addMatrixValue(this.get(r,c), other.get(r,c)));
            }
            
        }   
            return C;
    }
    
     private static int addMatrixValue(int m1Int, int m2Int) {
        return m1Int + m2Int;
    }

    /**
     * Multiplies this matrix by another.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        Matrix A = this;
        
        if((A.getNumColumns() != other.getNumRows())){
        throw new MatrixException(String.format("Matrix does not have equal dimensions"));
        }
        
        Matrix C = create(getNumRows(), other.getNumColumns());
        
        int m1Value;
        int m2Value;
        int productValue = 0;
        
        for (int r = 0; r < this.getNumRows(); r++) 
        {
            for (int c = 0; c < other.getNumColumns(); c++)
            {
                productValue = 0;
                
                for (int k=0; k< this.getNumColumns();k++)
                { 
                    m1Value = this.get(r, k);
                    m2Value = other.get(k, c);
                    productValue += m1Value * m2Value;
                }
                C.set(r,c,productValue);
                
            }
            
        }   
            return C;
        
        
    }
    
    /**
     * Private instance fields follow
     */

    private int numRows;
    private int numColumns;

}