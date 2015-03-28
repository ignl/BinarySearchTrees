package org.intelligentjava.algos.trees.utils;

/**
 * Helper class which helps to work with arrays.
 * 
 * @author Ignas Lelys
 * @created Apr 18, 2011
 * 
 */
public class ArrayUtils {

    /**
     * Swaps elements in array.
     * 
     * @param data
     *            Array of elements.
     * @param elementIndex1
     *            Index of element which value will be moved to elementIndex2.
     * @param elementIndex2
     *            Index of element which value will be moved to elementIndex1.
     */
    public static void swap(int[] data, int elementIndex1, int elementIndex2) {
        int tmp = data[elementIndex1];
        data[elementIndex1] = data[elementIndex2];
        data[elementIndex2] = tmp;
    }

    /**
     * Select nth smallest element in array. It could be easily solved by
     * sorting array and select nth element, but this algorithm is more
     * efficient. Expected running time is O(n). However worst case scenario
     * could be O(n^2).
     * 
     * @param data Data array.
     * @param n N parameter.
     * @return Value of nth smallest element.
     */
    public static int selectNthSmallestElement(int[] data, int n) {
        return selectNthSmallestElement(data, 0, data.length - 1, n);
    }

    /**
     * Select nth smallest element in array from sublist.
     * 
     * @param @param data Data array.
     * @param sublistStartIndex Sublist start index.
     * @param sublistEndIndex Sublist end index.
     * @param n N parameter.
     * @return Value of nth smallest element in sublist.
     */
    public static int selectNthSmallestElement(int[] data, int sublistStartIndex, int sublistEndIndex, int n) {
        if (sublistStartIndex == sublistEndIndex) {
            return data[sublistStartIndex];
        }
        int pivotIndex = partition(data, sublistStartIndex, sublistEndIndex);
        int k = sublistStartIndex - pivotIndex + 1;
        if (n == k) {
            return data[pivotIndex];
        } else if (n < k) {
            return selectNthSmallestElement(data, sublistStartIndex, pivotIndex - 1, n);
        } else {
            return selectNthSmallestElement(data, pivotIndex, sublistEndIndex, n - k);
        }
        
    }

    /**
     * Partition algorithm.
     * 
     * @param @param data Data array.
     * @param sublistStartIndex Sublist start index.
     * @param sublistEndIndex Sublist end index.
     * @return Pivot index.
     */
    private static int partition(int[] data, int sublistStartIndex, int sublistEndIndex) {
        int pivotElement = data[sublistEndIndex];
        int pivotIndex = sublistStartIndex - 1;
        for (int i = sublistStartIndex; i < sublistEndIndex; i++) {
            if (data[i] <= pivotElement) {
                pivotIndex++;
                ArrayUtils.swap(data, pivotIndex, i);
            }
        }
        ArrayUtils.swap(data, pivotIndex + 1, sublistEndIndex);
        return pivotIndex + 1;
    }
    
}
