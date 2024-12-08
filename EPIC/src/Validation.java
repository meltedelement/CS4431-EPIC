import java.util.ArrayList;

//what needs to be checked: no letters, no symbols (other than operators), brackets are finished, 



public class Validation{
    public boolean valid = true;


    //checks no letters
    private void checkCharacters(String s){
        //for loop each char of string, check if numbers, operator, else valid = false

        ArrayList<Character> operators = new ArrayList<>();
        char[] charOperators = {'+', '-', '*', '/', '^'};
        for (char c : charOperators) {
        operators.add(c);
        }
    
        for (int i = 0; i < s.length(); i++){
        if (!(Character.isDigit(s.charAt(i)) || operators.contains(s.charAt(i)))){      //should this be || or && ?
                valid = false;
            }   
        }
    }

    //input: "+", if (!(number or operator)), if (!(true false)), if(false true), called valid false for operators surely?

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

    public boolean isValid(String s) {
        checkCharacters(s);
        checkBrackets(s);
        return valid;
    }
    
}

//for other places:

/*


if (!Validation.isValid(userIn)){
    System.err.println("Expression is invalid");
}


*/