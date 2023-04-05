import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력받을 수열의 크기 N
        int N = Integer.parseInt(br.readLine());

        // 수열 크기만큼의 1차원 배열과 dp 배열 생성
        int[] input = new int[N+1];
        int[][] dp = new int[N+1][N+1];

        // 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 한 자리 수는 한 자리의 팰린드롬이다.
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;

            // 두 자리 수는 앞 뒤의 문자만 같으면 팰린드롬
            if (input[i-1] == input[i]) {
                dp[i-1][i] = 1;
            }
        }
        
        // a와 b가 주어지면, a+1, b-1까지가 팰린드롬이었다면, a와 b가 같으면 팰린드롬이므로
        // 이를 활용해서 표를 채운다.
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                if (j-i < 0) continue;

                if (dp[j-i+1][j-1] == 1 && input[j-i] == input[j]) {
                    dp[j-i][j] = 1;
                }
            }
        }

        // 홍준이의 질문 수 M
        int M = Integer.parseInt(br.readLine());

        // 질문을 입력받으며 dp table에 저장된 값을 출력한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(dp[a][b]).append("\n");
        }
        System.out.println(sb);
    }
}