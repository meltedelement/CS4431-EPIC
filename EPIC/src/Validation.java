import java.util.ArrayList;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, and minumum expression parameters, and valid expression for 1 more operand than operator

public class Validation{

    //array list of operators - used in checkCharacters & checkExpression, and list of expression's brackets
    public static ArrayList<Character> operators = new ArrayList<>();
    public static char[] charOperators = {'+', '-', '*', '/', '^', ' ', '(',')','>','.'};
    public static ArrayList<Character> bracketList = new ArrayList<>();

    
    //the throws InterruptedException is to allow use of Thread.sleep - (the exception is thrown if the Thread is interrupted WHEN sleeping) -- delay required as error printing is slower than printing, leading to an incorrect order in the terminal
    public static boolean isValid(String s) throws InterruptedException {
        //&& logic only returns true if ALL are true
        return checkCharacters(s) && checkBrackets(s) && checkExpression(s);
    }

    //checks that only operands and operators
    private static boolean checkCharacters(String s) throws InterruptedException{
        //for eacch character in charOperators... add to array list
        for (char c : charOperators) {
            operators.add(c);
        }

        int characterValid = 0; //only valid if = 0
    
        //for loop each char of string, check if numbers, operator, else valid = false
        for (int i = 0; i < s.length(); i++){
            if (!(Character.isDigit(s.charAt(i)) || operators.contains(s.charAt(i)))){      
                characterValid++;
            } 
        }

        if (characterValid != 0){
            System.err.println("Invalid character(s) in expression - Requires only intergers, decimals, brackets and operators.");
            Thread.sleep(500);
            return false;
        }
        return true;
    }

    //checks number of ( = number of )
    private static boolean checkBrackets(String s) throws InterruptedException{
        int bracketsPairsBalanced = 0;

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                bracketsPairsBalanced++;
            } else if (s.charAt(i) == ')'){
                bracketsPairsBalanced--;
            }
        }

        if (bracketsPairsBalanced != 0){
            System.err.println("Unbalanced brackets in expression - Requires a matching closing bracket for each open bracket.");
            Thread.sleep(500);
            return false;
        } 

        //fill array list bracketList with brackets from exoression
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == ')'){
                bracketList.add(s.charAt(i));
            }
        }
    
        try {
            //if first bracket is ), invalid
            if(bracketList.getFirst() == ')'){
                System.err.println("Invalid brackets in expression - Expression cannot begin with a closed bracket.");
                Thread.sleep(500);
                return false;
            }

            //if last bracket is (, invalid
            if(bracketList.getFirst() == '('){
                System.err.println("Invalid brackets in expression - Expression cannot end with an open bracket.");
                Thread.sleep(500);
                return false;
            }
        } catch (Exception e) {
            //handling isnt necessary as a bracketless expression isnt a problem
        }
        

        //if empty brackets, invalid
        if (s.contains("()")){
            System.err.println("Invalid brackets in expression - No empty brackets permitted.");
            Thread.sleep(500);
            return false;
        }
        return true;
    }

    //method to check that there is at least two operands with at least 1 operator between
    private static boolean checkExpression(String s) throws InterruptedException{
        
        //researched how to remove brackets and replace parts of strins with something else
        try {
             //replace )( with *
             String regexMultiply = "\\)\\(";
             s = s.replaceAll(regexMultiply, "*");
 
             //replace ( and ) with blanks
             String regexOpenBracket = "\\(";
             s = s.replaceAll(regexOpenBracket, "");
         
             String regexClosedBracket = "\\)";
             s = s.replaceAll(regexClosedBracket, "");
        } catch (Exception e) {
            //not a problem if no brackets so no error
        }

        if (s.equals("")){
            System.err.println("Invalid expression - Requires a minimum of two operands with one operator between them.");
            Thread.sleep(500);
            return false;
        }

        ParseStringExpression.splitEquation(s, "+-*/^");
        ArrayList<Double> skyeOperands = ParseStringExpression.operands;
        ArrayList<String> skyeOperators = ParseStringExpression.operators;
        int operatorCounter = skyeOperators.size();
        int operandCounter = skyeOperands.size();

        //change to with at least 1 opertaro 2 operands
        if (operandCounter != operatorCounter + 1 || operandCounter < 2 || operatorCounter < 1){
            System.err.println("Invalid expression - Requires a minimum of two operands with one operator between them.");
            Thread.sleep(500);
            return false;
        }
        return true;
    }
}

//for other places:

/*


if (!Validation.isValid(userIn)){
    System.err.println("Expression is invalid");
}


*/

// GRAVEYARD

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

 // // attempts to change )( to *:

            // if((i < s.length() - 1 && s.charAt(i) == ')') || (i < (s.length() - 1) && s.charAt(i+1) == '(')){
            //     System.out.println("adding the multiply thing");
            //     bracketlessString.append("*");
            //}

            // //if ")(", then replace with a "*"
            // try {
            //     if( s.charAt(i) == ')' && s.charAt(i+1) == '('){
            //         System.out.println("adding the multiply thing");
            //         bracketlessString.append('*');
            //     }
            //  } catch (IndexOutOfBoundsException e) {
            //      // TODO: handle exception
            //  }

            // for (int i = 0; i < s.length(); i++){

        //     //researched how to remove brackets from a string because skyes file doesnt handle brackets - decided to use stringbuilder
        // StringBuilder bracketlessString = new StringBuilder();
            
            //     if(s.charAt(i) != '(' && s.charAt(i) != ')'){
            //         //.append = add, so, if the char is not a bracket, it will be added to bracketlessString.
            //         System.out.println("adding the number");
            //         bracketlessString.append(s.charAt(i));
            //     }
            // }