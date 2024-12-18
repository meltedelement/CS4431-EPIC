import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, and minumum expression parameters, and valid expression for 1 more operand than operator

public class Validation{

    //array list of operators - used in checkCharacters & checkExpression, and list of expression's brackets
    public static char[] charOperators = {'+', '-', '*', '/', '^', ' ', '(',')','>','.'};
    public static Set<Character> charOperatorSet = new HashSet<>(Arrays.asList('+', '-', '*', '/', '^', ' ', '(', ')', '>', '.'));
    public static ArrayList<Character> operators = new ArrayList<>();
    public static ArrayList<Character> bracketList = new ArrayList<>();

    //the throws InterruptedException is to allow use of Thread.sleep - (the exception is thrown if the Thread is interrupted WHEN sleeping) -- delay required as error printing is slower than printing, leading to an incorrect order in the terminal
    public static boolean isValid(String s) throws InterruptedException {
        //&& logic only returns true if ALL are true
        return checkCharacters(s) && checkBrackets(s) && checkExpression(s);
    }

    public static boolean checkAdjacentOperators(String s){
        int minuscount = 0;
        for (int x = 1; x < s.length(); x++){
            char prev = s.charAt(x-1);
            char curr = s.charAt(x);
        
        
            if (prev == '-' && curr == '-'){
                minuscount++;
            }
            else{
                minuscount = 0;
            }

            if (charOperatorSet.contains(prev) && charOperatorSet.contains(curr) && curr != '-' ) {
                return false;
            }

            else if (minuscount >= 2){
                return false;
            }
        }
        
    return true;
    }

    //checks that only operands and operators
    private static boolean checkCharacters(String s) throws InterruptedException{
        //for eacch character in charOperators... add to array list
        //array list is easier than array (using .contains)
        for (char c : charOperators) {
            operators.add(c);
        }

        boolean characterValid = true;
    
        //for loop each char of string, check if numbers, operator, else valid = false
        for (int i = 0; i < s.length(); i++){
            if (!(Character.isDigit(s.charAt(i)) || operators.contains(s.charAt(i)))){      
                characterValid = false;
            } 
        }

        if (!characterValid){
            System.err.println("Invalid character(s) in expression - Only intergers, decimals, brackets and operators permitted");
            Thread.sleep(500);
            return false;
        }
        return true;
    }
    
    private static boolean checkBrackets(String s) throws InterruptedException{
        //checks number of ( = number of )
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

        //checking bracket positions/combinations
        //fill array list bracketList with brackets from exoression
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(' || s.charAt(i) == ')'){
                bracketList.add(s.charAt(i));
            }
        }
    
        try {
            //if first bracket is ), invalid
            if(bracketList.getFirst() == ')'){
                System.err.println("Invalid brackets in expression.");
                Thread.sleep(500);
                return false;
            }
            //if last bracket is (, invalid
            if(bracketList.getLast() == '('){
                System.err.println("Invalid brackets in expression.");
                Thread.sleep(500);
                return false;
            }
        }
        //handling isnt necessary as a bracketless expression isnt a problem
        catch (Exception e) {}
        
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
        
        if (s.equals("")){
            System.err.println("Invalid expression - Requires a minimum of two operands with one operator between them.");
            Thread.sleep(500);
            return false;
        }

        //to use ParseStringExpression, i have to remove brackets, but also consider )( as *
        //researched how to remove brackets and replace parts of strins with something else
        try {
            String regexWhiteSpace = "\\s";
             s = s.replaceAll(regexWhiteSpace, "");
             //replace )( with *
             String regexMultiply = "\\)\\(";
             s = s.replaceAll(regexMultiply, "*");
 
             //replace ( and ) with blanks
             String regexOpenBracket = "\\(";
             s = s.replaceAll(regexOpenBracket, "");
         
             String regexClosedBracket = "\\)";
             s = s.replaceAll(regexClosedBracket, "");
        } catch (Exception e) {
            //not a problem if no brackets so no handling needed
        }

        int operatorCounter = 0;
        int operandCounter = 0;
        boolean readingOperand = false;

        for (int i = 0; i < s.length(); i++) {
            //if character at i is in arraylist operators
            if (operators.contains(s.charAt(i))) { 
                operatorCounter++;
                readingOperand = false;
                //adds to operator counter
                //reading operand false
            //if character at i is an operand & its not already reading a long operand
            } else if (Character.isDigit(s.charAt(i)) && !readingOperand) {
                operandCounter++;
                readingOperand = true;
                //adds to operand counter
                //reading operand true
            }
        }

        //check if at least 1 operator w/ 2 operands
        if (operandCounter != operatorCounter + 1 || operandCounter < 2 || operatorCounter < 1){
            System.err.println("Invalid expression - Requires a minimum of two operands with one operator between them.");
            Thread.sleep(500);
            return false;
        }
        return true;
    }
}