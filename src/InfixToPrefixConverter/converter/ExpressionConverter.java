package InfixToPrefixConverter.converter;

import InfixToPrefixConverter.model.OperatorPriorityTable;
import InfixToPrefixConverter.util.Constants;

import java.util.Stack;

public class ExpressionConverter {

    // check if expression is fully parenthesized
    public static boolean isValidExpression(String expression) throws IllegalArgumentException {
        Stack<Character> stack = new Stack<>();

        // check if expression is too long or empty
        if (Constants.MAX_STRING_LEN <= expression.length() ||
                expression.isEmpty()) {
            throw new IllegalArgumentException(Constants.INVALID_STRING);
        }

        // use a stack to see if parentheses are matched
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is an opening parenthesis, don't push it onto the stack if
            // the previous character is not an operator
            if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == c) {

                if (i > 0 && OperatorPriorityTable.isOperator(expression.charAt(i-1))) {
                    stack.push(c);
                }
                else {
                    throw new IllegalArgumentException(Constants.INVALID_FORMAT_DIGIT_INFRONT_OPEN_PARENTHESIS);
                }
            }

            // If the character is a closing parenthesis
            else if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == c) {

                // If stack is empty or the top is not an opening parenthesis, it's invalid
                if (stack.isEmpty() ||
                        OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() != stack.pop()) {
                    throw new IllegalArgumentException(Constants.INVALID_PARENTHESIS);
                }
            }
        }

        // If stack is not empty, there are unmatched opening parentheses
        return stack.isEmpty();
    }
}
