package com.dsa.practise.tree;

/*
                1
            2       3
          7   6   5   4
         8

      OUTPUT : 1 2 3 7 6 5 4 8
*/

public class OperationsInBT {
    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.left.left.left = new Node(8);

        root.right.left = new Node(5);
        root.right.right = new Node(4);
        root.right.right.right = new Node(9);

//        System.out.println("no of nodes in the tree : " + countNoOfNodes(root));
//        System.out.println("sum of nodes in the tree : " + sumOfNodesInBT(root));
//        System.out.println("height of the binary tree : " + heightOfBT(root));
//        System.out.println("search a node : " + searchANode(root, 0));
//        System.out.println("max value in the tree : " + maxValueNodeInBT(root));

    }

    private static int countNoOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int countLeftNodes = countNoOfNodes(root.left);
        int countRightNodes = countNoOfNodes(root.right);
        return (1 + countLeftNodes + countRightNodes);
    }

    private static int sumOfNodesInBT(Node root) {
        if (root == null) {
            return 0;
        }
        int sumLeftSubTree = sumOfNodesInBT(root.left);
        int sumRightSubTree = sumOfNodesInBT(root.right);
        return root.data + sumLeftSubTree + sumRightSubTree;
    }

    private static int heightOfBT(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSubTreeHeight = heightOfBT(root.left);
        int rightSubTreeHeight = heightOfBT(root.right);
        return 1 + Math.max(leftSubTreeHeight, rightSubTreeHeight);
    }

    private static boolean searchANode(Node root, int searchValue) {
        if (root == null) {
            return false;
        }
        if (root.data == searchValue) {
            return true;
        }

        if (searchANode(root.left, searchValue)) {
            return true;
        }

        if (searchANode(root.right, searchValue)) {
            return true;
        }
        return false;
    }

    private static int maxValueNodeInBT(Node root) {
        if (root == null) {
            return -1;
        }

        int maxLeftTreeVal = maxValueNodeInBT(root.left);
        int maxRightTreeVal = maxValueNodeInBT(root.right);
        int maxVal = Math.max(maxLeftTreeVal, maxRightTreeVal);
        return Math.max(root.data, maxVal);
    }

    private static Node copyOfTree(Node root) {
        if (root == null) {
            return null;
        }
        Node n = new Node(root.data);
        n.left = copyOfTree(root.left);
        n.right = copyOfTree(root.right);
        return n;
    }

    private static boolean equalTrees(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        boolean compareLeftSubTrees = equalTrees(root1.left, root2.left);
        boolean compareRightSubTrees = equalTrees(root1.right, root2.right);
        return compareLeftSubTrees && root1.data == root2.data && compareRightSubTrees;
    }
}
