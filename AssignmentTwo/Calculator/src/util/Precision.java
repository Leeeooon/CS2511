package util;

/**
 * This class provides methods for testing the equality of double values
 * after truncating insignificant digits in order to avoid precision differences
 * that might arise between platforms.
 * @author tcolburn
 */
public class Precision {
    
    /**
     * Tests the equality of two double operands after truncating
     * insignificant digits.
     * @param op1 the first operand
     * @param op2 the second operand
     * @param places the number of decimal places to retain before truncating
     * @return true if the truncated operands are equal, false otherwise
     */
    public static boolean equals(double op1, double op2, int places) {
        return truncate(op1, places) == truncate(op2, places);
    }
    
    /**
     * Tests the equality of two double operands after truncating all 
     * but the 10 most significant digits to the right of the decimal point.
     * @param op1 the first operand
     * @param op2 the second operand
     * @return true if the truncated operands are equal, false otherwise
     */
    public static boolean equals(double op1, double op2) {
        return equals(op1, op2, 10);
    }
    
    private static double truncate (double d, int places) {
        double multiplier = Math.pow(10,places);
        return Math.round(d * multiplier)/(double)multiplier;
    }
    
    public static void main(String[] args) {
	double op1 = 13.449420783078308;
        double op2 = 13.449420783078306;
        System.out.println(op1);
        System.out.println(op2);
        for (int p = 5; p <= 15; p++) {
            System.out.println(truncate(op1, p) + "  " + truncate(op2, p) + "  " + Precision.equals(op1, op2, p));
        }
    }
    
}