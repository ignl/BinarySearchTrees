package org.intelligentjava.algos.trees;

import java.util.Random;

/**
 * Treap is randomized binary search tree. Easiest way to randomize would be to get all elements in array and then after
 * random permutation insert them all. However that would require to know all elements in advance. Treap solves this by
 * introducing additional variable to its node - priority which is generated randomly.
 * 
 * @author Ignas Lelys
 * @created Jul 25, 2011
 * 
 */
public class Treap extends AbstractSelfBalancingBinarySearchTree {

    private Random random = new Random(System.currentTimeMillis());

    /**
     * Insert same as normal binary search tree first, just TreapNode will have random number - priority. Then performs
     * rotations up until root if priority of child is larger than priority of parent.
     * 
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     */
    @Override
    public Node insert(int element) {
        // insert as in normal BST
        TreapNode insertedNode = (TreapNode) super.insert(element);
        // then rebalance by randomized priority
        while (insertedNode != root) {
            TreapNode parent = (TreapNode) insertedNode.parent;
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
     * {@inheritDoc}
     */
    @Override
    protected Node delete(Node deleteNode) {
        if (deleteNode != null) {
            // rotate node down to leaf
            Node replaceNode = rotateDown((TreapNode)deleteNode);
            // then delete it normally
            super.delete(deleteNode);
            return replaceNode;
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new TreapNode(value, parent, left, right, random.nextInt(10000));
    }

    /**
     * Rotates a node downwards until it becomes a leaf. It must be deleted later to keep heap property intact.
     * 
     * @param node The node which is moved to leaf.
     * 
     * @return Node that replaces the one which is moved downwards to leaf.
     */
    private Node rotateDown(TreapNode node) {
        Node replaceNode = null;
        while (true) {
            if (node.left != null) {
                boolean leftNodePriorityLarger = node.right == null || ((TreapNode) node.left).priority >= ((TreapNode) node.right).priority;
                if (leftNodePriorityLarger) {
                    Node replace = rotateRight(node);
                    if (replaceNode == null) {
                        replaceNode = replace;
                    }
                } else {
                    Node replace = rotateLeft(node);
                    if (replaceNode == null) {
                        replaceNode = replace;
                    }
                }
            } else if (node.right != null) {
                Node replace = rotateLeft(node);
                if (replaceNode == null) {
                    replaceNode = replace;
                }
            } else {
                break;
            }
        }
        return replaceNode;
    }

    /**
     * Node for Treap. It has additional priority value which is set randomly. It is used for tree randomization.
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
