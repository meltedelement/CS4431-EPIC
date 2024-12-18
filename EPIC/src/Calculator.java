public class Calculator implements Computer {

    public static boolean zeroError = false;

    public static double calc(String operator, double i, double j) throws InterruptedException {
        Calculator myCalculator = new Calculator();
        if (!Calculator.zeroError){
            System.out.print("\tNext up is ");
        }
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
        if (!Calculator.zeroError){
            System.out.printf("adding %.2f to %.2f... result %.2f%n", i, j, i + j);
        }
        return i+j;
    }
    @Override
    public double subtract(double i, double j) {
        if (!Calculator.zeroError){
            System.out.printf("subtracting %.2f from %.2f... result %.2f%n", j, i, i - j);
        }
        return i-j;
    }
    @Override
    public double multiply(double i, double j) {
        if (!Calculator.zeroError){
            System.out.printf("multiplying %.2f and %.2f... result %.2f%n", i, j, i * j);
        }
        return i * j;
    }
    @Override
    public double divide(double i, double j) throws InterruptedException {
        if (j != 0.0) { // Try catch to handle division by 0 errors
            if (!Calculator.zeroError){
                System.out.printf("dividing %.2f by %.2f... result %.2f%n", i, j, i / j);
            }
            return i / j;
        } else {
            if (!Calculator.zeroError){
                System.err.println("Arithmetic error when dividing... ensure divisor is non-zero!\n");
                zeroError = true;
                Thread.sleep(500);
            }
            return 0;
        }
    }
    @Override
    public double power(double i, double j) {
        if (!Calculator.zeroError){
            System.out.printf("raising %.2f to the power of %.2f... result %.2f%n", i, j, Math.pow(i,j));
        }
        return Math.pow(i,j);
    }
}
