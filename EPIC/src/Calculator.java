public class Calculator implements Computer {

    public static double calc(String operator, double i, double j) {
        Calculator myCalculator = new Calculator();
        System.out.print("\tNext up is ");
        switch (operator) {
            case "+": return myCalculator.add(i, j);
            case "-": return myCalculator.subtract(i, j);
            case "*": return myCalculator.multiply(i, j);
            case "/": return myCalculator.divide(i, j);
            case "^": return myCalculator.power(i,j);
            default:
                System.out.println("Unknown operator!");
                return 0.0;
        }
    }

    @Override
    public double add(double i, double j) {
        System.out.printf("adding %.2f to %.2f... result %.2f%n", i, j, i + j);
        return i+j;
    }
    @Override
    public double subtract(double i, double j) {
        System.out.printf("subtracting %.2f from %.2f... result %.2f%n", j, i, i - j);
        return i-j;
    }
    @Override
    public double multiply(double i, double j) {
        System.out.printf("multiplying %.2f and %.2f... result %.2f%n", i, j, i * j);
        return i * j;
    }
    @Override
    public double divide(double i, double j) {
        try { // Try catch to handle division by 0 errors
            System.out.printf("dividing %.2f by %.2f... result %.2f%n", i, j, i / j);
            return i / j;
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic error when dividing... ensure divisor is non-zero!\n" + e);
            return 0;
        }
    }
    @Override
    public double power(double i, double j) {
        System.out.printf("raising %.2f to the power of %.2f... result %.2f%n", i, j, Math.pow(i,j));
        return Math.pow(i,j);
    }
}
