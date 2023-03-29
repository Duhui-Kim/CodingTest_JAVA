import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 입력받을 map 선언 ( 1 ~ N까지의 수가 주어지므로 +1 크기로 만듦
        int[][] map = new int[N+1][N+1];

        // 두 점과 비용 입력받기
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 출발점과 도착점이 같은 경우는 최소비용이 항상 0이므로 건너뜀
            if (a == b) continue;

            // 기존 값과 비교해서 작은 값 넣어줌
            if(map[a][b] != 0 && map[a][b] < c) {
                continue;
            } else {
                map[a][b] = c;
            }
        }

        // i == j인 경우는 건너뛰고 0이 들어가있으면 큰 값을 넣어줌
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if(map[i][j] == 0) map[i][j] = 100000000;
            }
        }

        // 각 정점에서 한 지점을 통해 가는 것과 그냥 가는 것을 비교해서 최솟값을 갱신
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 큰 값이 그대로 들어있으면 불가능한 경로이므로 0 출력
        // 아닌 경우 최소비용 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(map[i][j] == 100000000) sb.append(0).append(" ");
                else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}