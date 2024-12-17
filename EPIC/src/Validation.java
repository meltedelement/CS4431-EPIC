import java.util.ArrayList;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, 

public class Validation{

    //array list of operators - used in checkCharacters & checkExpression
    public static ArrayList<Character> operators = new ArrayList<>();
    public static char[] charOperators = {'+', '-', '*', '/', '^', ' ', '(',')','>','.'};
    
    public static boolean isValid(String s) {
        //&& logic only returns true if BOTH are true
        return checkCharacters(s) && checkBrackets(s) && checkExpression(s);
    }
   
    //checks only digits(operands) and operators
    private static boolean checkCharacters(String s){
        //for loop each char of string, check if numbers, operator, else valid = false

        for (char c : charOperators) {
            operators.add(c);
        }

        int characterInvalid = 0; //only valid if = 0
    
        for (int i = 0; i < s.length(); i++){
            if (!(Character.isDigit(s.charAt(i)) || operators.contains(s.charAt(i)))){      //should this be || or && ?
                characterInvalid++;
            } 
        }

        if (characterInvalid != 0){
            System.out.println("Returning false for checkCharacters validity");
            return false;
        }
        System.out.println("Returning true for checkCharacters validity");
        return true;
    }

    //checks number of ( = number of )
    private static boolean checkBrackets(String s){
        int bracketsPairsBalanced = 0;

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                bracketsPairsBalanced++;
            } else if (s.charAt(i) == ')'){
                bracketsPairsBalanced--;
            }
        }

        if (bracketsPairsBalanced != 0){
            System.out.println("Returning false for checkBrackets validity");
            return false;
        } 
        System.out.println("Returning true for checkBrackets validity");
        return true;
    }

    //method to check that there is at least two operands with at least 1 operator between
    private static boolean checkExpression(String s){

        // skye thing cant deal with brackets ahhHhhHHhh
        
        //researched how to remove brackets from a string
        StringBuilder bracketlessString = new StringBuilder();

        //i have to for loop through string to remove brackets
        for (int i = 0; i < s.length(); i++){

            if(s.charAt(i) != '(' && s.charAt(i) != ')'){
                //.append = add, so, if the char is not a bracket, it will be added to bracketlessString.
                System.out.println("adding the number");
                bracketlessString.append(s.charAt(i));
            }

            //if ")(", then replace with a "*"
            if(i < (s.length() - 1) && (s.charAt(i) == ')' || s.charAt(i+1) == '(')){
                System.out.println("adding the multiply thing");
                bracketlessString.append("*");
            }
        }

        s = bracketlessString.toString();

        ParseStringExpression.splitEquation(s, "+-*/");
        ArrayList<Double> skyeOperands = ParseStringExpression.operands;
        ArrayList<String> skyeOperators = ParseStringExpression.operators;
        int operatorCounter = skyeOperators.size();
        int operandCounter = skyeOperands.size();

        System.out.println(s + " " + skyeOperands + " " + skyeOperators);
        //change to with at least 1 opertaro 2 operands
        if (operandCounter != operatorCounter + 1 || operandCounter < 2 || operatorCounter < 1){
            System.out.println("Returning false for checkExpression validity");
            return false;
        }
        System.out.println("Returning true for checkExpression validity");
        return true;
    }
}

//for other places:

/*


if (!Validation.isValid(userIn)){
    System.err.println("Expression is invalid");
}


*/


//original checkExpression()
//this is incredibly flawed as it cannot deal with negative numbers (double operators)

        // // if theres one more operand than operator then its all g
        // for(int i = 0; i < s.length(); i++){
        //     if(operators.contains(s.charAt(i))){
        //         operatorCounter--;
        //     }
        //     if(Character.isDigit(s.charAt(i))){
        //         operandCounter--;
        //     }
        // }

        // if (operatorCounter != 0 || operandCounter != 0){
        //     return true;
        // }
        // return false;