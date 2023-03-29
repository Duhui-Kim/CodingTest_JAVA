import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 최단거리를 저장할 map과 어디를 통해서 가는지 경로를 저장할 path 배열 선언
        int[][] map = new int[N+1][N+1];
        int[][] path = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 출발점 a, 도착점 b, 비용 c
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // map[a][b]가 0이 아닌데 c보다 작다면 지나가고
            if(map[a][b] != 0 && map[a][b] < c) {
                continue;
            }
            // 0이거나 c가 더 작으면 c로 바꾼다
            // 경로에도 어디로 가야하는지를 표시
            else {
                map[a][b] = c;
                path[a][b] = b;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 자기자신은 0으로 놓고, 0인 지점은 한 번에 못가는 지점이므로 큰 수를 넣어둠
                if (i == j) map[i][j] = 0;
                else if (map[i][j] == 0) map[i][j] = 100000000;
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    // 한번에 가는 것과 다른 지점을 통해 가는 것을 비교해서,
                    // k를 통해가는 것이 더 빠르다면 비용을 바꿔주고
                    // i->j를 i->k->j로 바꿔주기 위해 path도 바꿔줌
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        // 최저비용 저장과 경로 저장 완료했으므로 출력 준비
        StringBuilder sb = new StringBuilder();

        // 최저비용 출력 준비
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 엄청나게 큰 수 그대로이면 갈 수 있는 경로가 없으므로 0 출력
                // 경로도 -1로 써놓음
                if(map[i][j] == 100000000) {
                    sb.append(0).append(" ");
                    path[i][j] = -1;
                }
                else sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 몇 개의 경로를 거쳐가는지 체크도 해야해서 queue 선언
        Queue<Integer> queue = new LinkedList<>();
        
        // 반복문을 돌며 각 정점에서 다른 정점으로 가는 경우를 구함
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // i == j이면 0 출력 후 지나감
                if(i == j) {
                    sb.append(0).append("\n");
                    continue;
                }
                // path가 -1이면 경로가 없으므로 0 출력 후 지나감
                if (path[i][j] < 0) {
                    sb.append(0).append("\n");
                    continue;
                }

                // 시작지점을 st = i로 놓고 path[st][j]가 j가 될 때까지 st를 path[st][j]로 바꿔가며 진행
                int st = i;
                queue.offer(i);
                while (path[st][j] != j) {
                    queue.offer(path[st][j]);
                    st = path[st][j];
                }
                // 반복문 종료되었으면 도착했으므로 queue에 어디를 통해 왔는지 적혀있음
                queue.offer(j);
                
                // size를 먼저 출력하고 queue에 있는 값들을 차례로 출력
                sb.append(queue.size()).append(" ");
                while (!queue.isEmpty()) {
                    sb.append(queue.poll()).append(" ");
                }
                sb.append("\n");
            }
        }
        // 정답 출력
        System.out.println(sb);
    }
}