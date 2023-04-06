import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int max;
    static class Pair {
        int x, y, cnt;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
            this.cnt = 0;
        }
        private Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 열의 크기 입력받기
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 주어진 맵과 빙하 녹는 날짜 저장할 ice, 이동경로 표시할 check
        char[][] map = new char[R][C];
        int[][] ice = new int[R][C];
        int[][] check = new int[R][C];

        // 맵 입력받기
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 이동경로와 빙하 초기화하기
        for (int i = 0; i < R; i++) {
            Arrays.fill(ice[i], Integer.MAX_VALUE/2);
            Arrays.fill(check[i], Integer.MAX_VALUE/2);
        }

        // 백조 담을 리스트
        List<int[]> swan = new ArrayList<>(2);

        // 얼음 녹는 날짜 세는 용도
        Queue<Pair> queue = new LinkedList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 백조 담고 물 위치 담기
        loop:
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    swan.add(new int[] {i, j});
                    map[i][j] = '.';
                }
                if (map[i][j] == '.' && ice[i][j] == Integer.MAX_VALUE/2) {
                    ice[i][j] = 0;
                    queue.offer(new Pair(i, j));
                }
            }
        }
        // 얼음 녹는 날짜 체크
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                if (map[nx][ny] == 'X') {
                    if (ice[nx][ny] > ice[p.x][p.y] + 1) {
                        ice[nx][ny] = ice[p.x][p.y] + 1;
                        queue.offer(new Pair(nx, ny));
                    }
                }
            }
        }

        // 백조 좌표가 시작점
        int x = swan.get(0)[0];
        int y = swan.get(0)[1];
        max = Integer.MAX_VALUE / 2;

        // 도착점
        int end_x = swan.get(1)[0];
        int end_y = swan.get(1)[1];

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);

        check[x][y] = 0;
        pq.offer(new Pair(x, y, 0));

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                int cnt = pair.cnt;

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                if (nx == end_x && ny == end_y) {
                    System.out.println(cnt);
                    return;
                }

                if (check[nx][ny] > ice[nx][ny] + cnt) {
                    check[nx][ny] = ice[nx][ny] + cnt;
                    if (ice[nx][ny] > cnt) cnt = ice[nx][ny];
                    pq.offer(new Pair(nx, ny, cnt));
                }
            }
        }

    }
}