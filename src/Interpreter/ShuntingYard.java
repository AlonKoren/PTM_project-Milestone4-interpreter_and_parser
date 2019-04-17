package Interpreter;

import java.util.Stack;

public class ShuntingYard implements Interpreter {


    @Override
    public Double calculateExpression(String[] expression) {
        return evaluatePostfix(calculatePostfix(expression));
    }

    private String[] calculatePostfix(String[] expression) {
        return null;
    }

    public Double evaluatePostfix(String[] exp) {
        //create a stack
        Stack<Expression> stack = new Stack<>();

        // Scan all characters one by one
        for (String s : exp) {
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (isANumber(s))
                stack.push(new Number(Double.parseDouble(s)));

                //  If the scanned character is an operator, pop two
                //  elements from stack apply the operator
            else {
                double val1 = stack.pop().calculate();
                double val2 = stack.pop().calculate();

                switch (s) {
                    case "+":
                        stack.push(new Plus(new Number(val2), new Number(val1)));
                        break;

                    case "-":
                        stack.push(new Minus(new Number(val2), new Number(val1)));
                        break;

                    case "/":
                        stack.push(new Divide(new Number(val2), new Number(val1)));
                        break;

                    case "*":
                        stack.push(new Multiply(new Number(val2), new Number(val1)));
                        break;
                }
            }
        }
        return stack.pop().calculate();
    }

    private boolean isANumber(String word) {
        try {
            Double.parseDouble(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
