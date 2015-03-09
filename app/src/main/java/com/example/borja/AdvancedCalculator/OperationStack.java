package com.example.borja.AdvancedCalculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

import static com.example.borja.AdvancedCalculator.CalculatorArithmetic.Operations;

/**
 * Created by jvilar on 13/01/15.
 */
public class OperationStack {
    private static ParenthesisNode parenthesis = new ParenthesisNode();

    private static class Node { }

    private static class OperationNode extends Node {
        Operations operation;

        private OperationNode(Operations operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            switch (operation){
                case Addition: return "+";
                case Subtraction: return "-";
                case Product: return "*";
                case Division: return "/";
            }
            return super.toString();
        }
    }

    private static class NumberNode extends Node {
        BigDecimal value;

        private NumberNode(BigDecimal value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private static class ParenthesisNode extends Node {
        @Override
        public String toString() {
            return "(";
        }
    }

    private Operations topOperation;
    private BigDecimal topNumber;
    private Deque<Node> content;

    public OperationStack() {
        this.content = new ArrayDeque();
    }

    public OperationStack(String s) {
        this();
        if (s.charAt(0) != '[')
            throw new IllegalArgumentException("The initial character of the stack must be a [");
        if (s.charAt(s.length()-1) != ']')
            throw new IllegalArgumentException("The last character of the stack must be a ]");

        String[] parts = s.substring(1, s.length()-1).split(",");

        for (int i = parts.length - 1; i >= 0; i--) {
            String part = parts[i];

            switch (part) {
                case "+":
                    push(Operations.Addition);
                    break;
                case "-":
                    push(Operations.Subtraction);
                    break;
                case "*":
                    push(Operations.Product);
                    break;
                case "/":
                    push(Operations.Division);
                    break;
                case "(":
                    pushParenthesis();
                    break;
                default:
                    try {
                        push(new BigDecimal(part));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("The string \"" + part + "\"is not a correct element of the stack");
                    }
                    break;
            }
        }
    }


    public int size() {
        return content.size();
    }


    public void push(BigDecimal value) {
        content.push(new NumberNode(value));
        topNumber = value;
    }

    public void push(Operations operation) {
        content.push(new OperationNode(operation));
        topOperation = operation;
    }

    public void pushParenthesis () {
        content.push(parenthesis);
        topOperation = null;
    }

    public void clear() {
        content.clear();
        topOperation = null;
        topNumber = null;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public BigDecimal popNumber() {
        Node node = popNode();
        return getNumber(node);
    }

    public Operations popOperation() {
        Node node = popNode();
        return getOperation(node);
    }

    public void popParenthesis() {
        Node node = popNode();
        if (node instanceof ParenthesisNode)
            return;
        throw new ClassCastException("The top of the stack is not a parenthesis, it is " + node.toString());
    }

    private Node popNode() {
        if (content.isEmpty())
            throw new EmptyStackException();
        Node node = content.pop();
        if (node instanceof NumberNode)
            updateTopNumber();
        else if (node instanceof OperationNode)
            updateTopOperation();
        else if (node instanceof ParenthesisNode)
            updateTopOperation();
        return node;
    }

    private Operations getOperation(Node node) {
        if (node instanceof OperationNode) {
            return ((OperationNode) node).operation;
        }
        throw new ClassCastException("The top of the stack is not an operation, it is " + node.toString());
    }

    private BigDecimal getNumber(Node node) {
        if (node instanceof NumberNode) {
            return ((NumberNode) node).value;
        }
        throw new ClassCastException("The top of the stack is not a number, it is " + node.toString());
    }

    private void updateTopNumber() {
        topNumber = null;
        for (Node node : content)
            if (node instanceof NumberNode) {
                topNumber = ((NumberNode) node).value;
                break;
            }
    }

    private void updateTopOperation() {
        topOperation = null;
        for (Node node : content)
            if (node instanceof OperationNode) {
                topOperation = ((OperationNode) node).operation;
                break;
            } else if (node instanceof ParenthesisNode)
                break;
    }

    public BigDecimal peekNumber() {
        Node node = peekNode();
        return getNumber(node);
    }

    public Operations peekOperation() {
        Node node = peekNode();
        return getOperation(node);
    }

    private Node peekNode() {
        if (content.isEmpty())
            throw new EmptyStackException();
        return content.peek();
    }

    public Operations getTopOperation() {
        return topOperation;
    }

    public BigDecimal getTopNumber() {
        return topNumber;
    }

    public boolean isTopParenthesis() {
        return !content.isEmpty() && content.peekFirst() instanceof ParenthesisNode;
    }

    public boolean isTopNumber() {
        return !content.isEmpty() && content.peekFirst() instanceof NumberNode;
    }

    public boolean isTopOperation () {
        return !content.isEmpty() && content.peekFirst() instanceof OperationNode;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        String comma = "";
        for (Node node : content) {
            result.append(comma);
            result.append(node.toString());
            comma = ",";
        }
        result.append("]");
        return result.toString();
    }
}
