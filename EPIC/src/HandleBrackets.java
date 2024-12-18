public class HandleBrackets {
    int openBracketLocation= 0;
    int closeBracketLocation = 0;
    boolean bracketToMultiplyFlag = false;

    public String bracketSplitup(String expressionSplit) throws InterruptedException {
        String[] splitup = expressionSplit.split("");
        int iterator = 0;
        for (String x : splitup){
            iterator++;
            if (x.equals("(") ){
                openBracketLocation = iterator;
            }
            else if (x.equals(")")){
                closeBracketLocation = iterator;

                    if ( iterator != splitup.length && expressionSplit.charAt(iterator) ==  '('){
                        bracketToMultiplyFlag = true;
                    }
                

                    break;

                }
            }
        

        String sub = expressionSplit.substring(openBracketLocation, closeBracketLocation - 1);
        if (!Calculator.zeroError){
            System.out.println("Now we work through the expression " + sub);
        }
        String result = String.valueOf(ParseStringExpression.evaluate(sub));
        if (bracketToMultiplyFlag){
            expressionSplit = expressionSplit.substring(0, openBracketLocation-1) + " " + result + " *"
                    + expressionSplit.substring(closeBracketLocation);
            bracketToMultiplyFlag = false;
        }

        else{
            expressionSplit = expressionSplit.replace("(" + sub + ")", " " + result + "  ");
        }
        if (!Calculator.zeroError){
            System.out.println("Now our expression looks like this: " + expressionSplit);
        }
        return expressionSplit;
    }
}
