package org.intelligentjava.algos.trees.utils;

/**
 * Some syntactic sugar for math operations.
 * 
 * @author Ignas Lelys
 * @created May 3, 2011
 *
 */
public class MathUtils {
    
    /**
     * Extracts digit from integer number.
     * 
     * @param number Number from which digit is extracted.
     * @param digitIndex index of digit to get. 0 - last one.
     * 
     * @return Required digit.
     */
    public static int getDigitFromNumber(int number, int digitIndex) {
        // TODO error check and efficiency (maybe put powers of 10 to array)
        return number / (int)Math.pow(10, digitIndex) % 10;
    }
    
    /**
     * Returns bigger integer.
     * 
     * @param first First number.
     * @param second Second number.
     * @return Bigger number.
     */
    public static int getMax(int first, int second) {
        return first > second ? first : second;
    }
    
    /**
     * Calculates logarithm.
     */
    public static double logarithm(double base, double a) {
        return Math.log(a) / Math.log(base);
    }

}
