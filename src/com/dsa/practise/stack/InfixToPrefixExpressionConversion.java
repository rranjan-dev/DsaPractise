package com.dsa.practise.stack;

/*
Algorithm :
expression = a+b
postfix = ab+
prefix = +ab
- reverseTheStringWithParenthesis the given expression : b+a
- apply infixToPostfixConversion : ba+
- reverseTheStringWithParenthesis the infixToPostfixConversion output : +ab

d-(c*(b+a))
dcba+*-
-*+abcd
 */

import static com.dsa.practise.stack.InfixToPostFixExpressionConversion.infixToPostfixConverter;

public class InfixToPrefixExpressionConversion {
    public static void main(String[] args) {
        String inputExpression = "((a+b)*c)-d";
        String reversedString = reverseTheStringWithParenthesis(inputExpression);
        String postfixValue = infixToPostfixConverter(reversedString);
        String prefixExpression = reverseTheStringWithParenthesis(postfixValue);
        System.out.println("the final prefix expression is: " + prefixExpression);
    }

    private static String reverseTheStringWithParenthesis(String input){
        if (input == null || input.length() <= 1) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=input.length()-1; i>=0; i--){
            char ch = input.charAt(i);
            if(ch=='('){
                sb.append(')');
            } else if (ch==')') {
                sb.append('(');
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
