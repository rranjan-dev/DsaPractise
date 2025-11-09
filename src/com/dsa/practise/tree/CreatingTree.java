package com.dsa.practise.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CreatingTree {
    static int index = -1;

    public static void main(String[] args) {
        int[] inputArray = new int[]{1, 2, 4, -1, -1, 5, -1, -1, 3, 6, -1, -1, -1};
        Node root = buildTree(inputArray);
        System.out.println("root node of tree is " + root);
        printPreOrderTraversal(root);
        System.out.println();
        printInOrderTraversal(root);
        System.out.println();
        printPostOrderTraversal(root);
        System.out.println();
        levelOrderTraversal(root);
    }

    private static Node buildTree(int[] inputArray) {
        index++;
        if (index < inputArray.length && inputArray[index] == -1) {
            return null;
        }
        Node n1 = new Node(inputArray[index]);
        n1.left = buildTree(inputArray);
        n1.right = buildTree(inputArray);
        return n1;
    }

    private static void printPreOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }

    private static void printInOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        printInOrderTraversal(root.left);
        System.out.print(root.data + " ");
        printInOrderTraversal(root.right);
    }

    private static void printPostOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    private static void levelOrderTraversal(Node root){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node curr = q.remove();
            if(curr==null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }else {
                    q.add(null);
                }
            }else{
                System.out.print(curr.data);
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
    }
}
