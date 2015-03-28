package org.intelligentjava.algos.simplestructures;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ignas Lelys
 * @created May 5, 2011
 *
 */
public class LinkedListTest {
    
    @Test
    public void testInsertDelete() {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        Assert.assertEquals(list.get(0), 5);
        Assert.assertEquals(list.get(1), 4);
        Assert.assertEquals(list.get(2), 3);
        Assert.assertEquals(list.get(3), 2);
        Assert.assertEquals(list.get(4), 1);
        list.delete(5);
        Assert.assertEquals(list.get(0), 4);
        Assert.assertEquals(list.get(1), 3);
        Assert.assertEquals(list.get(2), 2);
        Assert.assertEquals(list.get(3), 1);
        list.delete(1);
        Assert.assertEquals(list.get(0), 4);
        Assert.assertEquals(list.get(1), 3);
        Assert.assertEquals(list.get(2), 2);
        list.delete(3);
        Assert.assertEquals(list.get(0), 4);
        Assert.assertEquals(list.get(1), 2);
        
    }

}
