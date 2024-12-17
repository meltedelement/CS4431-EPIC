import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixCalculator {

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.print("Welcome to the Matrix Calculator!");
        Thread.sleep(1000);
        for (int x = 0; x < 3; x++) {
            if (x != 2) {
                System.out.print(".");
            } else {
                System.out.println(".");
            }
            Thread.sleep(500);
        }

        int rows = 0, cols = 0;
        boolean validInput = false;

        // Input number of rows with exception handling
        while (validInput == false) {
            try {
                System.out.print("Enter the number of rows for the matrices: ");
                rows = input.nextInt();
                if (rows <= 0) {
                    System.out.println("Number of rows must be positive.");
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                input.next();
            }
        }

        validInput = false;


        while (validInput == false) {
            try {
                System.out.print("Enter the number of columns for the matrices: ");
                cols = input.nextInt();
                if (cols <= 0) {
                    System.out.println("Number of columns must be positive.");
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                input.next();
            }
        }

        System.out.println("Enter elements of Matrix 1:");
        int[][] matrix1 = readMatrix(input, rows, cols);

        System.out.println("Enter elements of Matrix 2:");
        int[][] matrix2 = readMatrix(input, rows, cols);

        System.out.println("Choose an operation: ");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Matrix1^2");
        System.out.println("5. Matrix2^2");
try{
        int choice = input.nextInt(); // exception

        switch (choice) {
            case 1:

                System.out.println("Result of Addition:");
                printMatrix(addMatrices(matrix1, matrix2));
                break;
            case 2:

                System.out.println("Result of Subtraction:");
                printMatrix(subtractMatrices(matrix1, matrix2));
                break;
            case 3:

                System.out.println("Result of Multiplication:");
                printMatrix(multiplyMatrices(matrix1, matrix2, rows, cols));
                break;
            case 4:
                System.out.println("Result of Squaring Matrix1");
                printMatrix(squareMatrix(matrix1, rows, cols));
                break;
            case 5:
                printMatrix(squareMatrix(matrix2, rows, cols));
                break;
            default:

                System.out.println("Invalid choice.");
                Thread.sleep(1000);
                clearScreen();
        }
        }catch(InputMismatchException e){
    System.out.println("Please enter a valid number from 1-5");
    input.next();
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
} catch (Exception e) {
    System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static int[][] readMatrix(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    System.out.println("Enter element for row " + (i + 1) + ", column " + (j + 1) + ": ");
                    matrix[i][j] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.next();
                    j--;
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("%-6d", element);
            }
            System.out.println();
        }
    }

    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        System.out.println("Matrix addition works by adding " +
                "corresponding elements of two " +
                "matrices of the same dimensions to produce a new matrix, " +
                "\nwhere each element at position \\((i, j)\\) is" +
                " the sum of the elements at \\((i, j)\\) in " +
                "the two original matrices.");
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) {
        System.out.println("Matrix subtraction works by subtracting the " +
                "corresponding elements of two " +
                "matrices of the same dimensions to produce a new matrix, " +
                "\nwhere each element is the difference of the elements" +
                " at the same position in the original matrices.");
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2, int rows, int cols) {
        System.out.println("Matrix multiplication involves taking the " +
                "dot product of rows from the first matrix " +
                "with columns from the second matrix, " +
                "summing the products, \nand placing " +
                "the result in the corresponding position " +
                "of the resulting matrix.");
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] squareMatrix(int[][] matrix1, int rows, int cols) {
        System.out.println("Matrix squaring involves multiplying a matrix by itself, " +
                "following the standard rules of matrix multiplication," +
                " where each element in the resulting matrix is computed " +
                "as the dot product of the corresponding row from the" +
                " first matrix and column from the second matrix.");
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols; k++) {
                    result[i][j] += matrix1[i][k] * matrix1[k][j];
                }
            }
        }
        return result;
    }
    public static void clearScreen(){
        System.out.print("\033\143");
    }
}
