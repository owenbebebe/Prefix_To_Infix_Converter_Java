package InfixToPrefixConverter.converter;

import InfixToPrefixConverter.model.OperatorPriorityTable;
import java.util.*;

public class StackConverter extends ExpressionConverter {

    // convert an infix to prefix expression using a symbol stack
    public static String convertInfixToPrefix(String expression) {

        // check if expression is valid
        try {
            isValidExpression(expression);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // reverse infix expression from left to right
        String reversedInfix = reverseInfix(expression);

        // convert to nearly postfix using a symbol stack and reverse it to get prefix
        return reverseInfix(convertInfixToNearlyPostFix(reversedInfix));
    }

    // reversing infix expression from left to right
    private static String reverseInfix(String expression) {
        StringBuilder reversedExpression = new StringBuilder();
        char[] chars = expression.toCharArray();

        // Traverse the expression in reverse order
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];

            // replace '(' with ')'
            if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == c) {
                reversedExpression.append(OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() );
            }

            // replace ')' with '('
            else if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == c) {
                reversedExpression.append(OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() );
            }
            else {
                reversedExpression.append(c);
            }
        }
        return reversedExpression.toString();
    }

    // use a stack to convert an infix expression to nearly a postfix one
    private static String convertInfixToNearlyPostFix(String expression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> symbol = new Stack<>();

        // iterate and add operators to symbol stack and convert to nearly postfix
        for (char c : expression.toCharArray()) {

            // if c is an operand just push to postfix expression
            if (!OperatorPriorityTable.isOperator(c)) {
                postfixExpression.append(c);
            }

            // push to symbol stack if '('
            else if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == c) {
                symbol.push(c);
            }

            // if ')' start to push every symbol until '(' is found
            else if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == c) {

                // stop popping till '(' is found
                while (!symbol.isEmpty() &&
                        OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() != symbol.peek()) {
                    postfixExpression.append(symbol.pop());
                }

                // pop '('
                symbol.pop();
            }
            // if c is other operators start to compare it to the top of symbol stack
            else {

                // keep on popping symbol stack when the top symbol priority is higher than c
                while (!symbol.isEmpty() &&
                        OperatorPriorityTable.getPriority(c) <
                                OperatorPriorityTable.getPriority(symbol.peek())) {
                    postfixExpression.append(symbol.pop());
                }
                symbol.push(c);
            }
        }

        // at the end pop every symbol to the postfix expression
        while (!symbol.isEmpty()) {
            postfixExpression.append(symbol.pop());
        }

        return postfixExpression.toString();
    }
}
