package com.dsa.practise.miscelleanous;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] outputLeftArr = new int[nums.length];
        int rightSideProd = 0;
        // int[] outputRightArr = new int[nums.length];
        int[] finalArr = new int[nums.length];
        for(int i=0; i<=nums.length-1; i++){
            if(i==0) outputLeftArr[i] = 1;
            else outputLeftArr[i] = outputLeftArr[i-1] * nums[i-1];
        }

        for(int i=nums.length-1; i>=0; i--){
            if(i==nums.length-1) {
                rightSideProd = 1;
                finalArr[i] = outputLeftArr[i] * rightSideProd;
                rightSideProd = finalArr[i];
            }
            else finalArr[i] = outputLeftArr[i] * rightSideProd;
        }

      /*  for(int i=0; i<=nums.length-1; i++){
            finalArr[i] = outputLeftArr[i] * outputRightArr[i];
        }
      */
        return finalArr;
    }
}
