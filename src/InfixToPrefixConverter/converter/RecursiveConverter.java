package InfixToPrefixConverter.converter;

import InfixToPrefixConverter.model.OperatorPriorityTable;
import InfixToPrefixConverter.util.Constants;

import java.lang.constant.Constable;

public class RecursiveConverter extends ExpressionConverter{

    // convert infix to prefix using recursive to break left and right using an operator
    public static String convertInfixToPreFix(String expression) {

        // check if expression is valid
        try {
            isValidExpression(expression);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // initiate the recursive function
        return infixToPrefix(expression);
    }

    // Function to check if a character is an operand
    public static boolean isOperand(char ch) {
        return Character.isDigit(ch) || Character.isLetter(ch);
    }

    // return a expression without parentheses if start and end has close and end
    private static String getExpressionWithoutOuterParentheses(String expression) {

        // check for not enough length
        if (Constants.MIN_LEN_PARENTHESES >= expression.length()) {
            return expression;
        }

        // check if open and close parenthesis is at both ends
        if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == expression.charAt(Constants.BEGIN_EXPRESSION_INDEX) &&
        OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == expression.charAt(expression.length()-1)) {
            return expression.substring(Constants.FIRST_EXPRESSION_INDEX, expression.length()-1);
        }
        return expression;
    }

    // find the main operator in the expression
    public static int findMainOperator(String expression) {

        // ignore parentheses on both ends if there is open and close parentheses
        int minPrecedence = Integer.MAX_VALUE;
        int mainOperatorIndex = Constants.INIT_MAIN_OPERATOR_INDEX;
        int parenthesesCount = Constants.INIT_PARENTHESES_COUNT;

        // iterate through expression to locate main operator
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == ch) {
                parenthesesCount++;
            } else if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == ch) {
                parenthesesCount--;
            } else if (OperatorPriorityTable.isOperator(ch) && parenthesesCount == Constants.INIT_PARENTHESES_COUNT) {
                int precedence = OperatorPriorityTable.getPriority(ch);
                
                // compare priority
                if (precedence <= minPrecedence) {
                    minPrecedence = precedence;
                    mainOperatorIndex = i;
                }
            }
        }
        return mainOperatorIndex;
    }

    // convert infix expression to prefix expression using recursion
    public static String infixToPrefix(String expression) {
        expression = expression.trim();

        // get expression without outer parentheses
        expression = getExpressionWithoutOuterParentheses(expression);

        // Base case: if the expression is a single operand
        if (Constants.FIRST_EXPRESSION_INDEX == expression.length() && isOperand(expression.charAt(Constants.BEGIN_EXPRESSION_INDEX))) {
            return expression;
        }

        // Identify the main operator with the lowest precedence
        int operatorIndex = findMainOperator(expression);
        char operator = expression.charAt(operatorIndex);

        // Split the expression into left and right sub-expressions
        String leftExpr = expression.substring(Constants.BEGIN_EXPRESSION_INDEX, operatorIndex);
        String rightExpr = expression.substring(operatorIndex + 1);

        // Recursively convert sub-expressions to prefix
        String leftPrefix = infixToPrefix(leftExpr);
        String rightPrefix = infixToPrefix(rightExpr);

        // Combine the operator with the converted sub-expressions
        return operator + leftPrefix + rightPrefix;
    }
}
