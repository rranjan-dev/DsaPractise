package com.dsa.practise.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of positive numbers.
Partition the set into two subsets with a minimum difference between their subset sums.
 */
public class MinimumSubsetSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 11, 5};
        int n = arr.length;
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        boolean[][] dp = new boolean[arr.length + 1][sum + 1];
        Arrays.fill(dp, false);
        subsetSum(arr, sum, dp, n);
        List<Integer> minimumSubsetPossibleValuesList = new ArrayList<>();
        for(int j = 0; j<=sum/2; j++){
            if(dp[n][j] == true ){
                minimumSubsetPossibleValuesList.add(j);
            }
        }
        int minimum = Integer.MAX_VALUE;
        for(int i=0; i<minimumSubsetPossibleValuesList.size(); i++){
          //  minimum = Math.min(minimum, sum-2(minimumSubsetPossibleValuesList.get(i)));
        }
        //return minimum;
    }

    private static void subsetSum(int[] arr, int sum, boolean[][] dp, int n) {
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (arr[n - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}
