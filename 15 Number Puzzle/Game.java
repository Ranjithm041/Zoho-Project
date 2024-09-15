
import java.util.Scanner;


 class FifteenPuzzle{
  private static  int matrix[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
  private static  int rowOfZero ;
  private static  int columnOfZero ;
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
public  void printBoard(){
    System.out.println();
    for(int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.printf("%3d ",matrix[i][j]);
        }
        System.out.println();
    }
}
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
        // puzzle.shuffleBoard(); 
        puzzle.printBoard();
        while (!puzzle.isSolved()) {
            System.out.print("Enter move (up/down/left/right): ");
            String move = input.nextLine().trim().toLowerCase();
            switch (move) {
                case "up": puzzle.moveUp(); break;
                case "down": puzzle.moveDown(); break;
                case "left": puzzle.moveLeft(); break;
                case "right" : puzzle.moveRight(); break;
                default: throw new  IllegalArgumentException("Invalid input... please enter only  up/down/left/right keyword "+ move);
            }
        }
        if (puzzle.isSolved()) 
        System.out.println("Congratulations you Won the game! ");
        
    }
}