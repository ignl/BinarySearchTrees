package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Splay tree unit tests.
 * 
 * @author Ignas Lelys
 * @created Jul 19, 2011
 *
 */
public class SplayTreeTest extends BaseBSTTest {
    
    @Test
    public void testSearch() {
        SplayTree splayTree = new SplayTree();
        splayTree.insert(24);
        splayTree.insert(20);
        splayTree.insert(30);
        splayTree.insert(39);
        splayTree.insert(80);
        splayTree.insert(11);
        splayTree.search(24);
        Assert.assertEquals(splayTree.root.value, (Integer)24);
        Assert.assertEquals(splayTree.root.left.value, (Integer)11);
        Assert.assertEquals(splayTree.root.left.right.value, (Integer)20);
        Assert.assertEquals(splayTree.root.right.value, (Integer)80);
        Assert.assertEquals(splayTree.root.right.left.value, (Integer)30);
        Assert.assertEquals(splayTree.root.right.left.right.value, (Integer)39);
    }
    
    @Test
    public void testInsert() {
        SplayTree splayTree = new SplayTree();
        splayTree.insert(24);
        splayTree.insert(20);
        splayTree.insert(30);
        splayTree.insert(39);
        splayTree.insert(80);
        splayTree.insert(11);
        Assert.assertEquals(splayTree.root.value, (Integer) 11);
        Assert.assertEquals(splayTree.root.right.value, (Integer) 80);
        Assert.assertNull(splayTree.root.right.right);
        Assert.assertEquals(splayTree.root.right.left.value, (Integer) 30);
        Assert.assertEquals(splayTree.root.right.left.right.value, (Integer) 39);
        Assert.assertEquals(splayTree.root.right.left.left.value, (Integer) 20);
        Assert.assertEquals(splayTree.root.right.left.left.right.value, (Integer) 24);
        
        testTreeBSTProperties(splayTree.root);
    }
    
    @Test
    public void testDelete() {
        SplayTree splayTree = new SplayTree();
        splayTree.insert(22);
        splayTree.insert(11);
        splayTree.insert(10);
        splayTree.insert(16);
        splayTree.insert(15);
        splayTree.insert(14);
        // test insert
        Assert.assertEquals(splayTree.root.value, (Integer)14);
        Assert.assertEquals(splayTree.root.left.value, (Integer)11);
        Assert.assertEquals(splayTree.root.left.left.value, (Integer)10);
        Assert.assertEquals(splayTree.root.right.value, (Integer)15);
        Assert.assertEquals(splayTree.root.right.right.value, (Integer)16);
        Assert.assertEquals(splayTree.root.right.right.right.value, (Integer)22);
        splayTree.delete(16);
        Assert.assertEquals(splayTree.root.value, (Integer)15);
        Assert.assertEquals(splayTree.root.right.value, (Integer)22);
        Assert.assertEquals(splayTree.root.left.value, (Integer)14);
        Assert.assertEquals(splayTree.root.left.left.value, (Integer)11);
        Assert.assertEquals(splayTree.root.left.left.left.value, (Integer)10);
        splayTree.delete(22);
        Assert.assertEquals(splayTree.root.value, (Integer)15);
        Assert.assertNull(splayTree.root.right);
        Assert.assertEquals(splayTree.root.left.value, (Integer)14);
        Assert.assertEquals(splayTree.root.left.left.value, (Integer)11);
        Assert.assertEquals(splayTree.root.left.left.left.value, (Integer)10);
        splayTree.delete(15);
        Assert.assertEquals(splayTree.root.value, (Integer)14);
        Assert.assertNull(splayTree.root.right);
        Assert.assertEquals(splayTree.root.left.value, (Integer)11);
        Assert.assertEquals(splayTree.root.left.left.value, (Integer)10);
        
        testTreeBSTProperties(splayTree.root);
    }

    @Test
    public void testSplayZig() {
        SplayTree splayTree = new SplayTree();
        Node root = new Node(15, null, null, null);
        splayTree.root = root;
        Node left = new Node(10, root, null, null);
        root.left = left;
        splayTree.splay(left);
        Assert.assertEquals(splayTree.root.value, (Integer) 10);
        Assert.assertEquals(splayTree.root.right.value, (Integer) 15);
        splayTree.splay(splayTree.root.right);
        Assert.assertEquals(splayTree.root.value, (Integer) 15);
        Assert.assertEquals(splayTree.root.left.value, (Integer) 10);
    }

    @Test
    public void testSplayZigZig() {
        SplayTree splayTree = new SplayTree();
        Node root = new Node(15, null, null, null);
        splayTree.root = root;
        Node left = new Node(10, root, null, null);
        Node leftleft = new Node(5, left, null, null);
        left.left = leftleft;
        root.left = left;
        splayTree.splay(leftleft);
        Assert.assertEquals(splayTree.root.value, (Integer) 5);
        Assert.assertEquals(splayTree.root.right.value, (Integer) 10);
        Assert.assertEquals(splayTree.root.right.right.value, (Integer) 15);
        splayTree.splay(splayTree.root.right.right);
        Assert.assertEquals(splayTree.root.value, (Integer) 15);
        Assert.assertEquals(splayTree.root.left.value, (Integer) 10);
        Assert.assertEquals(splayTree.root.left.left.value, (Integer) 5);
    }

    @Test
    public void testSplayZigZag() {
        SplayTree splayTree = new SplayTree();
        Node root = new Node(15, null, null, null);
        splayTree.root = root;
        Node left = new Node(10, root, null, null);
        Node leftright = new Node(12, left, null, null);
        left.right = leftright;
        root.left = left;
        // zig zag left-right
        splayTree.splay(leftright);
        Assert.assertEquals(splayTree.root.value, (Integer) 12);
        Assert.assertEquals(splayTree.root.right.value, (Integer) 15);
        Assert.assertEquals(splayTree.root.left.value, (Integer) 10);
        // zig zag from other side (right-left)
        splayTree.root.right.left = new Node(13, splayTree.root.right, null, null);
        splayTree.splay(splayTree.root.right.left);
        Assert.assertEquals(splayTree.root.value, (Integer) 13);
        Assert.assertEquals(splayTree.root.left.value, (Integer) 12);
        Assert.assertEquals(splayTree.root.right.value, (Integer) 15);
    }

}
