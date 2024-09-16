
import java.util.Scanner;

class Minesweeper{
   static int matrix[][] = {{1,-1,-1,1},{1,0,-1,0},{-1,1,0,1},{1,0,-1,-1}};
   public  void isNumber(int row ,int column){
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            if(matrix[row][column]==1){
                matrix[row][column]=2;
                break;
            }
        }
    }
}

public  void isSpace(int row,int column){
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[row][column]==-1 ) {
                matrix[row][column]=8;
                if (row > 0) isSpace(row - 1, column);
                  if (row < matrix.length - 1) isSpace(row + 1, column);
                  if (column > 0) isSpace(row, column - 1);
                  if (column < matrix[0].length - 1) isSpace(row, column + 1); 
            }
        }
       
    }
}
public boolean checkWin() {
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j] == 1 ||matrix[i][j] == -1) {
                return false;
            }
        }
    }
    return true;
 }
public  void printBoard(){
    for (int i = 0; i < matrix.length; i++) {cd
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j]==1 || matrix[i][j]==-1 || matrix[i][j]==0) {
                System.out.printf("%3s ","-");
            }else{
                System.out.printf("%3d ",matrix[i][j]);
            }

        }
        System.out.println();
    }
}
public boolean isBomb(int row ,int column){
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            if(matrix[i][j]==0 && matrix[row][column]==0){
              return true;
            }
        }
    }
    return false;
}
}
public class Game {
    
    public static void main(String[] args) {
        Minesweeper mine = new Minesweeper();
        mine.printBoard();
        Scanner input = new Scanner(System.in);
    
        while (true) {
            
            System.out.print("Enter the row : ");
            int row = input.nextInt();
            System.out.print("Enter the column : ");
            int column = input.nextInt();
            if(row==-1 || column==-1){
                System.out.println("Exiting game...");
                break;
            }
            if (row >= 0 && row < 4 && column >= 0 && column < 4) {
                switch (mine.matrix[row][column]) {
                    case -1: mine.isSpace(row, column); break;
                    case 1: mine.isNumber(row, column); break;
                    case 0: System.out.println("You hit a bomb... Game Over..."); return;  
                    default: System.out.println("choose other row and column");
                }
                mine.printBoard();
                if(mine.checkWin()){
                    System.out.println("Congrats.... you won the game...");
                    return;
                }
            } else {
                System.out.println("Invalid row or column.... Please enter values between 0 and 3.");
            }
        }
    }

}
