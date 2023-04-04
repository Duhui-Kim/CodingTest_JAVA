import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물건의 개수 N과 무게 한계 K
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // K+1 크기의 dp 배열을 만들고 -1로 초기화한다.
        long[] dp = new long[K+1];
        Arrays.fill(dp, -1);
        
        // dp[0]은 0부터 시작
        dp[0] = 0;

        // 최댓값 저장할 max
        long max = 0;

        // 각 물건을 하나씩 꺼내서 비교한다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // a는 물건의 무게, b는 가치
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // dp 배열의 뒤에서부터 앞으로 오며, -1이 아닌 경우 현재 물건의 무게를 더한 값이
            // K를 벗어나지 않았다면, 현재물건 + 추가된 물건 idx에 가치의 최댓값을 저장
            for (int j = K; j >= 0; j--) {
                if (dp[j] >= 0) {
                    if (j + a > K) continue;

                    dp[j+a] = Math.max(dp[j+a], dp[j] + b);
                    max = Math.max(max, dp[j+a]);
                }
            }
        }
        // 가치 출력
        System.out.println(max);
    }
}