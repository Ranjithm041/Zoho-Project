public class Pattern{
    public static void main(String[] args) {
        int row =9;
        Pattern(row);
    }
    public static void Pattern(int row){
        for (int i = 1; i <=row; i++) {
            int number=i;
            for (int j = 1; j <=i; j++) {
                System.out.print(number+" ");
                number +=row-j;
            }
            System.out.println();
        }
    }
}