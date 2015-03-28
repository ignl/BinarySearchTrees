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
public class ScapegoatTreeTest {
    
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
