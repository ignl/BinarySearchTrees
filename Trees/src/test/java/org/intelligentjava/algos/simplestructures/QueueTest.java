package org.intelligentjava.algos.simplestructures;

import org.intelligentjava.algos.trees.exceptions.QueueOverflowException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created May 4, 2011
 *
 */
public class QueueTest {
    
    @Test
    public void testEnqueueDequeue() throws QueueOverflowException {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Assert.assertEquals(queue.dequeue(), 1);
        Assert.assertEquals(queue.dequeue(), 2);
        Assert.assertEquals(queue.dequeue(), 3);
    }

    @Test
    public void testIsEmpty() throws QueueOverflowException {
        Queue queue = new Queue();
        Assert.assertTrue(queue.isEmpty());
        queue.enqueue(1);
        Assert.assertFalse(queue.isEmpty());
    }
    
    @Test
    public void testIsFull() throws QueueOverflowException {
        Queue queue = new Queue();
        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }
        Assert.assertFalse(queue.isFull());
        queue.enqueue(9);
        Assert.assertTrue(queue.isFull());
        queue.dequeue();
        Assert.assertFalse(queue.isFull());
        queue.enqueue(10);
        Assert.assertTrue(queue.isFull());
    }
    
//    @Test(expectedExceptions = { QueueOverflowException.class })
//    public void testQueueOverflow() throws QueueOverflowException {
//        Queue queue = new Queue();
//        for (int i = 0; i < 11; i++) {
//            queue.enqueue(i);
//        }
//    }

}
