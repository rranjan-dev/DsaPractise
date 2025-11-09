package com.dsa.practise.linkedlist;

// 138. Copy List with Random Pointer
//https://leetcode.com/problems/copy-list-with-random-pointer/description/?envType=problem-list-v2&envId=7p5x763

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> myNodeMap = new HashMap<>();
        while (head != null) {
            Node n = new Node(head.val);
            myNodeMap.put(head, n);
            head = head.next;
        }

        for (Map.Entry<Node, Node> e : myNodeMap.entrySet()) {
            Node n = e.getKey();
            Node a = e.getValue();
            a.random = myNodeMap.get(n.random);
            a.next = myNodeMap.get(n.next);
        }

        return myNodeMap.get(head);
    }
}
