package org.intelligentjava.algos.trees;

/**
 * Red-Black tree implementation.
 * 
 * @author Ignas Lelys
 * @created May 6, 2011
 * 
 */
public class RedBlackTree extends AbstractSelfBalancingBinarySearchTree {

    protected enum ColorEnum {
        RED,
        BLACK
    };

    private static final Node nilNode = new RedBlackNode(null, null, null, null, ColorEnum.BLACK);

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new RedBlackNode(value, parent, left, right, ColorEnum.RED);
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     */
    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        newNode.left = nilNode;
        newNode.right = nilNode;
        root.parent = nilNode;
        insertRBFixup((RedBlackNode) newNode);
        return newNode;
    }

    /**
     * Restores Red-Black tree properties after insert if needed. Insert can
     * break only 2 properties: root is red or if node is red then children must
     * be black.
     */
    private void insertRBFixup(RedBlackNode currentNode) {
        // current node is always RED, so if its parent is red it breaks
        // Red-Black property, otherwise no fixup needed and loop can terminate
        while (((RedBlackNode) currentNode.parent).color == ColorEnum.RED) {
            RedBlackNode parent = (RedBlackNode) currentNode.parent;
            RedBlackNode grandParent = (RedBlackNode) parent.parent;
            if (parent.equals(grandParent.left)) {
                RedBlackNode uncle = (RedBlackNode) grandParent.right;
                // case1 - uncle and parent are both red
                // re color both of them to black
                if (((RedBlackNode) uncle).color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // grandparent was recolored to red, so in next iteration we
                    // check if it does not break Red-Black property
                    currentNode = grandParent;
                } 
                // case 2/3 uncle is black - then we perform rotations
                else {
                    if (currentNode.equals(parent.right)) { // case 2, first rotate left
                        currentNode = parent;
                        parent = (RedBlackNode) currentNode.parent;
                        grandParent = (RedBlackNode) parent.parent;
                        rotateLeft(currentNode);
                    }
                    // do not use parent
                    parent.color = ColorEnum.BLACK; // case 3
                    grandParent.color = ColorEnum.RED;
                    rotateRight(grandParent);
                }
            } else if (parent.equals(grandParent.right)) {
                RedBlackNode uncle = (RedBlackNode) grandParent.left;
                // case1 - uncle and parent are both red
                // re color both of them to black
                if (((RedBlackNode) uncle).color == ColorEnum.RED) {
                    parent.color = ColorEnum.BLACK;
                    uncle.color = ColorEnum.BLACK;
                    grandParent.color = ColorEnum.RED;
                    // grandparent was recolored to red, so in next iteration we
                    // check if it does not break Red-Black property
                    currentNode = grandParent;
                }
                // case 2/3 uncle is black - then we perform rotations
                else {
                    if (currentNode.equals(parent.left)) { // case 2, first rotate right
                        currentNode = parent;
                        parent = (RedBlackNode) currentNode.parent;
                        grandParent = (RedBlackNode) parent.parent;
                        rotateRight(currentNode);
                    }
                    // do not use parent
                    parent.color = ColorEnum.BLACK; // case 3
                    grandParent.color = ColorEnum.RED;
                    rotateLeft(grandParent);
                }
            }

        }
        // ensure root is black in case it was colored red in fixup
        ((RedBlackNode) root).color = ColorEnum.BLACK;
    }

    protected static class RedBlackNode extends Node {
        public ColorEnum color;

        public RedBlackNode(Integer value, Node parent, Node left, Node right, ColorEnum color) {
            super(value, parent, left, right);
            this.color = color;
        }
    }

}
