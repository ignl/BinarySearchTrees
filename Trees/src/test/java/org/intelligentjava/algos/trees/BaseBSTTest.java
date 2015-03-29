package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.junit.Assert;

/**
 * Superclass that contains some basic BST tests.
 * 
 * @author Ignas
 *
 */
public class BaseBSTTest {
    
    protected void testTreeBSTProperties(Node entry) {
        if (entry != null) {
            // test heap properties and BST properties
            if (entry.left != null) {
                Assert.assertTrue(entry.value >= entry.left.value);
            }
            if (entry.right != null) {
                Assert.assertTrue(entry.value <= entry.right.value);
            }
            testTreeBSTProperties(entry.left);
            testTreeBSTProperties(entry.right);
        }
    }

}
