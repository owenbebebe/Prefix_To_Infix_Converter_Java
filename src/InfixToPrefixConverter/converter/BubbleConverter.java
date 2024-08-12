package InfixToPrefixConverter.converter;

import InfixToPrefixConverter.model.OperatorPriorityTable;

public class BubbleConverter extends ExpressionConverter {

    private final static int SWAP_SIZE = 2;

    // use bubble likely way to move every operator to its front
    public static String convertInfixToPreFix(String expression) {

        // check if expression is valid
        try {
            isValidExpression(expression);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // for every operator keep on jumping to numbers front and compares to its precedent
        for (int i = 0; i < expression.length(); i++) {

            // if we found an operator keep on jumping to found till its place
            if (OperatorPriorityTable.isOperator(expression.charAt(i)) &&
                    OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() != expression.charAt(i) &&
                    OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() != expression.charAt(i)) {
                expression = jumpOperator(expression, i);
                System.out.println("current: " + expression);
            }
        }
        return removeParentheses(expression);
    }

    // recursively compares to the operator infront of current operators's digit
    private static String jumpOperator(String expression, int i) {

        // stops when at the front
        if (0 >= i) {
            return expression;
        }

        // stops when there is no digit in front of the character unless ')' is at its front
        if (OperatorPriorityTable.isOperator(expression.charAt(i - 1))) {

            // push operator to parenthesis front if there is a close parenthesis at front
            if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == expression.charAt(i - 1)) {

                // keep on swapping till open parenthesis is reached
                while (i > 0 && OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() != expression.charAt(i+1)) {
                    expression = swapWithCharInFront(expression, i--);
                }
                return jumpOperator(expression, i);
            }
            else {
                return expression;
            }
        }

        // swap if there is a digit in front and there it either does not exist an operator
        // or the operator in front of its digit is > than current operator
        else {

            // just swap if there are no precedent
            if (i <= 1) {
                expression = swapWithCharInFront(expression, i);
            }

            // swap if the precedent is a digit
            else if (!OperatorPriorityTable.isOperator(expression.charAt(i - 2)) ||
                    OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == expression.charAt(i - 2) ||
                            OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == expression.charAt(i - 2)) {
                expression = swapWithCharInFront(expression, i);
            }

            // swap if there is an operator as a precedent and its priority is larder than current operator
            else if (OperatorPriorityTable.isOperator(expression.charAt(i - 2)) &&
                    OperatorPriorityTable.getPriority(expression.charAt(i)) <=
                            OperatorPriorityTable.getPriority(expression.charAt(i - 2))) {

                // swap twice one with the digit in front another with the operator in front of the digit
                expression = swapWithCharInFront(expression, i--);
                expression = swapWithCharInFront(expression, i);
            }

        }
        return jumpOperator(expression, i - 1);
    }

    // given a string and an index i swap the character in front of i
    private static String swapWithCharInFront(String input, int i) {

        // input checking
        if (null == input || SWAP_SIZE > input.length()) {
            return input;
        }

        // perform a swap using a character array
        char[] charArray = input.toCharArray();
        char temp  = charArray[i];
        charArray[i] = charArray[i-1];
        charArray[i-1] = temp;
        return new String(charArray);
    }

    // remove any parentheses inside a string
    public static String removeParentheses(String expression) {
        StringBuilder result = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() != c &&
                    OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() != c) {
                result.append(c);
            }
        }
        return result.toString();
    }
}
