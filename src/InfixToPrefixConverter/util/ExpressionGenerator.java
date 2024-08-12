package InfixToPrefixConverter.util;
import java.util.Random;
import java.util.Stack;

public class ExpressionGenerator {

    private static final char[] OPERATORS = {'+', '-', '*', '/'};
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(generateExpression(20)); // Example usage
        }
    }

    public static String generateExpression(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be at least 1");
        }

        StringBuilder expression = new StringBuilder();
        Stack<Character> parenthesesStack = new Stack<>();

        boolean canHaveOperator = false;
        boolean canHaveDigit = true;
        boolean canHaveOpenParenthesis = true;
        boolean canHaveCloseParenthesis = false;

        for (int i = 0; i < length; i++) {
            if (canHaveDigit && (i == 0 || RANDOM.nextBoolean())) {
                expression.append(RANDOM.nextInt(3));
                canHaveOperator = true;
                canHaveDigit = false;
                canHaveOpenParenthesis = true;
                canHaveCloseParenthesis = !parenthesesStack.isEmpty();
            } else if (canHaveOperator) {
                if (canHaveCloseParenthesis && !parenthesesStack.isEmpty() && RANDOM.nextBoolean()) {
                    expression.append(')');
                    parenthesesStack.pop();
                    canHaveOperator = true;
                    canHaveDigit = false;
                    canHaveOpenParenthesis = false;
                    canHaveCloseParenthesis = !parenthesesStack.isEmpty();
                } else {
                    expression.append(OPERATORS[RANDOM.nextInt(OPERATORS.length)]);
                    canHaveOperator = false;
                    canHaveDigit = true;
                    canHaveOpenParenthesis = true;
                    canHaveCloseParenthesis = false;
                }
            } else if (canHaveOpenParenthesis && RANDOM.nextBoolean()) {
                expression.append('(');
                parenthesesStack.push('(');
                canHaveOperator = false;
                canHaveDigit = true;
                canHaveOpenParenthesis = true;
                canHaveCloseParenthesis = false;
            } else if (canHaveCloseParenthesis && !parenthesesStack.isEmpty()) {
                expression.append(')');
                parenthesesStack.pop();
                canHaveOperator = true;
                canHaveDigit = false;
                canHaveOpenParenthesis = false;
                canHaveCloseParenthesis = !parenthesesStack.isEmpty();
            } else {
                expression.append(RANDOM.nextInt(10));
                canHaveOperator = true;
                canHaveDigit = false;
                canHaveOpenParenthesis = false;
                canHaveCloseParenthesis = !parenthesesStack.isEmpty();
            }
        }

        // delete every thing until parentheses stack is empty
        while (!parenthesesStack.isEmpty()) {
            char lastChar = expression.charAt(expression.length()-1);
            expression.append(')');
            parenthesesStack.pop();
        }

        // Ensure the expression does not end with an operator
        while (!expression.isEmpty() && isOperator(expression.charAt(expression.length() - 1))) {
            expression.setLength(expression.length() - 1);
        }

        return expression.toString();
    }

    private static boolean isOperator(char c) {
        for (char op : OPERATORS) {
            if (c == op) {
                return true;
            }
        }
        return false;
    }
}
