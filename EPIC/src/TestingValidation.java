public class TestingValidation {

    public static void main(String[] args) {

        // test cases where validation is wrong
        //System.out.println(Validation.isValid("")); // false but says true
        // System.out.println(Validation.isValid("4")); // false but says true
        //System.out.println(Validation.isValid("()")); // false but says true

        //fixed
        // System.out.println("\n5/0 <- false"); //not a problem for validation - fixed in caluclate
        // System.out.println(Validation.isValid("5/0")); // false but says true
        // System.out.println("\n2.2*5 <- true"); //fixed
        // System.out.println(Validation.isValid("2.2*5")); // true but says false
        // System.out.println("\n5/(3-3) <- false"); //zero error again caught elsewhere
        // System.out.println(Validation.isValid("5/(3-3)")); // false but says true
        // System.out.println("\n1.324654353+ 5645767657 <- true"); //fixed
        // System.out.println(Validation.isValid("1.324654353+ 5645767657")); // true but says false   //fix by add '.' to arry list
        // System.out.println("\n\n\n");

        // test cases where validation is correct
        //System.out.println(Validation.isValid("(()")); // false
    //     System.out.println(Validation.isValid("{5*12}")); // false
    //     System.out.println(Validation.isValid("=2/3+2")); // false
    //     System.out.println(Validation.isValid("3+65+2+1")); // true
    //     System.out.println(Validation.isValid("((2)(3))")); // true but returns false as no operator.

    //     System.out.println(Validation.isValid("3^2*2-12^-(1/2)")); // true
    }
}
