package InfixToPrefixConverter.model;

public enum OperatorPriorityTable {

    OPEN_PARENTHESIS('(', 0),
    CLOSE_PARENTHESIS(')', 0),
    PLUS('+', 1),
    MINUS('-', 1),
    MULTIPLY('*', 2),
    DIVIDE('/', 2);

    private final char symbol;
    private final int priority;

    OperatorPriorityTable(char symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getPriority() {
        return priority;
    }

    // loop through enums to return its priority: -1 if not an operator
    public static int getPriority(char symbol) {
        for (OperatorPriorityTable operatorPriorityPair:
                OperatorPriorityTable.values()) {
            if (operatorPriorityPair.symbol == symbol) {
                return operatorPriorityPair.priority;
            }
        }
        return -1;
    }

    // check if the symbol is an operator inside the enum table
    public static boolean isOperator(char symbol) {
        for (OperatorPriorityTable operatorPriorityPair:
                OperatorPriorityTable.values()) {
            if (operatorPriorityPair.symbol == symbol) {
                return true;
            }
        }
        return false;
    }
}
