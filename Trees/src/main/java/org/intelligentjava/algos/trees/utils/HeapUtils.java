package org.intelligentjava.algos.trees.utils;

/**
 * Utils for working with heap data structure. Heap is a tree like data
 * structure, which has root element always bigger than its children.
 * Example of a heap:
 *              100
 *               /\
 *             90  23
 *             /\  /
 *           45  1 15
 * Heap is used in heap sort and priority queues implementations.
 */
public class HeapUtils {
    
    /**
     * @param data
     * @param index
     */
    public static void buildMaxHeap(int[] data) {
        for (int i = data.length/2; i >= 0; i--) {
            maxHeapify(data, i);
        }
    }
    
    /**
     * This method takes array and index of root element (of heap subtree). It
     * assumes that this element might not
     * 
     * @param data
     * @param index
     */
    public static void maxHeapify(int[] data, int index) {
        maxHeapify(data, index, data.length);
    }
    
    /**
     * @param data
     * @param index
     * @param heapSize
     */
    public static void maxHeapify(int[] data, int index, int heapSize) {
        int leftLeaf = getLeftLeaf(index);
        int rightLeaf = getRightLeaf(index);
        int largest = index;
        if (leftLeaf < heapSize && data[leftLeaf] > data[largest]) {
            largest = leftLeaf;
        }
        if (rightLeaf < heapSize && data[rightLeaf] > data[largest]) {
            largest = rightLeaf;
        }
        if (largest != index) {
            ArrayUtils.swap(data, index, largest);
            HeapUtils.maxHeapify(data, largest, heapSize);
        }
    }

    public static int getParent(int leafIndex) {
        return leafIndex / 2;
    }
    
    /**
     * Calculates index of left child node.
     * 
     * @param parentIndex
     *            Array index that represents parent node.
     * @return Left child node index in array.
     */
    private static int getLeftLeaf(int parentIndex) {
        return 2 * parentIndex+1;
    }

    /**
     * Calculates index of right child node.
     * 
     * @param parentIndex
     *            Array index that represents parent node.
     * @return Right child node index in array.
     */
    private static int getRightLeaf(int parentIndex) {
        return 2 * parentIndex + 2;
    }
}
