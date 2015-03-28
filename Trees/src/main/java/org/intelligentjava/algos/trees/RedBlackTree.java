package org.intelligentjava.algos.trees;



/**
 * Red-Black tree implementation. From Introduction to Algorithms 3rd edition.
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
     * Slightly modified delete routine for red-black tree.
     * 
     * {@inheritDoc}
     */
    @Override
    protected Node delete(Node deleteNode) {
        Node removedOrMovedNode = deleteNode; // same as deleteNode if it has only one child, and otherwise it replaces deleteNode
        ColorEnum removedOrMovedNodeColor = ((RedBlackNode)removedOrMovedNode).color;
        Node replaceNode = null; // track node that replaces removedOrMovedNode
        
        if (deleteNode != null) {
            if (deleteNode.left == nilNode) {
                replaceNode = deleteNode.right;
                rbTreeTransplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == nilNode) {
                replaceNode = deleteNode.left;
                rbTreeTransplant(deleteNode, deleteNode.left);
            } else {
                removedOrMovedNode = getMinimum(deleteNode.right);
                removedOrMovedNodeColor = ((RedBlackNode)removedOrMovedNode).color;
                replaceNode = removedOrMovedNode.left;
                if (removedOrMovedNode.parent == deleteNode) {
                    replaceNode.parent = removedOrMovedNode;
                } else {
                    rbTreeTransplant(removedOrMovedNode, removedOrMovedNode.right);
                    removedOrMovedNode.right = deleteNode.right;
                    removedOrMovedNode.right.parent = removedOrMovedNode;
                }
                rbTreeTransplant(deleteNode, removedOrMovedNode);
                removedOrMovedNode.left = deleteNode.left;
                removedOrMovedNode.left.parent = removedOrMovedNode;
                ((RedBlackNode)removedOrMovedNode).color = ((RedBlackNode)deleteNode).color;
            }
            
            size--;
            if (removedOrMovedNodeColor == ColorEnum.BLACK) {
                deleteRBFixup((RedBlackNode)replaceNode);
            }
        }

        return replaceNode;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getMinimum(Node node) {
        while (node.left != nilNode) {
            node = node.left;
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node getMaximum(Node node) {
        while (node.right != nilNode) {
            node = node.right;
        }
        return node;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Node rotateLeft(Node node) {
        Node temp = node.right;
        temp.parent = node.parent;

        node.right = temp.left;
        if (node.right != nilNode) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != nilNode) {
            if (node.equals(temp.parent.left)) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        
        return temp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != nilNode) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != nilNode) {
            if (node.equals(temp.parent.left)) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        
        return temp;
    }

    
    /**
     * Similar to original transplant() method in BST but uses nilNode instead of null.
     */
    private Node rbTreeTransplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == nilNode) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        newNode.parent = nodeToReplace.parent;
        return newNode;
    }
    
    /**
     * Restores Red-Black tree properties after delete if needed.
     */
    private void deleteRBFixup(RedBlackNode currentNode) {
        while (currentNode != root && currentNode.color == ColorEnum.BLACK) {
            
            if (currentNode == currentNode.parent.left) {
                RedBlackNode sibling = (RedBlackNode)currentNode.parent.right;
                if (sibling.color == ColorEnum.RED) { // case 1 - sibling is red
                    sibling.color = ColorEnum.BLACK;
                    ((RedBlackNode)currentNode.parent).color = ColorEnum.RED;
                    rotateLeft(currentNode.parent);
                    sibling = (RedBlackNode)currentNode.parent.right; // converted to case 2, 3 or 4
                }
                // case 2 sibling is black and both of its children are black
                if (((RedBlackNode)sibling.left).color == ColorEnum.BLACK && ((RedBlackNode)sibling.right).color == ColorEnum.BLACK) {
                    sibling.color = ColorEnum.RED;
                    currentNode = (RedBlackNode)currentNode.parent;
                } else if (((RedBlackNode)sibling.right).color == ColorEnum.BLACK) { // case 3 sibling is black and its left child is red and right child is black
                    ((RedBlackNode)sibling.left).color = ColorEnum.BLACK;
                    sibling.color = ColorEnum.RED;
                    rotateRight(sibling);
                    sibling = (RedBlackNode)currentNode.parent.right;
                } else if (((RedBlackNode)sibling.right).color == ColorEnum.RED) { // case 4 sibling is black and right child is red
                    sibling.color = ((RedBlackNode)currentNode.parent).color;
                    ((RedBlackNode)currentNode.parent).color = ColorEnum.BLACK;
                    ((RedBlackNode)sibling.right).color = ColorEnum.BLACK;
                    rotateLeft(currentNode.parent);
                    currentNode = (RedBlackNode)root;
                }
            } else {
                RedBlackNode sibling = (RedBlackNode)currentNode.parent.left;
                if (sibling.color == ColorEnum.RED) { // case 1 - sibling is red
                    sibling.color = ColorEnum.BLACK;
                    ((RedBlackNode)currentNode.parent).color = ColorEnum.RED;
                    sibling = (RedBlackNode)currentNode.parent.left; // converted to case 2, 3 or 4
                }
                // case 2 sibling is black and both of its children are black
                if (((RedBlackNode)sibling.left).color == ColorEnum.BLACK && ((RedBlackNode)sibling.right).color == ColorEnum.BLACK) {
                    sibling.color = ColorEnum.RED;
                    currentNode = (RedBlackNode)currentNode.parent;
                }  else if (((RedBlackNode)sibling.left).color == ColorEnum.BLACK) { // case 3 sibling is black and its right child is red and left child is black
                    ((RedBlackNode)sibling.right).color = ColorEnum.BLACK;
                    sibling.color = ColorEnum.RED;
                    rotateLeft(sibling);
                    sibling = (RedBlackNode)currentNode.parent.left;
                } else if (((RedBlackNode)sibling.left).color == ColorEnum.RED) { // case 4 sibling is black and left child is red
                    sibling.color = ((RedBlackNode)currentNode.parent).color;
                    ((RedBlackNode)currentNode.parent).color = ColorEnum.BLACK;
                    ((RedBlackNode)sibling.left).color = ColorEnum.BLACK;
                    rotateRight(currentNode.parent);
                    currentNode = (RedBlackNode)root;
                }
            }
            
        }
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
