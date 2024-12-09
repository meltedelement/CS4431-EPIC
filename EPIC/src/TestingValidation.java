public class TestingValidation {

    public static void main(String[] args) {

        // test cases where validation is wrong
        System.out.println(Validation.isValid("")); // false but says true
        System.out.println(Validation.isValid("4")); // false but says true
        System.out.println(Validation.isValid("()")); // false but says true
        System.out.println(Validation.isValid("5/0")); // false but says true
        System.out.println(Validation.isValid("2.2*5")); // true but says false
        System.out.println(Validation.isValid("5/(3-3)")); // false but says true
        System.out.println(Validation.isValid("1.324654353+ 5645767657")); // true but says false
        System.out.println("\n\n\n");

        // test cases where validation is correct
        System.out.println(Validation.isValid("(()")); // false
        System.out.println(Validation.isValid("{5*12}")); // false
        System.out.println(Validation.isValid("=2/3+2")); // false
        System.out.println(Validation.isValid("3+65+2+1")); // true
        System.out.println(Validation.isValid("((2)(3))")); // true
        System.out.println(Validation.isValid("3^2*2-12^-(1/2)")); // true
    }
}
