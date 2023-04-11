import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int absN = Math.abs(N);

        if(N == 0) {
            System.out.println(0);
            System.out.println(0);
            return;
        } else if (absN == 1) {
            System.out.println(1);
            System.out.println(1);
            return;
        } 

        long[] dp = new long[absN+1];
        dp[1] = 1;

        for (int i = 2; i <= absN; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000000;
        }


        if (N < 0 && absN % 2 == 0) {
            System.out.println(-1);
            System.out.println(dp[absN]);
        } else {
            System.out.println(1);
            System.out.println(dp[absN]);
        }
    }
}