public class TestingValidation {

    public static void main(String[] args) throws InterruptedException {

        // test cases where validation is wrong

        System.out.println("5/0: " + Validation.isValid("5/0")); // false but division by zero error is handled elsewhere
        System.out.println("5/(3-3): " + Validation.isValid("5/(3-3)")); // false but division by zero error is handled elsewhere
        //System.out.println("2+++2: " + Validation.isValid("2+++2")); // false but completely fucked
        //System.out.println("2*/*-+()2: " + Validation.isValid("2+++2")); // false but completely fucked
        //System.out.println("(4+5)()/2: " + Validation.isValid("(4+5)()/2")); // false but completely fucked
        System.out.println("))4+4((: " + Validation.isValid("))4+4((")); // false but says true
        System.out.println("))3(15)+-4(((): " + Validation.isValid("))3(15)+-4((()")); // false but says true

        System.out.println(": " + Validation.isValid("")); // false
        System.out.println("4: " + Validation.isValid("4")); // false
        System.out.println("(): " + Validation.isValid("()")); // false
        System.out.println("((): " + Validation.isValid("(()")); // false
        System.out.println("2.2*5: " + Validation.isValid("2.2*5")); // true
        System.out.println("{5*12}: " + Validation.isValid("{5*12}")); // false
        System.out.println("=2/3+2: " + Validation.isValid("=2/3+2")); // false
        System.out.println("3+65+2+1: " + Validation.isValid("3+65+2+1")); // true
        System.out.println("((2)(3)): " + Validation.isValid("((2)(3))")); // true
        System.out.println("3^2*2-12^-(1/2): " + Validation.isValid("3^2*2-12^-(1/2)")); // true
        System.out.println("1.324654353+ 5645767657: " + Validation.isValid("1.324654353+ 5645767657")); // true
    }
}