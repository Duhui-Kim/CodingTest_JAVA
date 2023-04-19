import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        if(N < 3) {
            System.out.println(N);
            return;
        }
        
        int[] arr = new int[N+1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        
        for(int i=3; i<=N; i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % 15746;
        }
        System.out.println(arr[N]);
    }
}