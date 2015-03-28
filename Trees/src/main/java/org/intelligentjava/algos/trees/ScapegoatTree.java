package org.intelligentjava.algos.trees;

import org.intelligentjava.algos.trees.utils.MathUtils;

/**
 * Scapegoat tree non recursive implementation.
 * 
 * @author Ignas Lelys
 * @created Jul 28, 2011
 * 
 */
public class ScapegoatTree extends AbstractSelfBalancingBinarySearchTree {

    private static double alpha = 0.5;

    /**
     * The maximal value of size since the last time the tree was completely
     * rebuilt.
     */
    private int maxSize;

    // TODO change
    private java.util.Stack<Node> nodeSequence = new java.util.Stack<Node>();

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node,
     *      org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     */
    @Override
    public Node insert(int element) {
        nodeSequence.clear(); // TODO change clear for performance
        Node inserted = super.insert(element);
        if (size > maxSize) {
            maxSize = size;
        }
        if (isDeep(nodeSequence.size())) {
            Node scapegoat = findScapegoatNode();
            rebuildTree(scapegoat);
        }
        return inserted;
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#saveBreadCrumbs(org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected void saveBreadCrumbs(Node node) {
        nodeSequence.push(node);
    }

    /**
     * @param scapegoat
     */
    protected void rebuildTree(Node scapegoat) {
        
    }

    /**
     * @param nodes
     * @return
     */
    private Node buildTree(java.util.LinkedList<Node> nodes) {
        return null;
    }
    
    /**
     * Finds scapegoat node which is used for rebalancing the tree.
     * 
     * @return Scapegoat node.
     */
    protected Node findScapegoatNode() {
        Node node;
        try {
            node = nodeSequence.pop();
        } catch (Exception ex) {
            return root;
        }

        int leftSize = getSubtreeSize(node.left);
        int rightSize = getSubtreeSize(node.right);
        int sizeNode = getSubtreeSize(node);
        double coeff = sizeNode * alpha;

        boolean nodeHeightBalanced = leftSize < coeff && rightSize < coeff;
        if (nodeHeightBalanced) {
            return findScapegoatNode();
        } else {
            // our unbalanced scapegoat
            return node;
        }
    }

    /**
     * Calculate size of subtree.
     * 
     * @param node
     *            Subtree root node.
     * @return Number of elements in the subtree.
     */
    protected int getSubtreeSize(Node node) {
        if (node.isLeaf()) {
            return 1;
        }
        int sum = 1;
        if (node.left != null)
            sum += getSubtreeSize(node.left);
        if (node.right != null)
            sum += getSubtreeSize(node.right);

        return sum;
    }

    /**
     * Calculates if depth is considered too deep for alpha value.
     * 
     * @param depth
     *            Depth
     * @return True if node is inserted too deep.
     */
    private boolean isDeep(int depth) {
        double hAlpha = Math.floor(MathUtils.logarithm(1 / alpha, depth));
        return depth > hAlpha;
    }

}