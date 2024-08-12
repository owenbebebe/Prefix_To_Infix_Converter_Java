package InfixToPrefixConverter.converter;

import InfixToPrefixConverter.model.ExpressionNode;
import InfixToPrefixConverter.model.OperatorPriorityTable;

import java.util.*;

public class TreeConverter extends ExpressionConverter {

    // convert an infix to prefix constructing a binary tree from symbol and node stacks
    public static String convertInfixToPrefix(String expression) {

        // check if expression is valid
        try {
            isValidExpression(expression);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // initiate variables
        Stack<Character> symbol = new Stack<>();
        Stack<ExpressionNode> node = new Stack<>();
        StringBuilder operandBuffer = new StringBuilder();

        // iterate through the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // push symbol stack if c is (
            if (OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() == c) {
                symbol.push(c);
            }

            // if ) keep on popping stack until ( is found
            else if (OperatorPriorityTable.CLOSE_PARENTHESIS.getSymbol() == c) {

                // push operand builder to node stack and reset
                if(!operandBuffer.isEmpty()) {
                    node.push(new ExpressionNode(operandBuffer.toString()));
                    operandBuffer.setLength(0);
                }

                while (!symbol.isEmpty() &&
                        OperatorPriorityTable.OPEN_PARENTHESIS.getSymbol() !=
                                symbol.peek()) {

                    // create a new subtree with operator as parent
                    // right child as first pop node as left as second pop
                    node.push(ExpressionNode.createSubTree(
                            symbol.pop(),
                            node.pop(),
                            node.pop()));
                }

                // remove (
                symbol.pop();
            }

            // if c is an operator push it onto the stack
            else if (OperatorPriorityTable.isOperator(c)) {

                // push operand builder to node stack and reset
                if(!operandBuffer.isEmpty()) {
                    node.push(new ExpressionNode(operandBuffer.toString()));
                    operandBuffer.setLength(0);
                }

                // construct a subtree if the top of the symbol stack is not empty and has a higher priority
                while (!symbol.isEmpty() &&
                        OperatorPriorityTable.getPriority(symbol.peek()) >=
                OperatorPriorityTable.getPriority(c)) {

                    // create a new subtree with operator as parent
                    // right child as first pop node as left as second pop
                    node.push(ExpressionNode.createSubTree(
                            symbol.pop(),
                            node.pop(),
                            node.pop()));
                }
                symbol.push(c);
            }

            // if c is an operand
            else {
                operandBuffer.append(c);

                // if c is at last push it to node stack
                if (expression.length()-1 == i) {
                    node.push(new ExpressionNode(operandBuffer.toString()));
                }
            }
        }

        // pop the rest of the symbols and construct a tree
        while (!symbol.isEmpty()) {
            node.push(ExpressionNode.createSubTree(
                    symbol.pop(),
                    node.pop(),
                    node.pop()));
        }

        return ExpressionNode.buildStringWithPreorderedTraversal(node.peek());
    }
}
