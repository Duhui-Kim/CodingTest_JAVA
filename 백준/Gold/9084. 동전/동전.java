import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수 입력
        int testCase = Integer.parseInt(br.readLine());

        // 테스트케이스만큼 진행
        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(br.readLine());

            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 코인을 배열에 입력받는다.
            int[] coin = new int[N];
            for (int i = 0; i < N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            // 목표값을 입력받는다.
            int money = Integer.parseInt(br.readLine());

            int[] answer = new int[money+1];
            answer[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = coin[i]; j <= money; j++) {
                    answer[j] = answer[j] + answer[j-coin[i]];
                }
            }
            sb.append(answer[money] + "\n");
        }
        System.out.println(sb);
    }
}
