package org.intelligentjava.algos.trees.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created May 3, 2011
 *
 */
public class ArrayUtilsTest {
    
    @Test
    public void testSwap() {
        int[] testArr = {1, 2, 4, 3};
        ArrayUtils.swap(testArr, 2, 3);
        Assert.assertEquals(testArr[0], 1);
        Assert.assertEquals(testArr[1], 2);
        Assert.assertEquals(testArr[2], 3);
        Assert.assertEquals(testArr[3], 4);
    }

}
