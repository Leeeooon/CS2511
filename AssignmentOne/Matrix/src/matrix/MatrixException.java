package matrix;

/**                                                                                                                                                                                           
 * A class for handling matrix exceptions, including row or column indices                                                                                                                    
 * that are out of bounds, attempting to add or multiply matrices of incorrect                                                                                                                
 * dimensions, and other situations.                                                                                                                                                          
 * @author tcolburn                                                                                                                                                                           
 */
public class MatrixException extends RuntimeException {

    public MatrixException() {}

    /**                                                                                                                                                                                       
     * Creates a matrix exception object.                                                                                                                                                     
     * @param reason the reason for the exception                                                                                                                                             
     */
    public MatrixException(String reason) {
        super(reason);
    }

}