package com.dsa.practise.miscelleanous;

public class ReverseBits {
    public static void main(String[] args) {
        int input = 43261596;
        System.out.println("input before reversal : " + input);
        System.out.println("input in binary: " + String.format("%32s", Integer.toBinaryString(input)).replace(' ', '0'));
        System.out.println("\n--- Using Iterative Approach ---");
        System.out.println("result after reversal : " + reverseBits(input));
        System.out.println("\n--- Using Divide and Conquer (Bottom-Up) ---");
        System.out.println("result after reversal : " + reverseBitsDivideAndConquer(input));
        System.out.println("\n--- Using Divide and Conquer (Top-Down) ---");
        System.out.println("result after reversal : " + reverseBitsDivideAndConquerTopDown(input));
    }

    // Iterative approach
    private static int reverseBits(int n) {
        System.out.println("the n in binary form is ===>" + Integer.toBinaryString(n));
        int result = 0;
        for (int i = 32; i > 0; i--) {
            int num = n & 1;
            result = result << 1;
            
            result = result | num;
            n = n>>>1;
        }
        System.out.println("the result in binary form is ===>" + Integer.toBinaryString(result));
        return result;
    }

    /**
     * Divide and Conquer approach for reversing bits
     * 
     * The idea is to reverse bits in progressively larger groups:
     * 1. Swap adjacent bits (groups of 1)
     * 2. Swap adjacent pairs (groups of 2)
     * 3. Swap adjacent groups of 4 bits
     * 4. Swap adjacent groups of 8 bits
     * 5. Swap adjacent groups of 16 bits
     * 
     * Time Complexity: O(1) - constant time (always 5 operations)
     * Space Complexity: O(1)
     */
    private static int reverseBitsDivideAndConquer(int n) {
        System.out.println("Step 0 - Original: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        // Step 1: Swap adjacent bits (every 1 bit)
        // Mask: 0x55555555 = 01010101010101010101010101010101
        // Swap odd and even bits
        n = ((n & 0x55555555) << 1) | ((n >>> 1) & 0x55555555);
        System.out.println("Step 1 - Swap adjacent bits: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        // Step 2: Swap adjacent pairs (every 2 bits)
        // Mask: 0x33333333 = 00110011001100110011001100110011
        // Swap pairs of bits
        n = ((n & 0x33333333) << 2) | ((n >>> 2) & 0x33333333);
        System.out.println("Step 2 - Swap pairs: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        // Step 3: Swap adjacent groups of 4 bits
        // Mask: 0x0F0F0F0F = 00001111000011110000111100001111
        // Swap groups of 4 bits
        n = ((n & 0x0F0F0F0F) << 4) | ((n >>> 4) & 0x0F0F0F0F);
        System.out.println("Step 3 - Swap groups of 4: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        // Step 4: Swap adjacent groups of 8 bits
        // Mask: 0x00FF00FF = 00000000111111110000000011111111
        // Swap groups of 8 bits
        n = ((n & 0x00FF00FF) << 8) | ((n >>> 8) & 0x00FF00FF);
        System.out.println("Step 4 - Swap groups of 8: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        // Step 5: Swap adjacent groups of 16 bits
        // No mask needed for the last step - just swap the two halves
        n = (n << 16) | (n >>> 16);
        System.out.println("Step 5 - Swap groups of 16: " + String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        
        return n;
    }

    /**
     * Divide and Conquer approach (Top-Down) - Highly upvoted on LeetCode
     * 
     * This approach reverses bits by swapping progressively smaller groups:
     * 1. Swap groups of 16 bits (swap the two halves)
     * 2. Swap groups of 8 bits
     * 3. Swap groups of 4 bits
     * 4. Swap groups of 2 bits
     * 5. Swap groups of 1 bit (adjacent bits)
     * 
     * Analogy with decimal: 12345678 -> 56781234 -> 78563412 -> 87654321
     * 
     * For 32 bits: log2(32) = 5 steps
     * For 8 bits: log2(8) = 3 steps
     * 
     * Time Complexity: O(1) - constant time (always 5 operations)
     * Space Complexity: O(1)
     */
    private static int reverseBitsDivideAndConquerTopDown(int num) {
        System.out.println("\nStep 0 - Original: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        // Step 1: Swap groups of 16 bits (swap the two halves)
        // Mask: 0xffff0000 preserves upper 16 bits, 0x0000ffff preserves lower 16 bits
        // Right shift upper half by 16, left shift lower half by 16, then OR them
        num = ((num & 0xffff0000) >>> 16) | ((num & 0x0000ffff) << 16);
        System.out.println("Step 1 - Swap groups of 16: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        // Step 2: Swap groups of 8 bits
        // Mask: 0xff00ff00 preserves bits at positions 8-15 and 24-31
        // Mask: 0x00ff00ff preserves bits at positions 0-7 and 16-23
        num = ((num & 0xff00ff00) >>> 8) | ((num & 0x00ff00ff) << 8);
        System.out.println("Step 2 - Swap groups of 8: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        // Step 3: Swap groups of 4 bits
        // Mask: 0xf0f0f0f0 preserves bits at positions 4-7, 12-15, 20-23, 28-31
        // Mask: 0x0f0f0f0f preserves bits at positions 0-3, 8-11, 16-19, 24-27
        num = ((num & 0xf0f0f0f0) >>> 4) | ((num & 0x0f0f0f0f) << 4);
        System.out.println("Step 3 - Swap groups of 4: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        // Step 4: Swap groups of 2 bits
        // Mask: 0xcccccccc = 11001100110011001100110011001100 preserves bits at odd positions in pairs
        // Mask: 0x33333333 = 00110011001100110011001100110011 preserves bits at even positions in pairs
        num = ((num & 0xcccccccc) >>> 2) | ((num & 0x33333333) << 2);
        System.out.println("Step 4 - Swap groups of 2: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        // Step 5: Swap groups of 1 bit (swap adjacent bits)
        // Mask: 0xaaaaaaaa = 10101010101010101010101010101010 preserves odd-positioned bits
        // Mask: 0x55555555 = 01010101010101010101010101010101 preserves even-positioned bits
        num = ((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1);
        System.out.println("Step 5 - Swap adjacent bits: " + String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0'));
        
        return num;
    }
}
