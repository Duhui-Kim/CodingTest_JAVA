import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());

            int[][] input = new int[2][N];
            int[][] dp = new int[2][N];

            if (N == 1) {
                int a = Integer.parseInt(sc.nextLine());
                int b = Integer.parseInt(sc.nextLine());

                sb.append(Math.max(a, b)).append("\n");
                continue;
            }

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = input[0][0];
            dp[1][0] = input[1][0];

            int max = 0;
            for (int j = 1; j < N; j++) {
                for (int i = 0; i < 2; i++) {
                    if (dp[i][j-1] < dp[(i+1)%2][j-1] + input[i][j]) {
                        dp[i][j] = dp[(i+1)%2][j-1] + input[i][j];
                    } else {
                        dp[i][j] = dp[i][j-1];
                    }
                    max = Math.max(dp[i][j], max);
                }
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}