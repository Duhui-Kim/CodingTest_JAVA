import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        int[][] dp = new int[2][41];

        dp[0][0] = 1;
        dp[1][0] = 0;

        dp[0][1] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++) {
            dp[0][i] = dp[0][i-1] + dp[0][i-2];
            dp[1][i] = dp[1][i-1] + dp[1][i-2];
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < testCase; tc++) {
            int N = sc.nextInt();
            sb.append(dp[0][N]).append(" ").append(dp[1][N]).append("\n");
        }
        System.out.println(sb);
    }
}