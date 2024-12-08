import java.util.ArrayList;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, 



public class Validation{
    public boolean valid = true;

    public boolean isValid(String s) {
        checkCharacters(s);
        checkBrackets(s);
        return valid;
    }

    //checks no letters
    private void checkCharacters(String s){
        //for loop each char of string, check if numbers, operator, else valid = false

        ArrayList<Character> operators = new ArrayList<>();
        char[] charOperators = {'+', '-', '*', '/', '^'};
        for (char c : charOperators) {
        operators.add(c);
        }
    
        for (int i = 0; i < s.length(); i++){
        if (!(Character.isDigit(s.charAt(i)) || operators.contains(s.charAt(i)))){
                valid = false;
            }   
        }
    }

    //checks number of ( = number of )
    private void checkBrackets(String s){

        char openBracket = '(';
        char closedBracket = ')';

        int bracketsPairsBalanced = 0;

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == openBracket){
                bracketsPairsBalanced++;
            } else if (s.charAt(i) == closedBracket){
                bracketsPairsBalanced--;
            }
        }

        if (bracketsPairsBalanced != 0){
            valid = false;
        }
    }
}

//for other places:

/*


if (!Validation.isValid(userIn)){
    System.err.println("Expression is invalid");
}


*/