public class HandleBrackets {
    int openBracketLocation= 0;
    int closeBracketLocation = 0;
    boolean bracketToMultiplyFlag = false;

    public String bracketSplitup(String expressionSplit){
        String[] splitup = expressionSplit.split("");
        int iterator = 0;
        for (String x : splitup){
            iterator++;
            if (x.equals("(") ){
                openBracketLocation = iterator;
            }
            else if (x.equals(")")){
                closeBracketLocation = iterator;
                if (expressionSplit.charAt(iterator + 1) ==  '('){
                    bracketToMultiplyFlag = true;
                }
                break;
            }
        }

        String sub = expressionSplit.substring(openBracketLocation, closeBracketLocation - 1);
        System.out.println("Now we work through the expression " + sub);
        String result = String.valueOf(ParseStringExpression.evaluate(sub));
        if (bracketToMultiplyFlag){
            expressionSplit = expressionSplit.replace("(" + sub + ")", " " + result + " ");
        }

        else{
            expressionSplit = expressionSplit.replace("(" + sub + ")", " " + result + " * ");
        }
        System.out.println("Now our expression looks like this: " + expressionSplit);
        return expressionSplit;
    }

    public static void main(String[] args){
        HandleBrackets handleBrackets = new HandleBrackets();
        String test = "(3 + 2)+(8 * 9)";
        test = handleBrackets.bracketSplitup(test);
        while (test.contains("(")){
            test = handleBrackets.bracketSplitup(test);
        }
        System.out.println(ParseStringExpression.evaluate(test));
    }
}