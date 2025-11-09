package com.dsa.practise.miscelleanous;

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.Arrays;

class RotateArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k=1;
        rotate(arr, k);
    }

    public static void rotate(int[] nums, int k) {
        System.out.println("Before Rotation >>>" + Arrays.toString(nums));
        for (int i = 0; i < k; i++) {
           // rotateRightByOneUnit(nums);
            rotateLeftByOneUnit(nums);
        }
    }

    private static void rotateRightByOneUnit(int[] arr) {
        int lastElement = arr[arr.length - 1];
        for(int i=arr.length-1; i>0; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = lastElement;
        System.out.println("After RIGHT Rotation >>>" + Arrays.toString(arr));
    }

    private static void rotateLeftByOneUnit(int[] arr) {
        int firstElement = arr[0];
        for(int i=1; i<=arr.length-1; i++){
            arr[i-1] = arr[i];
        }
        arr[arr.length-1] = firstElement;
        System.out.println("After LEFT Rotation >>>" + Arrays.toString(arr));
    }
}
