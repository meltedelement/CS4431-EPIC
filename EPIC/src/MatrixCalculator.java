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
            Thread.sleep(200);
        }

        int rows1 = 0, cols1 = 0, rows2 = 0, cols2 = 0;
        boolean validInput = false;

        // Input dimensions of Matrix 1
        while (!validInput) {
            try {
                System.out.print("Enter the number of rows for Matrix 1: ");
                rows1 = input.nextInt();
                if (rows1 <= 0) {
                    System.out.println("Number of rows must be positive.");
                    continue;
                }
                System.out.print("Enter the number of columns for Matrix 1: ");
                cols1 = input.nextInt();
                if (cols1 <= 0) {
                    System.out.println("Number of columns must be positive.");
                    continue;
                }
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a positive integer.");
                input.next();
            }
        }

        validInput = false;

        // Input dimensions of Matrix 2
        while (!validInput) {
            try {
                System.out.print("Enter the number of rows for Matrix 2: ");
                rows2 = input.nextInt();
                if (rows2 <= 0) {
                    System.out.println("Number of rows must be positive.");
                    continue;
                }
                System.out.print("Enter the number of columns for Matrix 2: ");
                cols2 = input.nextInt();
                if (cols2 <= 0) {
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
        int[][] matrix1 = readMatrix(input, rows1, cols1);

        System.out.println("Enter elements of Matrix 2:");
        int[][] matrix2 = readMatrix(input, rows2, cols2);

        System.out.println("Choose an operation: ");
        System.out.println("1. Addition (only if dimensions match)");
        System.out.println("2. Subtraction (only if dimensions match)");
        System.out.println("3. Multiplication");
        System.out.println("4. Matrix1^2 (only if square)");
        System.out.println("5. Matrix2^2 (only if square)");

        try {
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    if (rows1 == rows2 && cols1 == cols2) {
                        System.out.println("Result of Addition:");
                        printMatrix(addMatrices(matrix1, matrix2));
                    } else {
                        System.out.println("Addition is not possible. Matrices must have the same dimensions.");
                    }
                    break;
                case 2:
                    if (rows1 == rows2 && cols1 == cols2) {
                        System.out.println("Result of Subtraction:");
                        printMatrix(subtractMatrices(matrix1, matrix2));
                    } else {
                        System.out.println("Subtraction is not possible. Matrices must have the same dimensions.");
                    }
                    break;
                case 3:
                    System.out.println("Result of Multiplication:");
                    printMatrix(multiplyMatrices(matrix1, matrix2, rows1, cols1, rows2, cols2));
                    break;
                case 4:
                    if (rows1 == cols1) {
                        System.out.println("Result of Squaring Matrix1:");
                        printMatrix(squareMatrix(matrix1, rows1, cols1));
                    } else {
                        System.out.println("Matrix1 is not square, so it cannot be squared.");
                    }
                    break;
                case 5:
                    if (rows2 == cols2) {
                        System.out.println("Result of Squaring Matrix2:");
                        printMatrix(squareMatrix(matrix2, rows2, cols2));
                    } else {
                        System.out.println("Matrix2 is not square, so it cannot be squared.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
                    Thread.sleep(1000);
                    clearScreen();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number from 1-5.");
            input.next();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        System.out.print("Enter 1 to start a new matrix calculation, or 2 to return to main menu: ");
        String str2 = input.nextLine();
        while (!(str2.equals("1") | str2.equals("2")) ) {
            System.err.println("Invalid option. Please try again.");
            Thread.sleep(500);
            System.out.print("Enter 1 to start a new matrix calculation, or 2 to return to main menu: ");
            str2 = input.nextLine();
        }
        clearScreen();
        if (str2.equals("2")) {
            Menu.mainMenu();
        } else {
            main(new String[]{});
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
        System.out.println("Matrix addition works by adding "
                + "corresponding elements of two matrices of the same dimensions to produce a new matrix, "
                + "\nwhere each element at position (i, j) is the sum of the elements at (i, j) in "
                + "the two original matrices.");
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
        System.out.println("Matrix subtraction works by subtracting the "
                + "corresponding elements of two matrices of the same dimensions to produce a new matrix, "
                + "\nwhere each element is the difference of the elements at the same position in the original matrices.");
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

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2, int rows1, int cols1, int rows2, int cols2) {
        System.out.println("Matrix multiplication is performed by calculating the dot product of the rows of the "
                + "first matrix and the columns of the second matrix. "
                + "\nThe resulting matrix has dimensions equal to the number of rows in the first matrix and the number "
                + "of columns in the second matrix.");
        if (cols1 != rows2) {
            throw new IllegalArgumentException("Matrix multiplication is not possible. "
                    + "The number of columns in the first matrix must equal "
                    + "the number of rows in the second matrix.");
        }

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] squareMatrix(int[][] matrix, int rows, int cols) {
        System.out.println("Matrix squaring involves multiplying a matrix by itself, "
                + "following the standard rules of matrix multiplication, "
                + "\nwhere each element in the resulting matrix is computed as the dot product of the corresponding row "
                + "from the first matrix and column from the second matrix.");
        return multiplyMatrices(matrix, matrix, rows, cols, rows, cols);
    }

    public static void clearScreen() {
        System.out.println("\033\143");
    }
}
