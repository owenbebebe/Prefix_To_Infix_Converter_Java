import InfixToPrefixConverter.InfixToPrefixConverter;
import InfixToPrefixConverter.converter.ExpressionConverter;
import InfixToPrefixConverter.converter.StackConverter;
import InfixToPrefixConverter.converter.TreeConverter;
import InfixToPrefixConverter.util.ExpressionGenerator;

import java.util.*;

public class Main {

    // constants area
    private static final String OPERATORS = "+-*/";
    private static final String DIGITS = "0123456789";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {

        InfixToPrefixConverter infixToPrefixConverter = new InfixToPrefixConverter();

        // generate 10 test cases and print out
//        for(int i = 0; i < 10; i++) {
//            String testCase = ExpressionGenerator.generateExpression(RANDOM.nextInt(20)+3);
//            System.out.println("test case: " + testCase);
//            System.out.println(infixToPrefixConverter.runTreeConverter(testCase));
//            System.out.println(infixToPrefixConverter.runStackConverter(testCase));
//            System.out.println(infixToPrefixConverter.runBubbleConverter(testCase));
//        }

        System.out.println(infixToPrefixConverter.runTreeConverter("0/2/1-1*7*(3+1)-1"));
        System.out.println(infixToPrefixConverter.runStackConverter("0/2/1-1*7*(3+1)-1"));
        System.out.println(infixToPrefixConverter.runRecursiveConverter("0/2/1-1*7*(3+1)-1"));
    }
}