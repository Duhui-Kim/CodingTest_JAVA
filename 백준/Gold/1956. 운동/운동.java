import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드의 개수 V, 간선의 개수 E
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 거리 담을 배열 선언
        int[][] map = new int[V+1][V+1];

        // 초기값 아주 큰 값으로 설정
        for (int i = 0; i < V+1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE / 2);
        }

        // 간선 저장하기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
        }

        // 최단거리 저장하기
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) continue;

                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 최소거리를 아주 큰 값으로 설정
        int minLength = Integer.MAX_VALUE / 2;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j < V; j++) {
                if (i == j) continue;

                // 최솟값을 저장한다
                minLength = Math.min(minLength, map[i][j] + map[j][i]);
            }
        }

        // 초기값 그대로면 불가능한 경우이므로 -1 출력, 아닌 경우 최솟값 출력
        if (minLength == Integer.MAX_VALUE / 2) {
            System.out.println(-1);
        } else {
            System.out.println(minLength);
        }
    }
}