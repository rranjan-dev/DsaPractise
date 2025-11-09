package com.dsa.practise.linkedlist;

public class LinkeListFromGivenArray {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        int[] inputArray = new int[]{1, 2, 3, 4, 5};
        Node head = createLinkedList(inputArray);
        printLinkedList(head);
    }

    private static Node createLinkedList(int[] inputArray) {
        Node prev = null, head = null;
        for(int i=0; i<inputArray.length; i++){
            Node n = new Node(inputArray[i]);
            if(head == null){
                head = n;
            }else{
                prev.next = n;
            }
            prev = n;
        }
        return head;
    }

    public static void printLinkedList(Node head){
        while(head != null){
            System.out.print(head.val);
            if(head.next != null){
                System.out.print("->");
            }
            head = head.next;
        }
    }
}


