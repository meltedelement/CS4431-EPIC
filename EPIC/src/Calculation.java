/*
- At this stage operators.length() == operands.length() -1
- Assign order of operations to the list of operators, then evaluate one after the other.
 */
import java.util.ArrayList;

public class Calculation {

    private String expression;
    private ArrayList<String> operators;
    private ArrayList<Integer> operands;

    public Calculation(String expression) {
        // initiate all instance variables
        this.expression = expression;
        operators = new ArrayList<String>();
        operands = new ArrayList<Integer>();
    }

    public ArrayList<String> getOperators() { return operators; }
    public void addOperator(String s) { operators.add(s); }
    public ArrayList<Integer> getOperands() { return operands; }
    public void addOperand(int i) { operands.add(i); }


    private void operAtorsAnds() {
        expression = expression.replaceAll("\\s",""); // remove all whitespace
        String operatorList = "+-*/^"; // list of the valid operators we can deal with

        int startIndex = -1; // index of the start of an operand
        boolean isNegative = false;

        // loop through every character in the expression
        for (int i = 0; i < expression.length(); i++) {
            String s = Character.toString(expression.charAt(i));

            if (operatorList.contains(s)) { // if the character is an operator

                // it will NOT be added if it is at index 0, or the previous character was an operator also.
                if ( !( i==0  || operatorList.contains(Character.toString(expression.charAt(i - 1))) ) ) {
                    addOperator(s);
                } else { // if the operator represents a negative, it does not need to be added to the list
                    isNegative = true;
                    continue;
                }

                // for every operator, everything between it and the last one is an operand
                String between = expression.substring(startIndex+1, i);
                addOperand(Integer.valueOf(between));
                startIndex = i;
                isNegative = false;
            }
        }
        String last = expression.substring(startIndex+1);
        addOperand(Integer.valueOf(last));

    }

    public int evaluate() {
        // This is the main method takes an input string expression without brackets and evaluates it.
        operAtorsAnds();
        if (getOperators().size() + 1 != getOperands().size()) {
            System.out.println("Error evaluating expression, disallowed sets of operands and operators...");
        }


        System.out.println(getOperators());
        System.out.println(getOperands());
        return 0;
    }

    public static void main(String[] args) {
        Calculation myCalc = new Calculation("-2 - -5 + 4 ^ -76 / 2 + -1");
        myCalc.evaluate();
    }

}
