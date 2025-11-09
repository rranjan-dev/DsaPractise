package com.dsa.practise.arrays;

class ReverseStringWordByWord {

    // Reverse a portion of the array
    private static void reverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    public static void reverseWords(char[] s) {
        int n = s.length;

        // Step 1: Reverse the whole array
        reverse(s, 0, n - 1);

        // Step 2: Reverse each word
        int start = 0;
        for (int end = 0; end <= n; end++) {
            if (end == n || s[end] == ' ') {
                reverse(s, start, end - 1);
                start = end + 1;
            }
        }
    }

    // Test
    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();

        reverseWords(s);

        System.out.println(new String(s)); // Output: "blue is sky the"
    }
}

