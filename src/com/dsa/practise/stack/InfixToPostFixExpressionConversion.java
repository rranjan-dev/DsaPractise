package com.dsa.practise.stack;

import java.util.Set;
import java.util.Stack;

public class InfixToPostFixExpressionConversion {
    public static void main(String[] args) {
        String inputInfixExpression = "a^b^c";
        infixToPostfixConverter(inputInfixExpression);
    }

    private static int getPrecedence(char inputChar) {
        if (inputChar == '^') {
            return 3;
        }
        if (inputChar == '*' || inputChar == '/') {
            return 2;
        }
        if (inputChar == '+' || inputChar == '-') {
            return 1;
        }
        return -1;
    }

    private static boolean isRightAssociative(char inputChar) {
        return inputChar == '^';
    }

    static String infixToPostfixConverter(String infixExpr) {
        Set<Character> operatorSet = Set.of('+', '-', '*', '/', '^');
        Stack<Character> charStack = new Stack<>();
        StringBuilder finalResult = new StringBuilder("");
        for (int i = 0; i < infixExpr.length(); i++) {
            char currentChar = infixExpr.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                continue;
            }

            if (Character.isLetterOrDigit(currentChar)) {
                finalResult.append(currentChar);
            } else if (currentChar == '(') {
                charStack.push('(');
            } else if (currentChar == ')') {
                while (!charStack.isEmpty() && charStack.peek() != '(') {
                    finalResult.append(charStack.pop());
                }
                if (!charStack.isEmpty() && charStack.peek() == '(') {
                    charStack.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses in expression: " + infixExpr);
                }
            } else if (operatorSet.contains(currentChar)) {
                while (!charStack.isEmpty() && charStack.peek() != '('
                        && (getPrecedence(charStack.peek()) > getPrecedence(currentChar)
                        || (getPrecedence(charStack.peek()) == getPrecedence(currentChar)
                        && !isRightAssociative(currentChar)))) {
                    finalResult.append(charStack.pop());
                }
                charStack.push(currentChar);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
            }
        }
        while (!charStack.isEmpty()) {
            char top = charStack.pop();
            if (top == '(') {
                throw new IllegalArgumentException("Mismatched parentheses in expression: " + infixExpr);
            }
            finalResult.append(top);
        }
        System.out.println("The postfix representation of the String is: " + finalResult);
        return finalResult.toString();
    }


    private static void infixToPostfixConverterOptimised(String infixExpr) {
        Set<Character> operatorSet = Set.of('+', '-', '*', '/', '^');
        Stack<Character> charStack = new Stack<>();
        StringBuilder finalResult = new StringBuilder();

        for (int i = 0; i < infixExpr.length(); i++) {
            char currentChar = infixExpr.charAt(i);

            // 1. Skip whitespace
            if (Character.isWhitespace(currentChar)) {
                continue;
            }

            // 2. Handle '('
            if (currentChar == '(') {
                charStack.push(currentChar);
            }

            // 3. Handle operators
            else if (operatorSet.contains(currentChar)) {
                while (!charStack.isEmpty() && charStack.peek() != '(' &&
                        (getPrecedence(charStack.peek()) > getPrecedence(currentChar) ||
                                (getPrecedence(charStack.peek()) == getPrecedence(currentChar) && !isRightAssociative(currentChar)))) {
                    finalResult.append(charStack.pop());
                }
                charStack.push(currentChar);
            }

            // 4. Handle ')' In above infixToPostfixConverter(), you assume there's always a matching ( for every ) — which can crash the program with EmptyStackException.
            else if (currentChar == ')') {
                boolean foundOpening = false;
                while (!charStack.isEmpty()) {
                    if (charStack.peek() == '(') {
                        charStack.pop();
                        foundOpening = true;
                        break;
                    } else {
                        finalResult.append(charStack.pop());
                    }
                }
                if (!foundOpening) {
                    throw new IllegalArgumentException("Mismatched parentheses: no matching '(' for ')'");
                }
            }

            // 5. Handle valid operands. In your above infixToPostfixConverter() Currently, any character not in the operator set or parentheses is assumed to be an operand.
            // Better to explicitly validate operands:
            else if (Character.isLetterOrDigit(currentChar)) {
                finalResult.append(currentChar);
            }

            // 6. Error on invalid characters
            else {
                throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
            }
        }

        // 7. Drain the stack and check for mismatched '('. After the main loop, you're popping all remaining operators — but if any ( is left, that’s a mismatch.
        //Modify your stack cleanup as:
        while (!charStack.isEmpty()) {
            char top = charStack.pop();
            if (top == '(' || top == ')') {
                throw new IllegalArgumentException("Mismatched parentheses in expression.");
            }
            finalResult.append(top);
        }

        System.out.println("The postfix representation of the String is: " + finalResult);
    }

}
