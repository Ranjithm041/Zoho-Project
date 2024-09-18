import java.util.Scanner;

class Minesweeper {
    int[][] matrix;
    private int size;

// Constructor to initialize  matrix  size
    public Minesweeper(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

// Method to shuffle the board with random  of -1, 0, 1
    public void shuffleBoard() {
        int totalElements = size * size;
        int numOnes = totalElements / 3;  
        int numMinusOne = totalElements / 3; 
        int numZeros = totalElements - numOnes - numMinusOne; 
        int[] dummy = new int[totalElements];
        for (int i = 0; i < numMinusOne; i++) dummy[i] = -1;
        for (int i = numMinusOne; i < numMinusOne + numOnes; i++) dummy[i] = 1;
        for (int i = numMinusOne + numOnes; i < totalElements; i++) dummy[i] = 0;

        // Shuffle
        for (int i = 0; i < totalElements; i++) {
            int swapIndex = (int) (Math.random() * totalElements); // Random swap index
            int temp = dummy[i];
            dummy[i] = dummy[swapIndex];
            dummy[swapIndex] = temp;
        }

        // Filling
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = dummy[k++];
            }
        }
    }
    
// Method to check is a box has a number  and revealed
    public void isNumber(int row, int column) {
        if (matrix[row][column] == 1) {
            matrix[row][column] = 2; // 2 is marked as number revealed
        }
    }

// Method to check empty spaces (-1)
    public void isSpace(int row, int column) {
        if (matrix[row][column] == -1) {
            matrix[row][column] = 8; // 2 is marked as space revealed

            //  reveal adjacent boxes
            if (row > 0) 
                isSpace(row - 1, column);
            if (row < size - 1) 
                isSpace(row + 1, column);
            if (column > 0) 
                isSpace(row, column - 1);
            if (column < size - 1) isSpace(row, column + 1);
        }
    }

// Method to check the player win or not
    public boolean checkWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 1 || matrix[i][j] == -1) {
                    return false; // If there are unrevealed numbers or bombs
                }
            }
        }
        return true;
    }

// Method to reveal the bomb when the player lose the game
    public void isBomb(int row, int column) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = 9; // 9 is  Marked as bomb revealed
                }
            }
        }
    }

// Method to print the current board state
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 1 || matrix[i][j] == -1 || matrix[i][j] == 0) {
                    System.out.printf("%3s ", "?"); // Hide default boxes like -1,0,1
                } else if (matrix[i][j] == 8) {
                    System.out.printf("%3s ", " "); // Empty revealed space
                } else if (matrix[i][j] == 2) {
                    System.out.printf("%3s ", "1"); // Revealed number 1
                } else if (matrix[i][j] == 9) {
                    System.out.printf("%3s ", "0"); // Revealed bomb
                } else {
                    System.out.printf("%3d ", matrix[i][j]); 
                }
            }
            System.out.println();
        }
    }
}

public class Game {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continuePlaying = true;

        while (continuePlaying) {
            int size = 0;
            while (size <= 0) {
                System.out.print("Enter the board size: ");
                size = input.nextInt();
                if (size <= 0) {
                    System.out.println("Invalid size...");
                }
            }

            Minesweeper mine = new Minesweeper(size);
            mine.shuffleBoard();
            mine.printBoard();

            boolean gameOver = false;

            while (!gameOver) {
                System.out.print("Enter the row: ");
                int row = input.nextInt();
                System.out.print("Enter the column: ");
                int column = input.nextInt();

                if (row >= 0 && row < size && column >= 0 && column < size) {
                    switch (mine.matrix[row][column]) {
                        case -1:
                            mine.isSpace(row, column);
                            break;
                        case 1:
                            mine.isNumber(row, column);
                            break;
                        case 0:
                            mine.isBomb(row, column);
                            mine.printBoard();
                            System.out.println("You hit a bomb... Game Over...");
                            gameOver = true;
                            break;
                        default:
                            System.out.println("\nChoose another row and column!\n");
                    }

                    mine.printBoard();

                    if (!gameOver && mine.checkWin()) {
                        System.out.println("Congrats.... you won the game...");
                        gameOver = true;
                    }
                } else {
                    System.out.println("Invalid row or column.... Please enter values within bounds.");
                }
            }

            // After win or game over ask continue or exit
            System.out.print("Enter -1 to exit / 0 to play again: ");
            int exitChoice = input.nextInt();
            if (exitChoice == -1) {
                continuePlaying = false;
                input.close();
                System.out.println("Exiting the game...");
            } else {
                System.out.println("Starting a new game...");
            }
        }

    }
}