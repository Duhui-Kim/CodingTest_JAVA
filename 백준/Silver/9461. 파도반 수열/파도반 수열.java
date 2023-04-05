import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }

        int testCase = sc.nextInt();

        for (int tc = 0; tc < testCase; tc++) {
            int N = sc.nextInt();
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}