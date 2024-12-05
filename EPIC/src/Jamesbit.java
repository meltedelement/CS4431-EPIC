public class Jamesbit {
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
        System.out.println("calling " + sub + " on skyes bit");
        String result = String.valueOf(ParseStringExpression.evaluate(sub));
        expressionSplit = expressionSplit.replace("(" + sub + ")", result);
        return expressionSplit;
    }

    public static void main(String[] args){
        Jamesbit james = new Jamesbit();
        String test = "(3 + 2)+(8 * 9)";
        test = james.bracketSplitup(test);
        while (test.contains("(")){
            //System.out.print(test);
            test = james.bracketSplitup(test);
        }
        System.out.println("calling " + test + " on skyes bit");
        System.out.println(ParseStringExpression.evaluate(test));
    }
}