package org.intelligentjava.algos.trees;

import java.util.Random;

/**
 * Treap is randomized binary search tree. Easiest way to randomize would be to
 * get all elements in array and then after random permutation insert them all.
 * However that would require to know all elements in advance. Treap solves this
 * by introducing additional
 * 
 * @author Ignas Lelys
 * @created Jul 25, 2011
 * 
 */
public class Treap extends AbstractSelfBalancingBinarySearchTree {

    private Random random = new Random(System.currentTimeMillis());

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new TreapNode(value, parent, left, right, random.nextInt());
    }

    /**
     * Insert same as normal binary search tree first, just TreapNode will have
     * random number - priority. Then performs rotations up until root if
     * priority of child is larger than priority of parent.
     * 
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     */
    @Override
    public Node insert(int element) {
        TreapNode insertedNode = (TreapNode) super.insert(element);
        while (insertedNode != root) {
            TreapNode parent = (TreapNode)insertedNode.parent;
            if (parent.priority < insertedNode.priority) {
                if (insertedNode.equals(parent.left)) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            } else {
                break;
            }
        }
        return insertedNode;
    }
    
    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
        // TODO Auto-generated method stub
        return super.delete(element);
    }

    /**
     * Node for Treap. It has additional priority value which is set randomly.
     * It is used for tree randomization.
     * 
     * @author Ignas Lelys
     * @created Jul 25, 2011
     */
    protected static class TreapNode extends Node {
        public int priority;

        public TreapNode(int value, Node parent, Node left, Node right, int priority) {
            super(value, parent, left, right);
            this.priority = priority;
        }
    }
}
