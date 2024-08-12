package InfixToPrefixConverter.util;

public class Constants {
    public static final int MAX_STRING_LEN = 2000;
    public static final int BEGIN_EXPRESSION_INDEX = 0;
    public static final int INIT_PARENTHESES_COUNT = 0;
    public static final int INIT_MAIN_OPERATOR_INDEX = 0;
    public static final int FIRST_EXPRESSION_INDEX = 1;
    public static final int MIN_LEN_PARENTHESES = 3;
    public static final String INVALID_PARENTHESIS = "expression not fully parenthesized";
    public static final String INVALID_STRING = "string is either empty or too long";
    public static final String INVALID_FORMAT_DIGIT_INFRONT_OPEN_PARENTHESIS = "expression is invalid with digit in-front of an open parenthesis";
}
