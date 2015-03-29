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
    public void testInsert() {
        ScapegoatTree tree = new ScapegoatTree();
        tree.insert(23);
        tree.insert(13);
        tree.insert(56);
        tree.insert(1);
        tree.insert(34);
        tree.insert(56);
        tree.insert(33);
        tree.insert(21);
        tree.insert(65);
        tree.insert(45);
        tree.insert(76);
        tree.insert(99);
        tree.insert(43);
        tree.insert(46);
        tree.insert(77);
        tree.insert(88);
        
        Assert.assertEquals(16, tree.size);
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

}
