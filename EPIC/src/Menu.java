import java.util.Scanner;

public class Menu {

    public static void clearScreen() {
        System.out.print("\033\143");
    }

<<<<<<< HEAD
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
=======
    public static void runCalculator(Scanner input) throws InterruptedException {
        System.out.print("Please enter an expression to be evaluated: ");
        String str = input.nextLine();
        while (!Validation.isValid(str)) {
            System.out.print("Please try again, enter an expression to be evaluated: ");
            str = input.nextLine();
        }
        System.out.println("Let's start our calculations!\n");
        HandleBrackets appy = new HandleBrackets();
        while (str.contains("(")){
            str = appy.bracketSplitup(str);
        }
        double result = ParseStringExpression.evaluate(str);
        System.out.println("\n...and it's as easy as that! Our result is " + result + "\n");
    }

    public static void main(String args[]) throws InterruptedException {
>>>>>>> c1b1c74815abcd8a3384bacd4eacdc7ace87967f
        System.out.println("\n\t ________       ________       ________          ___");
        System.out.println("\t|\\   __  \\     |\\   __  \\     |\\   __  \\        |\\  \\      ");
        System.out.println("\t\\ \\  \\|\\  \\    \\ \\  \\|\\  \\    \\ \\  \\|\\  \\       \\ \\  \\ ");
        System.out.println("\t \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\       \\ \\  \\   ");
        System.out.println("\t  \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\       \\ \\__\\   ");
        System.out.println("\t   \\ \\_______\\    \\ \\_______\\    \\ \\_______\\       \\|__|   ");
        System.out.println("\t    \\|_______|     \\|_______|     \\|_______|           ___ ");
        System.out.println("\t                                                      |\\__\\");
        System.out.println("  Welcome to the Order of Operations (OOO!) Calculator    \\|__|\n");

        while (1 == 1) {
            System.out.print("Please enter an expression to be evaluated: ");
            String str = input.nextLine();
            while (!Validation.isValid(str)) {
                System.out.print("Please try again, enter an expression to be evaluated: ");
                str = input.nextLine();
            }
            System.out.println("Let's start our calculations!\n");
            HandleBrackets handleBrackets = new HandleBrackets();
            while (str.contains("(")){
                str = handleBrackets.bracketSplitup(str);
            }
            double result = ParseStringExpression.evaluate(str);
            System.out.println("\n...and it's as easy as that! Our result is " + result + "\n");
        }
    }

}