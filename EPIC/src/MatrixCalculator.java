//import exception
import java.util.InputMismatchException;
//import Scanner for user input
import java.util.Scanner;

public class MatrixCalculator {

    public static void runMatrix() throws InterruptedException {
        Scanner input = new Scanner(System.in);
//welcome menu.
// for loop prints "." every .5 seconds to give command line loading visual
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
//initialise constructors for the dimensions of the matrices
        int rows1 = 0, cols1 = 0, rows2 = 0, cols2 = 0;
        boolean validInput = false;

        //while loop to input the dimensions of the matrices
        //boolean initialised to false. while false loop continues
        //if valid input, boolean is set to true and loop exits
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

        //repetition of while loop for inputting matrix dimensions
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
//method "reedMatrix" is called to store the matrix elements
        System.out.println("Enter elements of Matrix 1:");
        int[][] matrix1 = readMatrix(input, rows1, cols1);

        System.out.println("Enter elements of Matrix 2:");
        int[][] matrix2 = readMatrix(input, rows2, cols2);
//menu for user to choose which calculation they want to perform
        System.out.println("Choose an operation: ");
        System.out.println("1. Addition (only if dimensions match)");
        System.out.println("2. Subtraction (only if dimensions match)");
        System.out.println("3. Multiplication");
        System.out.println("4. Matrix1^2 (only if square)");
        System.out.println("5. Matrix2^2 (only if square)");
//switch statement in a try and
// catch block to decide which method to call based on user input
        try {
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    //check if dimensions are the same
                    // if they are call addition method and calculate
                    if (rows1 == rows2 && cols1 == cols2) {
                        System.out.println("Result of Addition:");
                        //return the result
                        printMatrix(addMatrices(matrix1, matrix2));
                    } else {
                        //matrices must have same dimensions to add
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
                    //in the case exceptions fall through catch block,
                //invalid choice will print and screen will be cleared
                default:
                    System.out.println("Invalid choice.");
                    Thread.sleep(1000);
                    clearScreen();
            }
            //in the case an input that cannot be converted to an integer is entered
            //or if a number outside the range 1-5 is entered
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number from 1-5.");
            input.next();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            //General exception
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
//method to read the users input
//static means you don't need to instantiate the class
// (make an Object based on your class) to use the method.
    private static int[][] readMatrix(Scanner scanner, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
//for loop to iterate through the 2D array
        //starts on row 1 and increments the column each iteration
        //when j is greater than the amount of columns
        // its reset to 0 until i is greater than
        //or equal to rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    //i +1 as i will result in an off by one logical error
                    //prompt user to enter element
                    System.out.println("Enter element for row " + (i + 1) + ", column " + (j + 1) + ": ");
                    //store next input in matric[row][column]
                    matrix[i][j] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.next();
//decrement to the previous iteration if the user enters an invalid element
                    j--;
                }
            }
        }
        return matrix;
    }
//method to print the matrix to the console
    private static void printMatrix(int[][] matrix) {
        //enchanced for loop
        //loops through the matrix to begin with
        for (int[] row : matrix) {
            //loops through each individual element(column) in each row
            for (int element : row) {
                System.out.printf("%-6d", element);
            }
            System.out.println();
        }
    }
//method to add two matrices together
    private static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        //message on how to add matrices.
        System.out.println("Matrix addition works by adding "
                + "corresponding elements of two matrices of the same dimensions to produce a new matrix, "
                + "\nwhere each element at position (i, j) is the sum of the elements at (i, j) in "
                + "the two original matrices.");
        //constructors
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
//for loop that stores each corresponding element added together in a new 2D array "result"
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }
//method to subtract matrices
    private static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) {
        System.out.println("Matrix subtraction works by subtracting the "
                + "corresponding elements of two matrices of the same dimensions to produce a new matrix, "
                + "\nwhere each element is the difference of the elements at the same position in the original matrices.");
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
//for loop that iterates through the two arrays,
// subtracting the corresponding elements of the second matrix,
        //from the first matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }
//method to multiply two matrices
    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2, int rows1, int cols1, int rows2, int cols2) {
        System.out.println("Matrix multiplication is performed by calculating the dot product of the rows of the "
                + "first matrix and the columns of the second matrix. "
                + "\nThe resulting matrix has dimensions equal to the number of rows in the first matrix and the number "
                + "of columns in the second matrix.");
        //exception
        //the columns of the first matrix must = the rows of the second in order to multiply
        if (cols1 != rows2) {
            throw new IllegalArgumentException("Matrix multiplication is not possible. "
                    + "The number of columns in the first matrix must equal "
                    + "the number of rows in the second matrix.");
        }
//initialise new 2D array
        int[][] result = new int[rows1][cols2];
//for loop that iterates through the two matrices
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i][j] = 0;
                //multiply the left most element, by the top most element
                //increment
                ////multiply the second left most element, by the second top most element
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
//reusing multiply method to multiply the matrix by itself.
    private static int[][] squareMatrix(int[][] matrix, int rows, int cols) {
        System.out.println("Matrix squaring involves multiplying a matrix by itself, "
                + "following the standard rules of matrix multiplication, "
                + "\nwhere each element in the resulting matrix is computed as the dot product of the corresponding row "
                + "from the first matrix and column from the second matrix.");
        return multiplyMatrices(matrix, matrix, rows, cols, rows, cols);
    }
//method to clear the screen using an ANSI sequence
    private static void clearScreen() {
        System.out.print("\033\143");
    }
}
