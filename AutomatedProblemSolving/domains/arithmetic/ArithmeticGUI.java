package domains.arithmetic;

import framework.ui.ProblemGUI;

/**
 *
 * @author Diego Brito
 * @course CS 2511
 * @section 007
 */
public class ArithmeticGUI extends ProblemGUI {
    
    public ArithmeticGUI(){
        super(new ArithmeticProblem(), 1000, 850);
    }
}