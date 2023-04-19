import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // map을 선언하고, 초기값으로 큰 수를 넣어둔다.
        int[][] map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) Arrays.fill(map[i], Integer.MAX_VALUE/2);

        // 길을 입력받는다.
        // 일방통행이라면 a -> b는 0이고 b -> a는 1
        // 양방향이라면 a -> b와 b -> a 모두 0
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = 0;
            map[b][a] = c == 1 ? 0 : 1;
        }

        // 중간에 다른 길로 우회해서 갔을 경우의 최솟값을 저장한다.
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) map[i][j] = 0;
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 결과 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // K개의 질문에 답하기
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 미리 만들어둔 map에 최소한으로 바꾸며 도달하는 값이 적혀있다.
            sb.append(map[a][b]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}