package org.intelligentjava.algos.trees.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created Apr 18, 2011
 *
 */
public class HeapUtilsTest {
    
    @Test
    public void testBuildHeap() {
        int[] testData = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] expectedData = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        HeapUtils.buildMaxHeap(testData);
        for (int i = 0; i < testData.length; i++) {
            Assert.assertTrue(testData[i] == expectedData[i]);
        }
    }
    
    @Test
    public void testMaxHeapify() {
        int[] testData = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] expectedData = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        HeapUtils.maxHeapify(testData, 1);
        for (int i = 0; i < testData.length; i++) {
            Assert.assertTrue(testData[i] == expectedData[i]);
        }
    }

}
