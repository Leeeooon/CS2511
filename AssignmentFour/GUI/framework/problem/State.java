package framework.problem;

/**
 * This interface represents the state of affairs of a problem solving
 * domain.  Implementing classes will store the representation details
 * of concrete problem states.
 */
public interface State {
    
    /**                                                                                                 
     * Tests for equality between this state and the argument state.                                    
     * The argument state will need to be cast to a specific class type.                                                    
     * @param other the state to test against this state                                                
     * @return true if this state and the other are equal, false otherwise                                                             
    */
    @Override
    boolean equals(Object other);
    
    /**                                                                                                 
     *Creates a primitive, non-GUI representation of this State object.                        
     *@return the string representation of this state                                       
    */
    @Override
    String toString();

}
