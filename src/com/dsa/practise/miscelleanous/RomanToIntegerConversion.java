package com.dsa.practise.miscelleanous;

public class RomanToIntegerConversion {
    public static void main(String[] args) {
        String s =   "IV";
        RomanToIntegerConversion romanToIntegerConversion = new RomanToIntegerConversion();
        System.out.println("Roman to Integer conversion is : " + romanToIntegerConversion.romanToInt(s));
    }
    public int romanToInt(String s) {
        char[] cArray = s.toCharArray();
        int value = 0, sum = 0;
        for (int i = 0; i < cArray.length; i++) {
            if ((cArray[i] == 'V' || cArray[i] == 'X') && (i - 1 >= 0) && cArray[i - 1] == 'I') {
                sum = sum + (getValue(cArray[i]) - 1) - getValue(cArray[i - 1]);
                continue;
            }
            if ((cArray[i] == 'L' || cArray[i] == 'C') && (i - 1 >= 0) && cArray[i - 1] == 'X') {
                sum = sum + (getValue(cArray[i]) - 10) - getValue(cArray[i - 1]);
                continue;
            }
            if ((cArray[i] == 'D' || cArray[i] == 'M') && (i - 1 >= 0) && cArray[i - 1] == 'C') {
                sum = sum + (getValue(cArray[i]) - 100) - getValue(cArray[i - 1]);
                continue;
            }
            sum = sum + getValue(cArray[i]);
        }

        return sum;
    }

    private int getValue(char c) {
        int value = 0;
        switch (c) {
            case 'I':
                value = 1;
                break;
            case 'V':
                value = 5;
                break;
            case 'X':
                value = 10;
                break;
            case 'L':
                value = 50;
                break;
            case 'C':
                value = 100;
                break;
            case 'D':
                value = 500;
                break;
            case 'M':
                value = 1000;
                break;
        }
        return value;
    }


//........................... Better approach .........................
/*
    class Solution {
        public int romanToInt(String s) {
            int sum = 0;
            int prev = 0;

            for (int i = s.length() - 1; i >= 0; i--) { // iterate from right to left
                int curr = getValue(s.charAt(i));

                if (curr < prev) {
                    sum -= curr; // subtract if current < previous
                } else {
                    sum += curr; // else add
                }

                prev = curr;
            }

            return sum;
        }

        private int getValue(char c) {
            switch (c) {
                case 'I': return 1;
                case 'V': return 5;
                case 'X': return 10;
                case 'L': return 50;
                case 'C': return 100;
                case 'D': return 500;
                case 'M': return 1000;
                default: return 0;
            }
        }
    }

 */
}
