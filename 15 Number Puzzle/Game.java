
import java.util.Scanner;


final class FifteenPuzzle{
  private final  int matrix[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}}; //initialize the board
  private  int rowOfZero ;
  private  int columnOfZero ;

// Method to move upward  
public void moveUp(){
    findZero();
    if (rowOfZero>0) {
        int temp=matrix[rowOfZero][columnOfZero];
        matrix[rowOfZero][columnOfZero]=matrix[rowOfZero-1][columnOfZero];
        matrix[rowOfZero-1][columnOfZero]=temp;
        rowOfZero--;
            printBoard();
    }else{
        System.out.println("No possible move for up ");
    }
}

// Method to move down
public  void moveDown(){
    findZero();
    if (rowOfZero<matrix.length-1) {
        int temp=matrix[rowOfZero][columnOfZero];
        matrix[rowOfZero][columnOfZero]=matrix[rowOfZero+1][columnOfZero];
        matrix[rowOfZero+1][columnOfZero]=temp;
        rowOfZero++;
            printBoard();
    }else{
        System.out.println("No possible move for down ");
    }
}

//Method to move right
public  void moveRight(){
     findZero();
        if (columnOfZero < matrix[0].length - 1) {
            int temp=matrix[rowOfZero][columnOfZero];
            matrix[rowOfZero][columnOfZero]=matrix[rowOfZero][columnOfZero+1];
            matrix[rowOfZero][columnOfZero+1]=temp;
            columnOfZero++;
                printBoard();
            }else{
                System.out.println("No possible for move right ");
            }
}

// Method to move Left
public  void moveLeft(){
    findZero();
    if(columnOfZero>0){
        int temp=matrix[rowOfZero][columnOfZero];
    matrix[rowOfZero][columnOfZero]=matrix[rowOfZero][columnOfZero-1];
    matrix[rowOfZero][columnOfZero-1]=temp;
    columnOfZero--;
        printBoard();
    }else{
        System.out.println("Not possible for move left ");
    }
}

// Method to Locate the Zero
public  void findZero(){
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            if(matrix[i][j]==0){
               rowOfZero=i;
               columnOfZero=j;
               return;
            }
        }
    }
    
}

// Method to print a Board
public  void printBoard(){
    System.out.println();
    for(int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.printf("%3d ",matrix[i][j]);
        }
        System.out.println();
    }
}

// Method to shuffle the board
public  void shuffleBoard() {
    int dummy[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    for (int i = 0; i < dummy.length; i++) {
        int swapIndex = (int) (Math.random() * (i+1));  
        int temp = dummy[i];
        dummy[i] = dummy[swapIndex];
        dummy[swapIndex] = temp;
    }

    int k = 0; 
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[i][j] = dummy[k++];
        }
    }
}

// Method to the current board match the pattern or not
    public  boolean isSolved(){
        int pattern[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[i].length; j++) {
                if (pattern[i][j] !=matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

public class Game{
    public static void main(String[] args) {
        FifteenPuzzle puzzle = new FifteenPuzzle();
        Scanner input = new Scanner(System.in);
        puzzle.shuffleBoard(); 
        puzzle.printBoard();
        boolean gameOver = false;
        while (!gameOver) {
            System.out.print("Enter move (up-> W /down_> S /left-> A /right-> D): ");
            String move = input.nextLine().trim().toLowerCase();
            switch (move) {
                case "w": puzzle.moveUp(); break;
                case "s": puzzle.moveDown(); break;
                case "a": puzzle.moveLeft(); break;
                case "d" : puzzle.moveRight(); break;
                default: System.out.println("Invalid input... please enter only  w/s/a/d keyword "); 
            }
            if (puzzle.isSolved()) {
                System.out.println("Congratulations you Won the game! ");
                System.out.print("If you want to continue the game press 0 else -1 : ");
                byte exitStatus = input.nextByte();
                if(exitStatus==0){
                    gameOver=false;
                    input.nextLine();
                    puzzle.shuffleBoard();
                    puzzle.printBoard();
                }else{
                    gameOver=true;
                    input.close();
                    System.out.println("exiting game....");
                }
            }
        }
        
        
    }
}