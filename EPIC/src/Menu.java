import java.util.Scanner;

public class Menu {

    public static void clearScreen() {
        System.out.println("\033\143");
    }

    public static void mainMenu() throws InterruptedException {
        System.out.println("\n\t ________       ________       ________          ___");
        System.out.println("\t|\\   __  \\     |\\   __  \\     |\\   __  \\        |\\  \\      ");
        System.out.println("\t\\ \\  \\|\\  \\    \\ \\  \\|\\  \\    \\ \\  \\|\\  \\       \\ \\  \\ ");
        System.out.println("\t \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\       \\ \\  \\   ");
        System.out.println("\t  \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\    \\ \\  \\\\\\  \\       \\ \\__\\   ");
        System.out.println("\t   \\ \\_______\\    \\ \\_______\\    \\ \\_______\\       \\|__|   ");
        System.out.println("\t    \\|_______|     \\|_______|     \\|_______|           ___ ");
        System.out.println("\t                                                      |\\__\\");
        System.out.println("  Welcome to the Order of Operations (OOO!) Calculator    \\|__|\n");

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("\t MAIN MENU");
            System.out.printf("%s%n%s%n%s%n", "\t\t1) OOO Informational Calculator",
                    "\t\t2) Advanced mode (matrices)", "\t\t3) Exit");

            System.out.print("Enter a menu choice: ");
            String str = input.nextLine();
            while (!(str.equals("1") | str.equals("2") | str.equals("3")) ) {
                System.err.println("Invalid option. Please try again.");
                Thread.sleep(500);
                System.out.print("Enter a menu choice: ");
                str = input.nextLine();
            }
            switch (str) {
                case "1": runCalculator(input); return;
                case "2": MatrixCalculator.runMatrix(); return;
                case "3":
                    System.out.println("Thank you for using the OOO calculator!\nBye!! :)");
                    return;
            }
            clearScreen();
        }
    }

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
        if (!Calculator.zeroError){
            System.out.println("\n...and it's as easy as that! Our result is " + result + "\n");
        }

        System.out.print("Enter 1 to start a new calculation, or 2 to return to main menu: ");
        String str2 = input.nextLine();
        while (!(str2.equals("1") | str2.equals("2")) ) {
            System.err.println("Invalid option. Please try again.");
            Thread.sleep(500);
            System.out.print("Enter 1 to start a new calculation, or 2 to return to main menu: ");
            str2 = input.nextLine();
        }
        clearScreen();
        if (str2.equals("2")) {
            mainMenu();
        } else {
            Calculator.zeroError = false;
            runCalculator(input);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        mainMenu();
    }

}