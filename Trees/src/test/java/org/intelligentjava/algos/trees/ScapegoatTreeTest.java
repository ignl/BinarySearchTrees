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
        Assert.assertEquals((Integer)5, tree.root.value);
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
        Assert.assertEquals((Integer)12, tree.root.value);
        testTreeBSTProperties(tree.root);
    }
    
    
    @Test
    public void testDeleteSortedDesc() {
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
        
        tree.delete(18);
        tree.delete(17);
        tree.delete(15);
        tree.delete(14);
        tree.delete(12);
        tree.delete(10);
        tree.delete(8);
        tree.printTree();
        
        Assert.assertEquals(11, tree.size);
        Assert.assertEquals((Integer)13, tree.root.value);
        testTreeBSTProperties(tree.root);
    }
    
    @Test
    public void testBuildBigTree() {
        ScapegoatTree tree = new ScapegoatTree(0.95);
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        tree.printTree();
        Assert.assertEquals(100, tree.size);
        testTreeBSTProperties(tree.root);
        
        for (int i = 0; i < 50; i++) {
            tree.delete(i);
        }
        tree.printTree();
        Assert.assertEquals(50, tree.size);
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
