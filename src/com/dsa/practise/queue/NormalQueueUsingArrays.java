package com.dsa.practise.queue;

/*
Where It Breaks: Intermediate Inserts + Deletes

Let’s say you:

Insert 5 items → rear = 5

Delete 2 items → front = 2, rear = 5

Now, front != rear, queue isn’t empty.
But when you next call insert(), your check:

if (rear == capacity) { // rear == 5
    System.out.println("queue is full !");
    return;
}
→ Prints “queue is full” ❌ even though there’s space in front (indices 0 and 1).

So your implementation works for linear, one-time-fill queues, but not for multiple cycles of insert/delete.

 */

public class NormalQueueUsingArrays {
    public static void main(String[] args) {
        NormalQueue nq = new NormalQueue(5);
        nq.insert(10);
        nq.insert(20);
        nq.insert(30);
        nq.insert(40);
        nq.insert(50);
        nq.display();
        System.out.println();
        nq.delete();
        nq.delete();
        nq.display();
    }

    static class NormalQueue {
        int front, rear, capacity;
        int[] arr;

        public NormalQueue(int capacity) {
            this.capacity = capacity;
            this.front = -1;
            this.rear = -1;
            arr = new int[capacity];
        }

        public void insert(int element) {
            if (rear == capacity) {
                System.out.println("queue is full !");
                return;
            }
            if (rear == front) {
                rear = front = 0;
            }
            arr[rear++] = element;
        }

        public void delete() {
            if (front == rear) {
                System.out.println("queue is empty !");
                return;
            }
            System.out.println("deleted object is : " + arr[front++]);
            if (rear == front) {
                rear = -1;
                front = -1;
            }
        }

        public void display() {
            if (front == -1 && rear == -1) {
                System.out.println("queue is empty !");
            }

           /* while(front < rear){
                System.out.print(arr[front++] + " ");
            }
             You are incrementing front inside display(), which changes the queue state!
              After calling display(), your queue becomes empty logically — that’s wrong.
             */

            for (int i = 0; i < rear; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}
