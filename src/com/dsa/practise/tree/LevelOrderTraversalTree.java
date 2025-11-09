package com.dsa.practise.tree;
/*
                1
            2       3
          7   6   5   4
         8

      OUTPUT : 1 2 3 7 6 5 4 8
*/

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalTree {
    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.left.left.left = new Node(8);

        root.right.left = new Node(5);
        root.right.right = new Node(4);
       // printLevelOrderTraversal1(root);
        printLevelOrderTraversal2(root);
    }

    private static void printLevelOrderTraversal1(Node root) {
        for(int i=1;i<=4;i++){
            printPerLevel(root,i);
            System.out.println();
        }
    }

    private static void printPerLevel(Node root, int level){
        if(root==null){
            return;
        }
        if(level==1){
            System.out.print(root.data);
        }
        printPerLevel(root.left, level-1);
        printPerLevel(root.right, level-1);
    }

    private static void printLevelOrderTraversal2(Node root) {
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        nodeQueue.add(null);
        while(!nodeQueue.isEmpty()){
            Node currentNode = nodeQueue.poll();
            if(currentNode==null){
                System.out.println();
                if(nodeQueue.isEmpty()){
                    break;
                }else{
                    nodeQueue.add(null);
                }
            }else{
                System.out.print(currentNode.data);
                if(currentNode.left != null){
                    nodeQueue.add(currentNode.left);
                }
                if(currentNode.right != null){
                    nodeQueue.add(currentNode.right);
                }
            }

        }
    }
}


