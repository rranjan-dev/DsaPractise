package com.dsa.practise.miscelleanous;

import java.util.HashMap;
import java.util.Map;

public class MajorityElementInArray {
    public static void main(String[] args) {
        int[] nums = {8, 9, 8, 9, 8};
        System.out.println("majority element is : " + majorityElement(nums));
    }

    private static int majorityElement(int[] nums) {
        Map<Integer,Integer> demoMap = new HashMap<>();
        int count = 0;
        // int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums.length != 1 && j != i && nums[i] == nums[j]) {
                    count++;
                } else if (nums.length == 1) {
                    count++;
                }
                demoMap.put(nums[i], count);
            }
            if (count > nums.length / 2) {
                demoMap.forEach((key, value) -> System.out.println(key + "---" + value));
                return nums[i];
            }
        }
        return -1;
    }
}

