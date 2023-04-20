import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Pair {
        int x, y, time;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
            this.time = 0;
        }

        private Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    private static int N, M, blank, idx, minTime;
    private static boolean[][] map;
    private static Pair[] virus;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blank = 0;
        idx = 0;
        virus = new Pair[10];
        map = new boolean[N][N];
        minTime = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                // 시작지점 배열에 담고 빈칸 개수에 저장
                if (num == 2) {
                    virus[idx++] = new Pair(i, j);
                // 벽 배열에 담기
                } else if (num == 1) map[i][j] = true;
                // 공백일 경우 빈칸 개수에 저장
                if (num != 1) blank++;
            }
        }

        backTracking(0, 0, 0);

        if (minTime == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minTime);
    }

    private static void backTracking(int num, int select, int k) {
        // M개가 선택되었다면 BFS 진행
        if (select == M) {
            Queue<Pair> queue = new LinkedList<>();
            boolean[][] check = new boolean[N][N];

            for (int i = 0; i < idx; i++) {
                if ((num & 1 << i) > 0) {
                    queue.offer(virus[i]);
                    check[virus[i].x][virus[i].y] = true;
                }
            }
            int time = BFS(queue, check);

            minTime = Math.min(time, minTime);
            return;
        }
        // idx에 도달하면 더 이상 선택 불가능하므로 종료
        if (k == idx) return;

        // 선택하고 넘기기
        backTracking(num | 1 << k, select+1, k+1);

        // 안고르고 넘기기
        backTracking(num, select, k+1);
    }

    private static int BFS(Queue<Pair> queue, boolean[][] check) {
        int cnt = 0;
        int time = 0;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            cnt++;

            time = Math.max(time, p.time);

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] || check[nx][ny]) continue;

                check[nx][ny] = true;
                queue.offer(new Pair(nx, ny, p.time + 1));
            }
        }

        if (cnt < blank) return Integer.MAX_VALUE;
        return time;
    }
}
