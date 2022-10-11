/*
 * This file is part of NTNU's IDATA2302 Lab 04.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */

package no.ntnu.idata2302.lab04;


public class Tree {
    private Node root;

    public void insert(int item) {
        Node parent = findParentOfItem(item);

        if (parent == null) {
            root = new Node(item, null);
            return;
        }

        // check if item already exists in the tree
        if ((parent.left != null && parent.left.item == item) || (parent.right != null && parent.right.item == item)) {
            throw new RuntimeException("Item already exists");
        } else if (item < parent.item) {
            parent.left = new Node(item, parent);
        } else {
            parent.right = new Node(item, parent);
        }
    }

    public void delete(int item) {
        Node parent = findParentOfItem(item);

        Node node = null;
        Boolean left = null;
        if (parent.left != null && parent.left.item == item) {
            node = parent.left;
            left = true;
        } else if (parent.right != null && parent.right.item == item) {
            node = parent.right;
            left = false;
        } else {
            throw new RuntimeException("Item doesn't exist");
        }

        // node doesn't have children, just delete it
        if (node.left == null && node.right == null) {
            if (left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // node has 2 children
        else if (node.left != null && node.right != null) {
            Node successor = findSuccessor(node);

            if (node.right != successor) {
                // since node.right != null, the successor must be in the right subtree
                // as such, it can't have a left child and could only have a right child
                if (successor.right != null) {
                    // the successor must be the left child of its parent
                    successor.parent.left = successor.right;
                    successor.right.parent = successor.parent;
                } else {
                    successor.parent.left = null;
                }
                successor.right = node.right;
                node.right.parent = successor;
            }
            successor.parent = parent;
            if (left) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = node.left;
            successor.left.parent = successor;
        }
        // node only has a left child
        else if (node.left != null) {
            if (left) {
                parent.left = node.left;
                node.left.parent = parent;
            } else {
                parent.right = node.left;
                node.left.parent = parent;
            }
        }
        // node only has a right child
        else {
            if (left) {
                parent.left = node.right;
                node.right.parent = parent;
            } else {
                parent.right = node.right;
                node.right.parent = parent;
            }
        }
    }

    // finds the theoretical or actual parent of an item
    public Node findParentOfItem(int item) {
        Node node = root;
        Node prev_node = null;

        while (node != null) {
            if (item < node.item) {
                prev_node = node;
                node = node.left;
            } else if (item > node.item) {
                prev_node = node;
                node = node.right;
            } else {
                return prev_node;
            }
        }

        return prev_node;
    }

    public Node findSuccessor(Node node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        Node parent = node.parent;
        if (parent == null) {
            throw new RuntimeException("node has no successor");
        }
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public void inOrderTraversal() {
        // TODO: implement in-order tree traversal, printing the items
        throw new RuntimeException("Not yet implemented!");
    }

    public void postOrderTraversal() {
        // TODO: implement post-order tree traversal, printing the items
        throw new RuntimeException("Not yet implemented!");
    }

    public void preOrderTraversal() {
        // TODO: implement pre-order tree traversal, printing the items
        throw new RuntimeException("Not yet implemented!");
    }

    public static void main(String[] args) {
        Tree t = new Tree();
    }
}

class Node {
    Node parent;
    Node left;
    Node right;
    int item;

    Node(int item, Node parent) {
        this.item = item;
        this.parent = parent;
    }
}
