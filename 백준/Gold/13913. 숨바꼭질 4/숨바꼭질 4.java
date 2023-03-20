import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        // 동생의 위치가 수빈이보다 작을 경우 이동 시간은 항상 N - K
        if(K <= N) {
            sb.append(N-K).append("\n");
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            return;
        }

        // BFS를 진행하기 위해 queue를 선언한다.
        Queue<Integer> queue = new LinkedList<>();

        // map은 0~100000까지의 위치를 표시하기 위해 100001 크기의 배열을 선언한다.
        int[][] map = new int[100001][2];

        // BFS용 배열
        int[] dx = {-1, 1, 0};

        // K를 queue에 넣고 시작한다.
        queue.offer(K);

        // queue가 빌 때까지 진행
        loop:
        while (!queue.isEmpty()) {
            int x = queue.poll();
            if(x % 2 == 0) dx[2] = x/2;
            else dx[2] = 0;

            // -1, +1, /2에 이전 이동시간+1을 표시한다.
            for (int i = 0; i < 3; i++) {
                int nx = x - dx[i];

                if(nx < 0 || nx >= 100001) continue;
                if(nx == K) continue;

                if(map[nx][0] == 0) {
                    map[nx][0] = map[x][0] + 1;
                    map[nx][1] = x;
                    queue.offer(nx);
                }
                // N을 만나면 종료
                if(nx == N) {
                    break loop;
                }
            }
        }

        // map[N]에 최소 시간이 입력되어있음
        sb.append(map[N][0]).append("\n");

        sb.append(N).append(" ");

        // map[N][1]에 어느 곳에서 왔는지 저장했으므로 순서대로 따라간다.
        while (N != K) {
            N = map[N][1];
            sb.append(N).append(" ");
        }

        System.out.println(sb);
    }
}