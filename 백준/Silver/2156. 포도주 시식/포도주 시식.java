import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] wines = new int[N];

        if (N < 3) {
            int num = 0;
            for (int i = 0; i < N; i++) {
                num += sc.nextInt();
            }
            System.out.println(num);
            return;
        }

        for (int i = 0; i < N; i++) {
            wines[i] = sc.nextInt();
        }

        int[][] dp = new int[3][N];

        dp[0][1] = wines[0];
        dp[1][1] = wines[1];
        dp[2][1] = wines[0] + wines[1];

        for (int i = 2; i < N; i++) {
            dp[0][i] = Math.max(Math.max(dp[0][i-1], dp[1][i-1]), dp[2][i-1]);
            dp[1][i] = dp[0][i-1] + wines[i];
            dp[2][i] = dp[1][i-1] + wines[i];
        }

        System.out.println(Math.max(Math.max(dp[0][N-1], dp[1][N-1]), dp[2][N-1]));
    }
}