package InfixToPrefixConverter.model;

public class ExpressionNode {

    private String value;
    private ExpressionNode left, right;

    public ExpressionNode(String value) {
        this.value = value;
        left = right = null;
    }

    // Method to set the left child
    public void setLeft(ExpressionNode left) {
        this.left = left;
    }

    // Method to set the right child
    public void setRight(ExpressionNode right) {
        this.right = right;
    }

    // Create a subtree with operator as parent
    public static ExpressionNode createSubTree(char operator,
                                                ExpressionNode right,
                                                ExpressionNode left) {
        ExpressionNode node = new ExpressionNode(Character.toString(operator));
        node.left = left;
        node.right = right;
        return node;
    }

    // initiator for preorder traversal to build a string from the traversal
    public static String buildStringWithPreorderedTraversal(ExpressionNode root) {
        StringBuilder result = new StringBuilder();
        buildStringWithPreorderedTraversalHelper(root, result);
        return result.toString();
    }

    // recursive function call for preorder traversal to append a string
    private static void buildStringWithPreorderedTraversalHelper (ExpressionNode node,
                                                                  StringBuilder result) {
        if (null == node) {
            return;
        }

        // Visit the root node first (preorder)
        result.append(node.value);

        // Recursively traverse the left subtree
        buildStringWithPreorderedTraversalHelper(node.left, result);

        // Recursively traverse the right subtree
        buildStringWithPreorderedTraversalHelper(node.right, result);
    }
}
