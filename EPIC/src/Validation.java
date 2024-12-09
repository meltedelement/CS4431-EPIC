import java.util.ArrayList;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, 

public class Validation{

    public static boolean isValid(String s) {
        //&& logic only returns true if BOTH are true
        return checkCharacters(s) && checkBrackets(s);
    }
   
    //checks only digits(operands) and operators
    private static boolean checkCharacters(String s){
        //for loop each char of string, check if numbers, operator, else valid = false

        ArrayList<Character> operators = new ArrayList<>();
        char[] charOperators = {'+', '-', '*', '/', '^', ' ', '(',')'};
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
            return false;
        }

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