import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N == 1) {
            System.out.println(9);
            return;
        }

        long[][] dp = new long[N+1][10];

        dp[1][0] = 0;
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            for (int j = 1; j < 9; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N][i]) % 1000000000;
        }
        System.out.println(answer);
    }
}