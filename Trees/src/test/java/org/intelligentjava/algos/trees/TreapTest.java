package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.intelligentjava.algos.trees.Treap.TreapNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Treap tests.
 * 
 * @author Ignas Lelys
 * @created Jul 26, 2011
 * 
 */
public class TreapTest extends BaseBSTTest {

    // TODO check if after rotation other Nodes can destroy heap property (it shouldn't i think)
    @Test
    public void testInsert() {
        Treap treap = new Treap() {
            @Override
            protected Node createNode(int value, Node parent, Node left, Node right) {
                return new TreapNode(value, parent, left, right, Integer.MAX_VALUE); // create max integer priority instead of random
            }
        };
        TreapNode root = new TreapNode(20, null, null, null, 100);
        TreapNode rootLeft = new TreapNode(15, root, null, null, 99);
        TreapNode rootRight = new TreapNode(25, root, null, null, 98);
        root.left = rootLeft;
        root.right = rootRight;
        TreapNode rootLeftLeft = new TreapNode(14, rootLeft, null, null, 97);
        TreapNode rootLeftRight = new TreapNode(17, rootLeft, null, null, 96);
        rootLeft.left = rootLeftLeft;
        rootLeft.right = rootLeftRight;
        treap.root = root;
        treap.insert(16);
        // this should go to root because createNode sets highest priority
        Assert.assertEquals(treap.size, 1); // because only one insert invoked (other nodes were added manually)
        Assert.assertEquals(treap.root.value, (Integer) 16);
        Assert.assertEquals(treap.root.right.value, (Integer) 20);
        Assert.assertEquals(treap.root.right.right.value, (Integer) 25);
        Assert.assertEquals(treap.root.right.left.value, (Integer) 17);
        Assert.assertEquals(treap.root.left.value, (Integer) 15);
        Assert.assertEquals(treap.root.left.left.value, (Integer) 14);
    }
    
    @Test
    public void testDelete() {
        Treap treap = new Treap();
        TreapNode root = new TreapNode(20, null, null, null, 100);
        TreapNode rootLeft = new TreapNode(15, root, null, null, 99);
        TreapNode rootRight = new TreapNode(25, root, null, null, 98);
        root.left = rootLeft;
        root.right = rootRight;
        TreapNode rootLeftLeft = new TreapNode(14, rootLeft, null, null, 97);
        TreapNode rootLeftRight = new TreapNode(17, rootLeft, null, null, 96);
        rootLeft.left = rootLeftLeft;
        rootLeft.right = rootLeftRight;
        treap.root = root;
        Node delete = treap.delete(20);
        Assert.assertEquals(delete.value, (Integer) 15); 
    }
    
    @Test
    public void testHeapPropertyAfterInsertAndDelete() {
        Treap treap = new Treap();
        treap.insert(23);
        treap.insert(13);
        treap.insert(56);
        treap.insert(1);
        treap.insert(34);
        treap.insert(56);
        treap.insert(33);
        treap.insert(21);
        treap.insert(65);
        treap.insert(45);
        treap.insert(76);
        treap.insert(99);
        treap.insert(43);
        treap.insert(46);
        treap.insert(77);
        treap.insert(88);
        treap.insert(102);
        treap.insert(18);
        treap.insert(123);
        treap.insert(256);
        treap.insert(111);
        treap.insert(331);
        treap.insert(2);
        treap.insert(3);
        treap.insert(4);
        
        Assert.assertEquals(25, treap.size);
        testTreeBSTProperties(treap.root);
        testHeapProperties(treap.root);        
        treap.delete(1);
        treap.delete(45);
        treap.delete(43);
        treap.delete(331);
        treap.delete(3);

        Assert.assertEquals(20, treap.size);
        testTreeBSTProperties(treap.root);
        testHeapProperties(treap.root);        
    }
    
    private void testHeapProperties(Node entry) {
        if (entry != null) {
            // test heap properties and BST properties
            if (entry.left != null) {
                Assert.assertTrue(((TreapNode)entry).priority >= ((TreapNode)entry.left).priority);
            }
            if (entry.right != null) {
                Assert.assertTrue(((TreapNode)entry).priority >= ((TreapNode)entry.right).priority);
            }
            testHeapProperties(entry.left);
            testHeapProperties(entry.right);
        }
    }

}
