package com.dsa.practise.linkedlist;

import org.w3c.dom.Node;

public class PalindromeList {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.next = n2;
        Node n3 = new Node(2);
        n2.next = n3;
        Node n4 = new Node(1);
        //  Node n4 = new Node(4);
        n3.next = n4;
        printLinkedList(n1);
        System.out.println();
        // Node rev = reverseList(n1);
        // printLinkedList(rev);
        System.out.println("is the given LinkedList Palindrome : " + checkPalindromicList(n1));
    }

    private static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
    }

    private static boolean checkPalindromicList(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node secondHalf = reverseList(slow);
        System.out.print("printing the secondHalf after reversal ===> ");
        printLinkedList(secondHalf);
        System.out.println();

        Node firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private static Node reverseList(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

}


