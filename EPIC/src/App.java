import java.util.Scanner;
import java.util.Arrays; 

public class App {
    int openBracketLocation;
    int closeBracketLocation;
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter an expression to be evaluated!");
        String userIn = scan.nextLine();


        bracketSplitup(userIn)

    }


    public String[] bracketSplitup(String[] expressionSplit){
        boolean bracketFound = false;
        for (String x : expressionSplit){
            if (x == "("){
                bracketFound = true;
                openBracketLocation = Arrays.binarySearch(expressionSplit, x);
            }
            else if (x == ")"){
                bracketFound = true;

                closeBracketLocation = Arrays.binarySearch( expressionSplit, x);
                break;
            }
        }
        if (bracketFound){
            return(bracketSplitup(getSliceOfArray(expressionSplit, openBracketLocation, closeBracketLocation)))
        }
        return(expressionSplit);
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
