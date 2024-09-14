
import java.util.Arrays;

public class Rotate {
    public static void main(String[] args) {

        int arr[][] ={{1,2,3},{1,4,2},{4,3,6}};
        // int newarr[][]=new int[arr.length][arr.length];
        // int index =arr.length-1;
        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr.length; j++) {      
        //         newarr[j][i]=arr[index][j];
        //     }
        //     index--;
        // }
        
        for (int i = 0; i < arr.length; i++) {
                for (int j = arr.length-1; j >=0; j--) {      
                //   int  temp =arr[j][i];
                    arr[j][i]=arr[i][j];
                }
        }     
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
               System.out.print(arr[i][j]+" ");
                
            }
            System.out.println();
        }
        //  System.out.println(Arrays.deepToString(arr));
}
}
