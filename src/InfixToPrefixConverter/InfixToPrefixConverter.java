package InfixToPrefixConverter;

import InfixToPrefixConverter.converter.BubbleConverter;
import InfixToPrefixConverter.converter.RecursiveConverter;
import InfixToPrefixConverter.converter.StackConverter;
import InfixToPrefixConverter.converter.TreeConverter;
import InfixToPrefixConverter.model.OperatorPriorityTable;
import InfixToPrefixConverter.util.Constants;

import java.util.*;

public class InfixToPrefixConverter {

    public String runTreeConverter(String expression) {
        return TreeConverter.convertInfixToPrefix(expression);
    }

    public String runStackConverter(String expression) {
        return StackConverter.convertInfixToPrefix(expression);
    }

    // this is not a proper algorithm
    public String runBubbleConverter(String expression) {
        return BubbleConverter.convertInfixToPreFix(expression);
    }

    public String runRecursiveConverter(String expression) {
        return RecursiveConverter.convertInfixToPreFix(expression);
    }
}
