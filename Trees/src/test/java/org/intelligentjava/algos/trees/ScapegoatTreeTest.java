package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Scapegoat tree implementation tests.
 * 
 * @author Ignas Lelys
 * @created Jul 28, 2011
 *
 */
public class ScapegoatTreeTest extends BaseBSTTest {
    
    
    @Test
    public void testInsertSorted() {
        ScapegoatTree tree = new ScapegoatTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(18);
        
        Assert.assertEquals(18, tree.size);
        Assert.assertEquals((Integer)8, tree.root.value);
        testTreeBSTProperties(tree.root);
    }

    @Test
    public void testInsertSortedDesc() {
        ScapegoatTree tree = new ScapegoatTree();
        tree.insert(18);
        tree.insert(17);
        tree.insert(16);
        tree.insert(15);
        tree.insert(14);
        tree.insert(13);
        tree.insert(12);
        tree.insert(11);
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        tree.insert(7);
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.printTree();
        
        Assert.assertEquals(18, tree.size);
        Assert.assertEquals((Integer)11, tree.root.value);
        testTreeBSTProperties(tree.root);
    }
    
    @Test
    public void testGetSubtreeSize() {
        Node root = new Node(4, null, null, null);
        Node rightChild = new Node(6, root, null, null);
        root.right = rightChild;
        Node leftGrandChild = new Node(5, rightChild, null, null);
        Node rightGrandChild = new Node(7, rightChild, null, null);
        rightChild.left = leftGrandChild;
        rightChild.right = rightGrandChild;
        ScapegoatTree tree = new ScapegoatTree();
        Assert.assertEquals(tree.getSubtreeSize(root), 4);
    }
    
    @Test
    public void testFindScapegoat() {
        // TODO
    }
    
    @Test
    public void testRebuildTree() {
        // TODO
    }

}
