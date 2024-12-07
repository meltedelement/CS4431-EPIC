public class App {
    int openBracketLocation= 0;
    int closeBracketLocation = 0;

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
                break;
            }
        }


        String sub = expressionSplit.substring(openBracketLocation, closeBracketLocation - 1);
        String result = String.valueOf(ParseStringExpression.evaluate(sub));
        expressionSplit = expressionSplit.replace("(" + sub + ")", result);
        return expressionSplit;
    }

    public static void main(String[] args){
        App appy = new App();
        String test = "(3 + 2)+(8 * 9)";
        test = appy.bracketSplitup(test);
        while (test.contains("(")){
            test = appy.bracketSplitup(test);
        }
        System.out.println(ParseStringExpression.evaluate(test));
    }
}