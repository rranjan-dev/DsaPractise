package com.dsa.practise.tree;

/*
          8
        /   \
      3      10
     / \       \
    1   6       14
       / \     /
      4   7   13
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTrees {

    public static void main(String[] args) {
        Node root = new Node(8);

        root.left = new Node(3);
        root.right = new Node(10);

        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);

        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
       // levelOrderTraversal(root);
       // insertNode(root, 5, null);
       // insertNodeOptimised(root,5);
        levelOrderTraversal(root);
      //  System.out.printf("Min Element in The BST : " + findMinElementInBST_Iterative(root));
      //  System.out.printf("chk if given tree is BST : " + checkIfGivenTreeIsBst(root));
        System.out.println();
      //  deletingALeafNode(root, 7);
      //  deleteNodeWithTwoChildrenInBST(root, 6);
      //  levelOrderTraversal(root);
        System.out.println("printListOfNodesHavingValuesInRange => ");
        printListOfNodesHavingValuesInRange(root, 5, 10);
    }

    private static void levelOrderTraversal(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node n = q.remove();
            if(n == null){
                System.out.println();
                if(q.isEmpty()){
                    return;
                }else {
                    q.add(null);
                }
            }else {
                System.out.print(n.data + " ");
                if(n.left != null) {
                    q.add(n.left);
                }
                if(n.right != null) {
                    q.add(n.right);
                }
            }
        }
    }

    private static void insertNode(Node root, int insertVal, Node prev){
        if(root==null){
            if(insertVal > prev.data){
                prev.right = new Node(insertVal);
            }else {
                prev.left = new Node(insertVal);
            }
            return;
        }
        if(insertVal > root.data){
            insertNode(root.right, insertVal, root);
        }else if(insertVal < root.data){
            insertNode(root.left, insertVal, root);
        }else {
            throw new RuntimeException("duplicate insertVal...");
        }
    }

    // better approach for above code
    private static Node insertNodeOptimised(Node root, int insertVal){
        if(root==null){
            return new Node(insertVal);
        }
        if(insertVal > root.data){
            root.right = insertNodeOptimised(root.right, insertVal);
        }else {
            root.left = insertNodeOptimised(root.left, insertVal);
        }
        return root;
    }

    //recursion
    private static int findMinElementInBST(Node root){
        if(root==null){
            return Integer.MAX_VALUE;
        }
        return Math.min(root.data, findMinElementInBST(root.left));
    }

    //iterative
    private static int findMinElementInBST_Iterative(Node root){
        if(root == null){
            return Integer.MAX_VALUE;
        }
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }

    private static boolean checkIfGivenTreeIsBst(Node root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValidBST(Node root, Integer min, Integer max){
        if(root == null) return true;

        if(root.data <= min || root.data >= max) return false;

        return isValidBST(root.left, min, root.data)
                && isValidBST(root.right, root.data, max);
    }

    private static Node deletingALeafNode(Node root, int data){
        if(data < root.data){
            root.left = deletingALeafNode(root.left, data);
        }
        if(data > root.data){
            root.right = deletingALeafNode(root.right, data);
        }
        if(root.left == null && root.right == null){
            return null;
        }
        return root;
    }

    private static Node deleteNodeWithTwoChildrenInBST(Node root, int data){
        if(data < root.data){
            root.left = deleteNodeWithTwoChildrenInBST(root.left, data);
        }
        if(data > root.data){
            root.right = deleteNodeWithTwoChildrenInBST(root.right, data);
        }
        if(data == root.data){
            Node n = getInorderSuccessor(root);
            root.data = n.data;
            root.right = deletingALeafNode(root.right, n.data);
        }
        return root;
    }

    private static Node getInorderSuccessor(Node root){
        if(root.left == null){
            return root;
        }
        return getInorderSuccessor(root.left);
    }

/*
          8
        /   \
      3      10
     / \       \
    1   6       14
       / \     /
      4   7   13
*/
    // 2 to 10
    private static void printListOfNodesHavingValuesInRange(Node root, int min, int max){
        List<Integer> myList = new ArrayList<>();
        fetchNodesInTheRange(root, min, max, myList);
        for(int i : myList){
            System.out.print(i + ",");
        }
    }

    private static void fetchNodesInTheRange(Node root, int min, int max, List<Integer> myList){
        if(root == null){
            return;
        }

        if(root.data < min){
            fetchNodesInTheRange(root.right, min, max, myList);
        }

        else if(root.data > max){
            fetchNodesInTheRange(root.left, min, max, myList);
        }

        else {
            myList.add(root.data);
            fetchNodesInTheRange(root.left, min, max, myList);
            fetchNodesInTheRange(root.right, min, max, myList);
        }

    }
}
