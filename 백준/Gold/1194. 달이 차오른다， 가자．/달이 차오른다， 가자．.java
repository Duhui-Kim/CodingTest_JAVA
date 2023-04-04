import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        int x;
        int y;
        int t;
        int key;
        int cnt;
        private Pair(int x, int y, int t, int key, int cnt) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.key = key;
            this.cnt = cnt;
        }

    }
    public static void main(String[] args) throws IOException {
        // BFS를 진행하는데, 1을 만나면 종료
        // 키를 만나면 해당 좌표부터 다시 BFS 진행 + 체크 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // map, 방문체크 배열 생성
        char[][] map = new char[N][M];
        int[][][] check = new int[7][N][M];

        // map 입력받기
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().trim().toCharArray();
        }

        // BFS용 queue
        Queue<Pair> queue = new LinkedList<>();

        // 시작점 찾고 queue에 넣고 체크하기
        loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    queue.offer(new Pair(i, j, 0, 1 << 8, 0));
                    check[0][i][j] = 1 << 8;
                    break loop;
                }
            }
        }

        // BFS용 delta 배열
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int answer = -1;

        loop:
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];
                // 범위 밖이면 지나감
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                // 이미 방문했으면 지나감
                if ((check[pair.cnt][nx][ny] & pair.key) == pair.key) continue;
                // 벽이면 지나감
                if (map[nx][ny] == '#') continue;


                // 1을 만나면 탈출
                if (map[nx][ny] == '1') {
                    answer = pair.t + 1;
                    break loop;
                }
                // 문일 경우
                else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    // 키가 있을 경우에 체크해주고 queue에 넣어줌
                    if ((pair.key & 1 << (map[nx][ny] - 'A')) > 0) {
                        check[pair.cnt][nx][ny] =  check[pair.cnt][nx][ny] | pair.key;
                        queue.offer(new Pair(nx, ny, pair.t+1, pair.key, pair.cnt));
                    }
                }
                // 열쇠일 경우 열쇠 체크
                else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int key = pair.key;
                    int cnt = pair.cnt;
                    check[pair.cnt][nx][ny] = check[pair.cnt][nx][ny] | key;

                    // 아직 key가 없었다면
                    if ((pair.key & 1 << (map[nx][ny] - 'a')) == 0) {
                        key = pair.key | 1 << (map[nx][ny] - 'a');
                        cnt = pair.cnt + 1;
                    }

                    // 체크해주고 queue에 넣어줌
                    check[pair.cnt][nx][ny] = check[pair.cnt][nx][ny] | key;
                    queue.offer(new Pair(nx, ny, pair.t + 1, key, cnt));

                }
                // 위의 경우가 다 아니면 .을 만났으므로 진행
                else {
                    check[pair.cnt][nx][ny] = check[pair.cnt][nx][ny] | pair.key;
                    queue.offer(new Pair(nx, ny, pair.t + 1, pair.key, pair.cnt));
                }
            }
        }
        System.out.println(answer);
    }
}