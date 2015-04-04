package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.utils.MathUtils;

/**
 * AVL tree implementation.
 * 
 * In computer science, an AVL tree is a self-balancing binary search tree, and
 * it was the first such data structure to be invented.[1] In an AVL tree, the
 * heights of the two child subtrees of any node differ by at most one. Lookup,
 * insertion, and deletion all take O(log n) time in both the average and worst
 * cases, where n is the number of nodes in the tree prior to the operation.
 * Insertions and deletions may require the tree to be rebalanced by one or more
 * tree rotations.
 * 
 * @author Ignas Lelys
 * @created Jun 28, 2011
 * 
 */
public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     * 
     *      AVL tree insert method also balances tree if needed. Additional
     *      height parameter on node is used to track if one subtree is higher
     *      than other by more than one, if so AVL tree rotations is performed
     *      to regain balance of the tree.
     */
    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AVLNode)newNode);
        return newNode;
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        Node successorNode = super.delete(deleteNode);
        rebalance((AVLNode)deleteNode.parent);
        return successorNode;
    }
    
    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }

    /**
     * Go up from inserted node, and update height and balance informations if needed.
     * If some node balance reaches 2 or -2 that means that subtree must be rebalanced.
     * 
     * @param node Inserted Node.
     */
    private void rebalance(AVLNode node) {
        while (node != null) {
            
            Node parent = node.parent;
            
            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            // rebalance (-2 means left subtree outgrow, 2 means right subtree)
            if (nodeBalance == 2) {
                if (node.right.right != null) {
                    node = (AVLNode)avlRotateLeft(node);
                    break;
                } else {
                    node = (AVLNode)doubleRotateRightLeft(node);
                    break;
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = (AVLNode)avlRotateRight(node);
                    break;
                } else {
                    node = (AVLNode)doubleRotateLeftRight(node);
                    break;
                }
            } else {
                updateHeight(node);
            }
            
            node = (AVLNode)parent;
        }
    }

    /**
     * Rotates to left side.
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);
        
        updateHeight((AVLNode)temp.left);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * Rotates to right side.
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode)temp.right);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    /**
     * Take right child and rotate it to the right side first and then rotate
     * node to the left side.
     */
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    /**
     * Updates height and balance of the node.
     * 
     * @param node Node for which height and balance must be updated.
     */
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        node.height = 1 + MathUtils.getMax(leftHeight, rightHeight);
    }

    /**
     * Node of AVL tree has height and balance additional properties. If balance
     * equals 2 (or -2) that node needs to be re balanced. (Height is height of
     * the subtree starting with this node, and balance is difference between
     * left and right nodes heights).
     * 
     * @author Ignas Lelys
     * @created Jun 30, 2011
     * 
     */
    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

    public void printTree() {
        printSubtree(root);
    }
    
    public void printSubtree(Node node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }
    
    private void printNodeValue(Node node) {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
            System.out.print(" (" + ((AVLNode)node).height + ")");
        }
        System.out.println();
    }
    
    private void printTree(Node node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }
}
