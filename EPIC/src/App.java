import java.util.Scanner;
import java.util.ArrayList;

public class App {
    int openBracketLocation;
    int closeBracketLocation;
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        ArrayList<String> orderedBracketContents = new ArrayList<>();

        System.out.println("Enter an expression to be evaluated!");
        String userIn = scan.nextLine();

        App appy = new App();

        appy.bracketSplitup(userIn, orderedBracketContents);

        for(String x:orderedBracketContents){
            System.out.println(x);
        }
    }


    public void bracketSplitup(String expressionSplit, ArrayList<String> orderedBracketContents ){
        bracketSplitup(expressionSplit.split(""), orderedBracketContents);
    }
    
    public void bracketSplitup(String[] expressionSplit, ArrayList<String> orderedBracketContents){
        int iterator = 0;
        boolean bracketFound = false;
        for (String x : expressionSplit){
            iterator ++;
            if (x == "("){
                bracketFound = true;
                openBracketLocation = iterator;
            }
            else if (x == ")"){
                bracketFound = true;

                closeBracketLocation = iterator;
                break;
            }
        }
        if (bracketFound){
            orderedBracketContents.add(getSliceOfArray(expressionSplit, openBracketLocation, closeBracketLocation).toString());
        }
        orderedBracketContents.add(expressionSplit.toString());
    }




    public static String[] getSliceOfArray(String[] arr, int start,  int end){

    
    String[] slice = new String[end - start];

    // Copy elements of arr to slice
    for (int i = 0; i < slice.length; i++) {
        slice[i] = arr[start + i];
    }

    // return the slice
    return slice;
}
}
