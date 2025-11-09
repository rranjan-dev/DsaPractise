package com.dsa.practise.miscelleanous;

public class HouseRobber {
    public int rob(int[] nums) {
        int l = nums.length;
        int sum = Integer.MIN_VALUE;
        int[][] arr = new int[l + 1][l + 1];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (i == j) {
                    arr[i][j] = -1;
                    continue;
                }
                if ((i - j) == 1 || (j - i) == 1) {
                    arr[i][j] = -1;
                    continue;
                }
              //  else {
                    arr[i][j] = nums[i] + nums[j];
                    sum = Math.max(sum, arr[i][j]);
                //}
            }
        }
        for(int k=0; k<l; k++){
            System.out.println();
            for (int n = 0; n <l; n++){
                System.out.print(arr[k][n]);
                System.out.print(" ");
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] nums = {1,2,3,1};
        hr.rob(nums);
    }
}
