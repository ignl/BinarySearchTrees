package org.intelligentjava.algos.trees;

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
public class TreapTest {

    // TODO check if after rotation other Nodes can destroy heap property (it shouldn't i think)
    @Test
    public void testInsert() {
        Treap treap = new Treap() {
            @Override
            protected Node createNode(int value, Node parent, Node left, Node right) {
                return new TreapNode(value, parent, left, right, Integer.MAX_VALUE);
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
        
        treap.printTreeInOrder(); // should be in order
    }
    
    @Test
    public void testDelete() {
        
    }

}
