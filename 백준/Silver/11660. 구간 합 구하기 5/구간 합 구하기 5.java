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

        int[][] map = new int[N+1][N+1];

        // 배열의 누적합 구하기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 자기 자신 값 + 위의 값 + 왼쪽값 - 대각선 값
                map[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] += map[i-1][j];
                map[i][j] += map[i][j-1];
                map[i][j] -= map[i-1][j-1];
            }
        }

        // 누적합이 구해졌으므로 x2, y2 값에서 제외되는 범위의 값 빼주기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1]).append("\n");
        }
        System.out.println(sb);
    }
}