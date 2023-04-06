import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] arr = new int[300];
        int N = sc.nextInt();
        
        for(int i=0; i<N; i++) {
            arr[sc.nextInt() + 100]++;
        }
        
        System.out.println(arr[sc.nextInt() + 100]);
    }
}