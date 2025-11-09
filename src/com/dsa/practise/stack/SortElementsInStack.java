package com.dsa.practise.stack;

import java.util.Stack;

public class SortElementsInStack {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();
        myStack.push(203);
        myStack.push(555);
        myStack.push(123);
        myStack.push(0);
        myStack.push(999);
        System.out.println("before sorting ==>" + myStack);
       // sortStack(myStack);
        sortStackUsingRecursion(myStack);
        System.out.println("after sorting ==>" + myStack);
        bottomInsertion(myStack, 1000);
        System.out.println("stack after bottom insertion: " + myStack);
        reverseStack(myStack);
        System.out.println("stack after reversal: " + myStack);
    }

/*    private static void sortStack(Stack<Integer> inputStack) {
        Stack<Integer> tempStack = new Stack<>();
        while (!inputStack.isEmpty()) {
            int current = inputStack.pop();
            if (tempStack.isEmpty() || current < tempStack.peek()) {
                tempStack.push(current);
            } else {
                while (current > tempStack.peek()) {
                    int poppedElement = tempStack.pop();
                    inputStack.push(poppedElement);
                }
                tempStack.push(current);
            }
        }

        while (!tempStack.isEmpty()) {
            inputStack.push(tempStack.pop());
        }
    }*/

    private static void sortStack(Stack<Integer> inputStack) {
        Stack<Integer> tempStack = new Stack<>();

        while (!inputStack.isEmpty()) {
            int current = inputStack.pop();

            // move elements back until correct place for current is found
            while (!tempStack.isEmpty() && tempStack.peek() > current) {
                inputStack.push(tempStack.pop());
            }
            tempStack.push(current);
        }

        // move back to inputStack to restore sorted order
        while (!tempStack.isEmpty()) {
            inputStack.push(tempStack.pop());
        }
    }

    private static void sortedInsert(Stack<Integer> inputStack, int element) {
        if (inputStack.isEmpty() || element > inputStack.peek()) {
            inputStack.push(element);
            return;
        }
        int poppedElement = inputStack.pop();
        sortedInsert(inputStack, element);
        inputStack.push(poppedElement);
    }

    private static void sortStackUsingRecursion(Stack<Integer> inputStack){
        if(inputStack.isEmpty()){
            return;
        }
        int inputEle = inputStack.pop();
        sortStackUsingRecursion(inputStack);
        sortedInsert(inputStack,inputEle);
    }

    /*
    usually when we insert an element in the stack, it inserts at the top position, now i want the
    insertion to happen from the bottom of the stack
     */
    private static void bottomInsertion(Stack<Integer> inputStack, int element){
        if(inputStack.isEmpty()){
           inputStack.push(element);
           return;
        }
        int temp = inputStack.pop();
        bottomInsertion(inputStack, element);
        inputStack.push(temp);
    }

    private static void reverseStack(Stack<Integer> inputStack){
        if(inputStack.size()==1){
            return;
        }
        int temp = inputStack.pop();
        reverseStack(inputStack);
        bottomInsertion(inputStack, temp);
    }


}
