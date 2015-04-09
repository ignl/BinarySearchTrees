package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.AVLTree.AVLNode;
import org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for AVL tree.
 * 
 * @author Ignas Lelys
 * @created Jun 29, 2011
 *
 */
public class AVLTreeTest extends BaseBSTTest {
    
    @Test
    public void testDelete() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        Assert.assertEquals(tree.size, 4);
        tree.delete(15); // root is now unbalanced rotation performed
        Assert.assertEquals(tree.size, 3);
        Assert.assertEquals(tree.root.value, (Integer)23); // new root
        Assert.assertEquals(((AVLNode)tree.root).height, 1); // new root
        Assert.assertEquals(tree.root.left.value, (Integer)20);
        Assert.assertEquals(tree.root.right.value, (Integer)25);
        
        testTreeBSTProperties(tree.root);
    }
    
    @Test
    public void testInsert() {
        AVLTree tree = new AVLTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(22);
        tree.insert(21);
        Assert.assertEquals(tree.size, 5);
        Assert.assertEquals((int) tree.root.value, 20);
        Assert.assertEquals((int) tree.root.left.value, 15);
        AVLNode rightSubtree = (AVLNode) tree.root.right;
        // rotation performed and height+balance updated
        Assert.assertEquals((int) rightSubtree.value, 22);
        Assert.assertEquals((int) rightSubtree.height, 1);
        Assert.assertEquals((int) rightSubtree.right.value, 25);
        Assert.assertEquals(((AVLNode) rightSubtree.right).height, 0);
        Assert.assertEquals((int) rightSubtree.left.value, 21);
        Assert.assertEquals(((AVLNode) rightSubtree.left).height, 0);
        
        testTreeBSTProperties(tree.root);
    }

    @Test
    public void testRotateLeft() {
        Node root = new AVLNode(4, null, null, null);
        Node rightChild = new AVLNode(6, root, null, null);
        root.right = rightChild;
        Node leftGrandChild = new AVLNode(5, rightChild, null, null);
        Node rightGrandChild = new AVLNode(7, rightChild, null, null);
        rightChild.left = leftGrandChild;
        rightChild.right = rightGrandChild;

        AVLTree tree = new AVLTree();
        Node rotated = tree.rotateLeft(root);

        Assert.assertEquals((int) rotated.value, 6);
        Assert.assertEquals((int) rotated.left.value, 4);
        Assert.assertEquals((int) rotated.right.value, 7);
        Assert.assertEquals((int) rotated.left.right.value, 5);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.parent.value, rotated.value);
        Assert.assertEquals(rotated.left.right.parent.value, rotated.left.value);
    }

    @Test
    public void testRotateRight() {
        Node root = new AVLNode(8, null, null, null);
        Node leftChild = new AVLNode(6, root, null, null);
        root.left = leftChild;
        Node leftGrandChild = new AVLNode(5, leftChild, null, null);
        Node rightGrandChild = new AVLNode(7, leftChild, null, null);
        leftChild.left = leftGrandChild;
        leftChild.right = rightGrandChild;

        AVLTree tree = new AVLTree();
        Node rotated = tree.rotateRight(root);

        Assert.assertEquals((int) rotated.value, 6);
        Assert.assertEquals((int) rotated.left.value, 5);
        Assert.assertEquals((int) rotated.right.value, 8);
        Assert.assertEquals((int) rotated.right.left.value, 7);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.left.parent.value, rotated.right.value);
    }

    @Test
    public void testDoubleRotateRightLeft() {
        Node root = new AVLNode(5, null, null, null);
        Node rightChild = new AVLNode(8, root, null, null);
        root.right = rightChild;
        Node leftGrandChild = new AVLNode(7, rightChild, null, null);
        Node rightGrandChild = new AVLNode(10, rightChild, null, null);
        rightChild.left = leftGrandChild;
        rightChild.right = rightGrandChild;

        AVLTree tree = new AVLTree();
        Node rotated = tree.doubleRotateRightLeft(root);

        Assert.assertEquals((int) rotated.value, 7);
        Assert.assertEquals((int) rotated.left.value, 5);
        Assert.assertEquals((int) rotated.right.value, 8);
        Assert.assertEquals((int) rotated.right.right.value, 10);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.right.parent.value, rotated.right.value);
    }

    @Test
    public void testDoubleRotateLeftRight() {
        Node root = new AVLNode(5, null, null, null);
        Node leftChild = new AVLNode(3, root, null, null);
        root.left = leftChild;
        Node leftGrandChild = new AVLNode(1, leftChild, null, null);
        Node rightGrandChild = new AVLNode(4, leftChild, null, null);
        leftChild.left = leftGrandChild;
        leftChild.right = rightGrandChild;

        AVLTree tree = new AVLTree();
        Node rotated = tree.doubleRotateLeftRight(root);

        Assert.assertEquals((int) rotated.value, 4);
        Assert.assertEquals((int) rotated.left.value, 3);
        Assert.assertEquals((int) rotated.right.value, 5);
        Assert.assertEquals((int) rotated.left.left.value, 1);

        Assert.assertNull(rotated.parent);
        Assert.assertEquals(rotated.left.parent.value, rotated.value);
        Assert.assertEquals(rotated.right.parent.value, rotated.value);
        Assert.assertEquals(rotated.left.left.parent.value, rotated.left.value);
    }
}
