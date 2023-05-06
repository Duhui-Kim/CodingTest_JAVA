import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int answer, N, M;
    private static double sum, cnt;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열의 크기 N, M과 회전횟수 T
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        // 초기값 저장해두기
        answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer += map[i][j];
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 1. x의 배수를 d 방향으로 k칸 회전시키기
            rotate(map, x, d, k);

            // 2. 인접 + 숫자 같은 애들 체크
            boolean[][] check = new boolean[N][M];
            sum = 0; cnt = 0;
            boolean flag = BFS(map, check);

            // 3. flag에 따라 0으로 만들거나 평균값 처리하기
            if (flag) {
                makeZero(map, check);
            } else {
                avgCheck(map);
            }
        }
        System.out.println(answer);
    }

    // 평균보다 큰 값은 빼주고 작은 값은 더해주기
    private static void avgCheck(int[][] map) {
        if (cnt == 0) return;

        double avg = sum / cnt;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (map[i][j] < avg) {
                    answer++;
                    map[i][j]++;
                } else if (map[i][j] > avg) {
                    answer--;
                    map[i][j]--;
                }
            }
        }
    }

    // 같은 값들은 0으로 만들기
    private static void makeZero(int[][] map, boolean[][] check) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j]) {
                    answer -= map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    }

    private static boolean BFS(int[][] map, boolean[][] check) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        boolean flag = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] != 0) {
                    sum += map[i][j];
                    cnt ++;

                    visited[i][j] = true;
                    queue.offer(new int[] {i, j});

                    while (!queue.isEmpty()) {
                        int[] tmp = queue.poll();
                        int x = tmp[0];
                        int y = tmp[1];

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = (y + M + dy[k]) % M;

                            if (nx < 0 || nx >= N) continue;

                            // 0인 값은 진행 X
                            if (map[nx][ny] == 0) continue;
                            // 같은 값이 있다면 체크
                            if (map[x][y] == map[nx][ny]) {
                                check[x][y] = true;
                                check[nx][ny] = true;
                                flag = true;
                            }
                            // 이미 방문한 값 재방문 X
                            if (visited[nx][ny]) continue;
                            visited[nx][ny] = true;

                            // 합계 더해주고 cnt도 증가
                            sum += map[nx][ny];
                            cnt++;
                            queue.offer(new int[] {nx, ny});
                        }
                    }
                }
            }
        }
        return flag;
    }


    // 외판원 회전 method
    private static void rotate(int[][] map, int x, int d, int k) {
        // d가 1이면 반시계방향으로 회전
        if (d == 1) k = -k;

        for (int i = x-1; i < N; i += x) {
            int[] arr = map[i].clone();

            for (int j = 0; j < M; j++) {
                map[i][(j+k+M) % M] = arr[j];
            }
        }
    }
}