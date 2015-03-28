package org.intelligentjava.algos.simplestructures;

import org.intelligentjava.algos.trees.exceptions.QueueOverflowException;

/**
 * Simple queue.
 * 
 * @author Ignas Lelys
 * @created May 4, 2011
 * 
 */
public class Queue {

    private Object[] queue;

    private int head = 0;

    private int tail = 0;

    public Queue() {
        super();
        queue = new Object[10];
    }

    public Queue(int queueSize) {
        super();
        queue = new Object[queueSize];
    }

    public void enqueue(Object element) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }
        queue[tail] = element;
        if (tail == queue.length - 1) {
            tail = 0;
        } else {
            tail++;
        }
    }

    public Object dequeue() {
        Object element = queue[head];
        if (head == queue.length - 1) {
            head = 0;
        } else {
            head++;
        }
        return element;
    }

    public boolean isEmpty() {
        return head == tail;
    }
    
    public boolean isFull() {
        int nextTailValue = tail;
        if (nextTailValue == queue.length - 1) {
            nextTailValue = 0;
        } else {
            nextTailValue++;
        }
        if (head == nextTailValue) {
            return true;
        } else {
            return false;
        }
    }
}
