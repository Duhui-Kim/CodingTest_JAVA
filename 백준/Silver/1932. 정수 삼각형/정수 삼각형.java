import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        int[] dp = new int[N+1];

        int cnt = 1;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for (int j = cnt; j > 0; j--) {
                dp[j] = Integer.parseInt(st.nextToken()) + Math.max(dp[j], dp[j-1]);
                if (i == N-1) answer = Math.max(answer, dp[j]);
            }
            cnt++;
        }
        System.out.println(answer);
    }
}