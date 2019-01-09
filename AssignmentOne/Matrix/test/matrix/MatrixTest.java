package matrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class tests the Matrix API.
 * @author tcolburn
 */
public class MatrixTest {
    
    public MatrixTest() {
        m3x2 = Matrix.create(3, 2);
        m2x3 = Matrix.create(2, 3);
        m3x3 = Matrix.create(3, 3);
    }

    @Test
    public void testDimensions() {
        assertTrue(m3x2.getNumRows() == 3);
        assertTrue(m3x2.getNumColumns() == 2);
    }

    @Test
    public void testBounds() {
        // these checks should succeed
        for (int r = 0; r < m3x2.getNumRows(); r++) {
            for (int c = 0; c < m3x2.getNumColumns(); c++) {
                m3x2.checkBounds(r, c);
            }
        }
        // Test for exceptions
        tryBadIndex(-1, 2); // bad row
        tryBadIndex(2, -1); // bad column
        tryBadIndex(3, 1);  // bad row
        tryBadIndex(2, 2);  // bad column
    }
    
    private void tryBadIndex(int row, int col) { // row or column should be
        try {                                    // out of bounds
            m3x2.checkBounds(row, col);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
    }
    
    @Test
    public void testGet() {  // m should have all zeroes
        for (int r = 0; r <  m3x2.getNumRows(); r++) 
            for (int c = 0; c < m3x2.getNumColumns(); c++) 
                assertTrue(m3x2.get(r, c) == 0);
        System.out.println(m3x2);
    }
    
    @Test
    public void testSetAndGet() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testClear() {
        m3x2.set(0, 0, 1);   m3x2.set(0, 1, 4);  // 1 4
        m3x2.set(1, 0, 2);   m3x2.set(1, 1, 5);  // 2 5
        m3x2.set(2, 0, 3);   m3x2.set(2, 1, 6);  // 3 6
        
        m3x2.clear();
        
        assertTrue(m3x2.get(0, 0) == 0);  assertTrue(m3x2.get(0, 1) == 0);
        assertTrue(m3x2.get(1, 0) == 0);  assertTrue(m3x2.get(1, 1) == 0);
        assertTrue(m3x2.get(2, 0) == 0);  assertTrue(m3x2.get(2, 1) == 0);
    }
    
    @Test
    public void testFillRowWise() {
        m3x2.fillRowWise();           // 1 2
                                      // 3 4
                                      // 5 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 2);
        assertTrue(m3x2.get(1, 0) == 3);  assertTrue(m3x2.get(1, 1) == 4);
        assertTrue(m3x2.get(2, 0) == 5);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testFillColumnWise() {
        m3x2.fillColumnWise();        // 1 4
                                      // 2 5
                                      // 3 6
        assertTrue(m3x2.get(0, 0) == 1);  assertTrue(m3x2.get(0, 1) == 4);
        assertTrue(m3x2.get(1, 0) == 2);  assertTrue(m3x2.get(1, 1) == 5);
        assertTrue(m3x2.get(2, 0) == 3);  assertTrue(m3x2.get(2, 1) == 6);
    }
    
    @Test
    public void testTranspose() {
        m3x2.fillColumnWise();          // 1 4
                                        // 2 5
                                        // 3 6
                                        
        m2x3 = m3x2.transpose();        // 1 2 3
                                        // 4 5 6
        assertTrue(m2x3.get(0, 0) == 1 && m2x3.get(0, 1) == 2 && m2x3.get(0, 2) == 3);
        assertTrue(m2x3.get(1, 0) == 4 && m2x3.get(1, 1) == 5 && m2x3.get(1, 2) == 6);
    }
    
    @Test
    public void testMakeIdentity() {
        try {                                    
            m3x2.makeIdentity();
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) { }
        
        m3x3.makeIdentity();    // 1 0 0
                                // 0 1 0
                                // 0 0 1
        assertTrue(m3x3.get(0, 0) == 1 && m3x3.get(0, 1) == 0 && m3x3.get(0, 2) == 0);
        assertTrue(m3x3.get(1, 0) == 0 && m3x3.get(1, 1) == 1 && m3x3.get(1, 2) == 0);
        assertTrue(m3x3.get(2, 0) == 0 && m3x3.get(2, 1) == 0 && m3x3.get(2, 2) == 1);
    }
    
    /**
     * This test should test the equality of:
     *   1. A matrix with itself (should be true)
     *   2. Several pairs of matrices of differing dimensions 
     *      (should be false)
     *   3. Two empty matrices of the same dimensions (should be true)
     *   4. Two nonempty matrices of the same dimensions with the same 
     *      values for elements (should be true)
     *   5. Two nonempty matrices of the same dimensions with the same 
     *      values except for one element (should be false)
     *   6. A nonempty matrix with the transpose of the transpose of
     *      itself (should be true)
     */
    @Test
    public void testEquals() {
	Matrix A = m3x3;
        Matrix B = m3x3;
        assertTrue(A.equals(A));
        
        assertFalse(m2x3.equals(m3x2));
        assertFalse(m2x3.equals(m3x3));
        assertFalse(m3x2.equals(m2x3));
        assertFalse(m3x2.equals(m3x3));
        assertFalse(m3x3.equals(m2x3));
        assertFalse(m3x3.equals(m3x2));
        
        A.clear();
        B.clear();
        assertTrue(A.equals(B));
        
        A.fillColumnWise();
        B.fillColumnWise();
        assertTrue(A.equals(B));
        
        //5
        
        
        
        A.fillColumnWise();
        Matrix C = A.transpose();
        assertTrue(C.equals(A.transpose()));
        
        
        
        
    }
    
    /**
     * This test should:
     *   1. Try to add two matrices of different dimensions and catch the
     *      thrown exception
     *   2. Add two empty matrices of the same dimensions and confirm
     *      the result with assertions
     *   3. Add two nonempty matrices of the same dimensions and confirm
     *      the result with assertions
     */
    @Test
    public void testAdd() {
	try {                                    
            m3x2.add(m2x3);
            fail("add should not have succeeded");
        }
        catch(MatrixException ex) { }
        
        Matrix A=m3x3;
        Matrix B=m3x3;
        
        //2
        A.clear();
        B.clear();
        Matrix C=A.add(B);
        
        assertTrue(C.get(0, 0) == 0 && C.get(0, 1) == 0 && C.get(0, 2) == 0);
        assertTrue(C.get(1, 0) == 0 && C.get(1, 1) == 0 && C.get(1, 2) == 0);
        assertTrue(C.get(2, 0) == 0 && C.get(2, 1) == 0 && C.get(2, 2) == 0);
        
        //3
        A.fillRowWise();
        B.fillRowWise();
        assertTrue(C.get(0, 0) == 2 && C.get(0, 1) == 4 && C.get(0, 2) == 6);
        assertTrue(C.get(1, 0) == 8 && C.get(1, 1) == 10 && C.get(1, 2) == 12);
        assertTrue(C.get(2, 0) == 14 && C.get(2, 1) == 16 && C.get(2, 2) == 18);
        
        
    }
    
    /**
     * This test should:
     *   1. Try to multiply several pairs of incompatible matrices and catch the
     *      thrown exceptions
     *   2. Multiply two nonempty compatible matrices and confirm
     *      the result with assertions
     *   3. Multiply a nonempty square matrix by the identity matrix of the same
     *      dimensions and confirm that the result is the original matrix
     */
    @Test
    public void testMultiply() {
	// 1
        try {                                    
            m3x2.multiply(m3x3);
            fail("multiply should not have succeeded");
            m3x2.multiply(m3x2);
            fail("multiply should not have succeeded");
            m2x3.multiply(m2x3);
            fail("multiply should not have succeeded");
            m3x3.multiply(m2x3);
            fail("multiply should not have succeeded");
        }
        catch(MatrixException ex) { }
        
       
        //2
        Matrix A =(m2x3);
        Matrix B = (m3x2);
        A.fillRowWise();
        B.fillRowWise();
        Matrix C = A.multiply(B);
        assertTrue(C.get(0, 0) == 14 && C.get(0, 1) == 32);
        assertTrue(C.get(1, 0) == 32 && C.get(1, 1) == 77);
        
        //3
        Matrix D = m3x3;
        Matrix E = m3x3;
        D.fillRowWise();
        E.makeIdentity();
        Matrix F = D.multiply(E);
        assertTrue(F.get(0, 0) == 1 && F.get(0, 1) == 2 && F.get(0, 2) == 3);
        assertTrue(F.get(1, 0) == 4 && F.get(1, 1) == 5 && F.get(1, 2) == 6);
        assertTrue(F.get(2, 0) == 7 && F.get(2, 1) == 8 && F.get(2, 2) == 9);
        
        
        
    }
    
    private final Matrix m3x2;
    private Matrix m2x3;
    private Matrix m3x3;
}
