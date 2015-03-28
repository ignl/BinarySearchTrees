package org.intelligentjava.algos.simplestructures;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created May 4, 2011
 * 
 */
public class StackTest {

    @Test
    public void testPushPop() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 3);
        Assert.assertEquals(stack.pop(), 2);
        Assert.assertEquals(stack.pop(), 1);
    }

    @Test
    public void testIsEmpty() {
        Stack stack = new Stack();
        Assert.assertTrue(stack.isEmpty());
        stack.push(1);
        Assert.assertFalse(stack.isEmpty());
    }

    /**
     * Inserts more than 10 elements to stack. (Tests if inner array grows
     * correctly because starting size for it is 10 elements).
     */
    @Test
    public void testMoreThan10() {
        Stack stack = new Stack();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            Assert.assertEquals(stack.pop(), i);
        }
    }

}
