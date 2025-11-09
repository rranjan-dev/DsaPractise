package com.dsa.practise.queue;

class Node {
    Node next;
    int data;

    Node(int data){
        this.data = data;
        next = null;
    }
}

public class NormalQueueUsingLinkedList {
    Node front;
    Node rear;
    int capacity;
    int count;
    NormalQueueUsingLinkedList(int capacity){
        this.capacity = capacity;
        front = null;
        rear = null;
    }
    public static void main(String[] args) {
        NormalQueueUsingLinkedList nq = new NormalQueueUsingLinkedList(5);

    }

    void insert(int data){
        if(count==capacity){
            System.out.println("Queue is full !");
            return;
        }
        Node node = new Node(data);
        if(front==null && rear == null){
            front = node;
            rear = node;
        }else {
            rear.next = node;
        }
        count++;
    }

    void delete(){
        if (front == null && rear == null){
            System.out.println("Queue is empty !");
            return;
        }

    }
}
