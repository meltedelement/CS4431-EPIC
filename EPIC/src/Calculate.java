public abstract class Calculate {

    public static double calc(String operator, double i, double j) {

        switch (operator) {
            case "+":
                System.out.printf("Adding %.2f to %.2f... result %.2f%n", i, j, i + j);
                return i + j;
            case "-":
                System.out.printf("Subtracting %.2f from %.2f... result %.2f%n", i, j, i - j);
                return i - j;
            case "*":
                System.out.printf("Multiplying %.2f and %.2f... result %.2f%n", i, j, i * j);
                return i * j;
            case "/":
                // Try catch to handle division by 0 errors
                try {
                    System.out.printf("Dividing %.2f by %.2f... result %.2f%n", i, j, i / j);
                    return i / j;
                } catch (ArithmeticException e) {
                    System.err.println("Arithmetic error when dividing... ensure divisor is non-zero!\n" + e);
                }
            case "^":
                System.out.printf("Calculating %.2f to the power of %.2f... result %.2f%n", i, j, Math.pow(i,j));
                return Math.pow(i,j);
            default:
                System.out.println("Unknown operator!");
                return 0.0;
        }

    }

}
