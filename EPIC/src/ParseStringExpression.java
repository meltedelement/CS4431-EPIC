/*
Explanation for how this works:
 - First of all it will take the input expression, and create a list of operators (symbols) and operands (numbers)
   This is achieved by finding 2 operators from a list of valid ones, then setting everything in between to be an operand.

 - Next, it actually performs the operations. It looks at the list of operators and finds the one of highest importance.
   It will then take that operator, and the 2 corresponding operands using the same index and index+1, and call "Calculate"
   Calculate will take the two numbers, recognise the symbol as a string and actually perform the operation.

 - The evaluate method then removes the operator from its list and changes the 2 operands to the calculation result.
   It also prints out the calculation that was performed to show order of operations working.
   When there are no more operators to apply, there will only be one operand in the list and that will be returned.
*/
import java.util.ArrayList;
public abstract class ParseStringExpression {

    public static ArrayList<String> operators = new ArrayList<String>();
    public static ArrayList<Double> operands = new ArrayList<Double>();

    public static void splitEquation(String expression, String orderOfOperations) {
        // This method splits the equation into a list of operators, and a list of operands.
        // It is private because it should only be called from within this class, in evaluate.

        int operatorIndex = -1; // index of the start of an operand

        // loop through every single character in the expression on its own
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
                String between = expression.substring(operatorIndex+1, i);
                operands.add(Double.valueOf(between));
                operatorIndex = i;
            }
        }
        String last = expression.substring(operatorIndex+1);
        operands.add(Double.valueOf(last));
    }


    public static double evaluate(String expression) {
        operands.clear();
        operators.clear();
        expression = expression.replaceAll("\\s",""); // remove all whitespace
        // This is the main method takes an input string expression without brackets and evaluates it.
        String[] orderOfOperations = {"^", "*/", "+-"}; // Can be changed for additional operators
        splitEquation(expression, String.join("", orderOfOperations));
        if (operators.size() + 1 != operands.size()) {
            System.out.println("Error evaluating expression, disallowed sets of operands and operators...");
        }
        // loop through each operator in order of importance
        for (int i=0; i<orderOfOperations.length; i++) {
            String precedence = orderOfOperations[i];

            if (precedence.length() == 1) {
                String thisOperator = String.valueOf(precedence.charAt(0));
                // while the list of operators contains the one we're checking
                while (operators.contains(thisOperator)) {
                    int index = operators.indexOf(thisOperator);
                    double result = Calculator.calc(thisOperator, operands.get(index), operands.get(index+1));
                    operands.remove(index);
                    // when you remove the first operand, the second one is now at the original index
                    operands.set(index, result); // replace second operand with result variable
                    operators.remove(thisOperator);
                }
            } else if (precedence.length() == 2) {
                String firstOperator = String.valueOf(precedence.charAt(0));
                String secondOperator = String.valueOf(precedence.charAt(1));

                while (operators.contains(firstOperator) || operators.contains(secondOperator)) {
                    if (operators.indexOf(secondOperator)==-1 || (operators.indexOf(secondOperator) > 0
                            && operators.indexOf(firstOperator) < operators.indexOf(secondOperator)) ) {
                        // if the first operator is before the second one when read left to right

                        int index = operators.indexOf(firstOperator);
                        double result = Calculator.calc(firstOperator, operands.get(index), operands.get(index+1));
                        operands.remove(index);
                        // when you remove the first operand, the second one is now at the original index
                        operands.set(index, result); // replace second operand with result variable
                        operators.remove(firstOperator);
                    } else {
                        int index = operators.indexOf(secondOperator);
                        double result = Calculator.calc(secondOperator, operands.get(index), operands.get(index+1));
                        operands.remove(index);
                        // when you remove the first operand, the second one is now at the original index
                        operands.set(index, result); // replace second operand with result variable
                        operators.remove(secondOperator);
                    }
                }
            }
        }
        return operands.get(0);
    }

}