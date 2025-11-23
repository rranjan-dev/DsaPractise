package com.dsa.practise.tree;

import static com.dsa.practise.tree.AvlTree.height;

public class MyAvlTreePractise {
    private static class Node {
        int data;
        Node left, right;
        int height;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    static Node root;

    MyAvlTreePractise(){
        root = null;  // initialize root to null at the beginning
    }

    public void insert(int data){
        root = insert(root, data);
    }

    private Node insert(Node node, int data){
        if(node == null) node = new Node(data);
        else if(data < node.data){
            insert(node.left, data);
        }else if(data > node.data){
            insert(node.right, data);
        } else {
            return node; // duplicate case so don't do insertion just return the result
        }

        // update height
     //   node.height = 1 + Math.max(height(node.left), height(node.right));
        return null;
    }


}
