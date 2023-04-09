import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());

            for (int j = coin; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }

        if (dp[k] == Integer.MAX_VALUE / 2) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}