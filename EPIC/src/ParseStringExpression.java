// System.out.println("Enter an expression to be evaluated!");
// String userIn = scan.nextLine();
// double result = ParseStringExpression.evaluate(userIn, "^*/+-");
// System.out.println("Answer is: " + result + " Wooooooooooooooooooooooooooooooooooo!");

import java.util.ArrayList;

public abstract class ParseStringExpression {

    private static ArrayList<String> operators = new ArrayList<String>();
    private static ArrayList<Double> operands = new ArrayList<Double>();

    private static void splitEquation(String expression, String orderOfOperations) {
        // This method splits an equation into a list of operators, and a list of operands.

        int startIndex = -1; // index of the start of an operand

        // loop through every character in the expression
        for (int i = 0; i < expression.length(); i++) {
            String s = Character.toString(expression.charAt(i));

            if (orderOfOperations.contains(s)) { // if the character is an operator
                // it will NOT be added if it is at index 0, or the previous character was an operator also.
                if ( !( i==0  || orderOfOperations.contains(Character.toString(expression.charAt(i - 1))) ) ) {
                    operators.add(s);
                } else { // if the operator represents a negative, it does not need to be added to the list
                    continue;
                }

                // for every operator, everything between it and the last one is an operand
                String between = expression.substring(startIndex+1, i);
                operands.add(Double.valueOf(between));
                startIndex = i;
            }
        }
        String last = expression.substring(startIndex+1);
        operands.add(Double.valueOf(last));
    }


    public static double evaluate(String expression, String orderOfOperations) {
        expression = expression.replaceAll("\\s",""); // remove all whitespace
        // This is the main method takes an input string expression without brackets and evaluates it.
        splitEquation(expression, orderOfOperations);
        if (operators.size() + 1 != operands.size()) {
            System.out.println("Error evaluating expression, disallowed sets of operands and operators...");
        }

        for (int i=0; i<orderOfOperations.length(); i++) { // "^*/+-"
            String operator = String.valueOf(orderOfOperations.charAt(i));
            while (operators.contains(operator)) {
                int index = operators.indexOf(operator);
                double result = Calculate.calc(operator, operands.get(index), operands.get(index+1));
                operands.remove(index);
                operands.set(index, result);
                operators.remove(operator);
            }
        }

        return operands.get(0);
    }
}
