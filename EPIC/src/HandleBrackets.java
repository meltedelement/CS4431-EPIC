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

                    if ( iterator != splitup.length && expressionSplit.charAt(iterator) ==  '('){
                        System.out.print("me when");

                        bracketToMultiplyFlag = true;
                    }
                

                    break;

                }
            }
        

        String sub = expressionSplit.substring(openBracketLocation, closeBracketLocation - 1);
        System.out.println("Now we work through the expression " + sub);
        String result = String.valueOf(ParseStringExpression.evaluate(sub));
        if (bracketToMultiplyFlag){
            expressionSplit = expressionSplit.replace("(" + sub + ")", " " + result + " * ");
            bracketToMultiplyFlag = false;
        }

        else{
            expressionSplit = expressionSplit.replace("(" + sub + ")", " " + result + "  ");
        }
        System.out.println("Now our expression looks like this: " + expressionSplit);
        return expressionSplit;
    }
    
    public static void main(String[] args){
        HandleBrackets handleBrackets = new HandleBrackets();
        String test = "(1)(2)(1)";
        test = handleBrackets.bracketSplitup(test);
        while (test.contains("(")){
            test = appy.bracketSplitup(test);
        }
        System.out.println("Before sending to skyes ting: " + test);
        System.out.println(ParseStringExpression.evaluate(test));
    }
}
