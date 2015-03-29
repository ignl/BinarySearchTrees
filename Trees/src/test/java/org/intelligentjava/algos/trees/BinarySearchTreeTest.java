package org.intelligentjava.algos.trees;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for simple unbalanced binary search tree.
 * 
 * @author Ignas Lelys
 * @created May 6, 2011
 *
 */
public class BinarySearchTreeTest extends BaseBSTTest {
    
    @Test
    public void testInsertDelete() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(8);
        Assert.assertTrue(tree.contains(10));
        Assert.assertTrue(tree.contains(16));
        Assert.assertTrue(tree.contains(1));
        Assert.assertFalse(tree.contains(9));
        tree.delete(16);
        tree.delete(1);
        Assert.assertFalse(tree.contains(16));
        Assert.assertFalse(tree.contains(1));
        
        testTreeBSTProperties(tree.root);
    }
    
    @Test
    public void testSize() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        Assert.assertEquals(tree.getSize(), 3);
        tree.delete(16);
        Assert.assertEquals(tree.getSize(), 2);
        tree.delete(16);
        Assert.assertEquals(tree.getSize(), 2);
    }

    @Test
    public void testMinimumMaximum() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(16);
        tree.insert(1);
        tree.insert(8);
        Assert.assertEquals(tree.getMinimum(), 1);
        Assert.assertEquals(tree.getMaximum(), 16);
    }
    
    @Test
    public void testGetSuccessor() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(17);
        tree.insert(20);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        Assert.assertEquals(tree.getSuccessor(15), 17);
        Assert.assertEquals(tree.getSuccessor(13), 15);
    }
}
