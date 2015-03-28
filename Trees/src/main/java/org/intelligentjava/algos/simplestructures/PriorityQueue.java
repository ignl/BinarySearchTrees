package org.intelligentjava.algos.simplestructures;

import org.intelligentjava.algos.trees.utils.ArrayUtils;
import org.intelligentjava.algos.trees.utils.HeapUtils;

/**
 * Priority queue implementation.
 * 
 * @author Ignas Lelys
 * @created Apr 18, 2011
 * 
 */
public class PriorityQueue {

    private int[] heap;

    private int heapSize = 0;

    public PriorityQueue() {
        heap = new int[100];
    }

    public void insertElement(int element) {
        heapSize++;
        heap[heapSize - 1] = Integer.MIN_VALUE;
        changeElementValue(heapSize - 1, element);
    }

    public void changeElementValue(int index, int newValue) {
        if (heap[index] > newValue) {
            throw new RuntimeException("New value is smaller than old one.");
        }
        heap[index] = newValue;
        while (index > 0 && heap[HeapUtils.getParent(index)] < heap[index]) {
            ArrayUtils.swap(heap, index, HeapUtils.getParent(index));
            index = HeapUtils.getParent(index);
        }
    }

    public int getElement() {
        return heap[0];
    }

    public int removeElement() {
        if (heapSize < 1) {
            throw new RuntimeException();
        }
        int maxElement = heap[0];
        heap[0] = heap[heapSize - 1];
        heapSize--;
        HeapUtils.maxHeapify(heap, 0, heapSize);
        return maxElement;
    }

}
