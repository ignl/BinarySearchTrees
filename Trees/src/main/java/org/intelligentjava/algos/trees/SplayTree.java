package org.intelligentjava.algos.trees;

/**
 * Splay tree implementation.
 * 
 * @author Ignas Lelys
 * @created Jul 19, 2011
 * 
 */
public class SplayTree extends AbstractSelfBalancingBinarySearchTree {

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#search(int)
     */
    @Override
    public Node search(int element) {
        Node node = super.search(element);
        if (node != null) {
            splay(node);
        }
        return node;
    }
    
    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#insert(int)
     */
    @Override
    public Node insert(int element) {
        Node insertNode = super.insert(element);
        splay(insertNode);
        return insertNode;
    }

    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#delete(int)
     */
    @Override
    public Node delete(int element) {
        // search first, because need parent to splay, might be improved later if needed
        Node deleteNode = super.search(element); // do not use search with splaying
        Node successor = null;
        if (deleteNode != null) {
            Node parent = deleteNode.parent;
            successor = super.delete(deleteNode);
            if (parent != null) {
                splay(parent);
            }
        }
        return successor;
    }
    
    /**
     * @see org.intelligentjava.algos.trees.AbstractBinarySearchTree#createNode(int, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node, org.intelligentjava.algos.trees.AbstractBinarySearchTree.Node)
     */
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    /**
     * Splay operation. Move node to the root through some tree transformations.
     * 
     * @param node
     *            Node to perform splay operation on.
     */
    protected void splay(Node node) {
        // move node up until its root
        while (node != root) {
            // Zig step
            Node parent = node.parent;
            if (parent.equals(root)) {
                if (node.equals(parent.left)) {
                    rotateRight(parent);
                } else if (node.equals(parent.right)) {
                    rotateLeft(parent);
                }
                break;
            } else {
                Node grandParent = parent.parent;
                boolean nodeAndParentLeftChildren = node.equals(parent.left) && parent.equals(grandParent.left);
                boolean nodeAndParentRightChildren = node.equals(parent.right) && parent.equals(grandParent.right);
                boolean nodeRightChildParentLeftChild = node.equals(parent.right) && parent.equals(grandParent.left);
                boolean nodeLeftChildParentRightChild = node.equals(parent.left) && parent.equals(grandParent.right);
                // Zig zig step to the right
                if (nodeAndParentLeftChildren) {
                    rotateRight(grandParent);
                    rotateRight(parent);
                }  
                // Zig zig step to the left
                else if (nodeAndParentRightChildren) {
                    rotateLeft(grandParent);
                    rotateLeft(parent);
                }
                // Zig zag steps
                else if (nodeRightChildParentLeftChild) {
                    rotateLeft(parent);
                    rotateRight(grandParent);
                }
                else if (nodeLeftChildParentRightChild) {
                    rotateRight(parent);
                    rotateLeft(grandParent);
                }
            }
        }
    }

}
