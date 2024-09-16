class Minesweeper{
   private static int matrix[][] = {{1,-1,-1,1},{1,0,-1,0},{-1,1,0,1},{1,0,-1,-1}};
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
public  void printBoard(){
    for (int i = 0; i < matrix.length; i++) {
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
    }

}
