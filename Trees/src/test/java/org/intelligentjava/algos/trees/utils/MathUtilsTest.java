package org.intelligentjava.algos.trees.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created May 3, 2011
 *
 */
public class MathUtilsTest {
    
    @Test
    public void testGetDigitFromNumber() {
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 0), 5);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 1), 4);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 2), 4);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 3), 3);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 4), 3);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 5), 2);
        Assert.assertEquals(MathUtils.getDigitFromNumber(2233445, 6), 2);
    }

}
