package com.dsa.practise.tree;

import java.util.*;

class AvlTree {
    private static class Node {
        int data;
        int ht;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.ht = 1;
        }
    }

    static Node root;

    AvlTree() {
        root = null;
    }

    static int height(Node node) {
        if (node == null)
            return 0;
        return node.ht;
    }

    static int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public static void levelOrderTraversal(Node node) {
        if (node == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        q.add(null);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println();
                if (q.isEmpty())
                    break;
                else
                    q.add(null);
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
        }
    }

    static Node findInOrderSuccessor(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    static Node rightRotation(Node y) {
        Node x = y.left;
        Node t = x.right;
        x.right = y;
        y.left = t;
        x.ht = 1 + Math.max(height(x.left), height(x.right));
        y.ht = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    static Node leftRotation(Node x) {
        Node y = x.right;
        Node t = y.left;
        y.left = x;
        x.right = t;
        x.ht = 1 + Math.max(height(x.left), height(x.right));
        y.ht = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    // insertNode(root, data) will return root reference as after every insertion followed by re-balancing root may change
    void insertNode(int data) {
        root = insertNode(root, data);
    }

    //overloaded version of insertNode
    Node insertNode(Node node, int data) {
        if (node == null)
            node = new Node(data);
        else if (data < node.data)
            node.left = insertNode(node.left, data);
        else if (data > node.data)
            node.right = insertNode(node.right, data);
        else
            return node;
        //duplicates not allowed. so we don't do any insertion.
        // We just return the node i.e.root node as it is and assign it to root node reference itself in the caller function
        // basically doing nothing in case of duplicate.

        //balacing
        node.ht = 1 + Math.max(height(node.left), height(node.right)); // height of a node is calculated as  the difference of height of left and right subtree +1.
        // here we are calculating the height of a subtree by
        int bf = getBalance(node);
        if (bf > 1 && data < node.left.data) //LL case
            return rightRotation(node);
        if (bf < -1 && data > node.right.data) //RR case
            return leftRotation(node);
        if (bf > 1 && data > node.left.data) { //LR case
            node.left = leftRotation(node.right);
            return rightRotation(node);
        }
        if (bf < -1 && data < node.left.data) { //RL case
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    void deleteNode(int data) {
        root = deleteNode(root, data);
    }

    Node deleteNode(Node node, int data) {
        if (node.data < data)
            node.right = deleteNode(node.right, data);
        else if (node.data > data)
            node.left = deleteNode(node.left, data);
        else {
            //case1: if no child
            if (node.left == null && node.right == null)
                return null;
                //case2: if one child
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
                //case3: both children
            else {
                Node is = findInOrderSuccessor(node.right);
                node.data = is.data;
                node.right = deleteNode(node.right, is.data);
            }
        }
        //balacing
        if (node == null)
            return node;
        node.ht = 1 + Math.max(height(node.left), height(node.right));
        int bf = getBalance(node);
        if (bf > 1 && data < node.left.data) //LL case
            return rightRotation(node);
        if (bf < -1 && data > node.right.data) //RR case
            return leftRotation(node);
        if (bf > 1 && data > node.left.data) { //LR case
            node.left = leftRotation(node.right);
            return rightRotation(node);
        }
        if (bf < -1 && data < node.left.data) { //RL case
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }
}

public class AvlTreesTest {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.insertNode(10);
        tree.insertNode(20);
        tree.insertNode(30);
        tree.insertNode(40);
        tree.insertNode(50);
        tree.insertNode(25);
        System.out.println("Level order: ");
        tree.levelOrderTraversal(tree.root);
        System.out.println();
    }
}
