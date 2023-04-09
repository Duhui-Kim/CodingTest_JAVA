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

        // 동전의 개수를 체크할 dp 배열 선언
        int[] dp = new int[k+1];
        
        // 큰 값으로 채워넣고 초기값 0 저장
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        // coin을 입력받고, i원에서 i+coin 값이 되기 위한 개수는 i일 때의 개수 + 1
        // 그 중에서 최솟값을 dp 배열에 저장한다.
        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());

            for (int j = coin; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }

        // k원의 값이 초기값과 같으면 불가능한 경우이므로 -1 출력
        // 아닌 경우 값 출력
        if (dp[k] == Integer.MAX_VALUE / 2) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}