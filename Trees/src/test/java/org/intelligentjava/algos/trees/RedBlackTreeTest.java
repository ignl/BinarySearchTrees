package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.RedBlackTree.ColorEnum;
import org.intelligentjava.algos.trees.RedBlackTree.RedBlackNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Red-Black tree.
 * 
 * @author Ignas Lelys
 * @created Jul 18, 2011
 *
 */
public class RedBlackTreeTest {
    
    @Test
    public void testInsert() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(10); // re color 15 and 25 to black on this insert
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(15)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(25)).color, ColorEnum.BLACK);
        tree.insert(17);
        tree.insert(8);
        Assert.assertEquals(((RedBlackNode)tree.search(15)).color, ColorEnum.RED);
        Assert.assertEquals(((RedBlackNode)tree.search(10)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(17)).color, ColorEnum.BLACK);
        Assert.assertEquals(((RedBlackNode)tree.search(8)).color, ColorEnum.RED);
        tree.insert(9); // case 2/3 - rotation right, then left
        Assert.assertEquals(((RedBlackNode)tree.search(10)).color, ColorEnum.RED);
        Assert.assertEquals(((RedBlackNode)tree.search(8)).color, ColorEnum.RED);
        Assert.assertEquals(((RedBlackNode)tree.search(9)).left.value, (Integer)8);
        
        tree.printTreeInOrder();
    }
    
    @Test
    public void testSimpleDelete() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.size, 4);
        tree.delete(15);
        Assert.assertEquals(tree.size, 3);
        Assert.assertEquals(tree.root.value, (Integer)23); // new root
        Assert.assertEquals(tree.root.left.value, (Integer)20);
        Assert.assertEquals(tree.root.right.value, (Integer)25);
    }
    
    @Test
    public void testDelete() {
        RedBlackTree tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(25);
        tree.insert(23);
        tree.insert(27);
        Assert.assertEquals(((RedBlackNode)tree.root).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.size, 5);
        Assert.assertEquals(tree.root.right.value, (Integer)25);
        Assert.assertEquals(tree.root.right.left.value, (Integer)23);
        Assert.assertEquals(((RedBlackNode)tree.root.right.left).color, ColorEnum.RED);
        tree.delete(25);
        Assert.assertEquals(tree.size, 4);
        Assert.assertEquals(tree.root.value, (Integer)20);
        Assert.assertEquals(tree.root.right.value, (Integer)27);
        Assert.assertEquals(((RedBlackNode)tree.root.right).color, ColorEnum.BLACK);
        Assert.assertEquals(tree.root.right.right.value, null);
        Assert.assertEquals(tree.root.right.left.value, (Integer)23);
        Assert.assertEquals(((RedBlackNode)tree.root.right.left).color, ColorEnum.RED);
    }

}
