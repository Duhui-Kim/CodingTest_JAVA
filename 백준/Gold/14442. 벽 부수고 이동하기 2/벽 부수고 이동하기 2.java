import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행의 개수 N과 열의 개수 M, 벽부수는 횟수 K를 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        if(N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        // 해당 크기의 map을 입력받고, 해당 크기의 이동시간 체크 배열도 만든다.
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // 시간 체크용 time 배열
        int[][][] time = new int[K+1][N][M];

        // 시작점은 1이므로
        time[K][0][0] = 1;

        // BFS용 Queue와 delta 배열
        Queue<int[]> queue = new LinkedList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[] arr = new int[3];
        arr[2] = K;
        queue.offer(arr);

        boolean isEscape = false;
        int answer = 0;

        loop:
        while (!queue.isEmpty()) {
            // queue에서 좌표 x, y와 벽 부술 수 있는 횟수 k를 꺼낸다.
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            int k = queue.poll()[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 밖이거나 이미 지나온 값이면 지나간다.
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(nx == N-1 && ny == M-1) {
                    answer = time[k][x][y] + 1;
                    isEscape = true;
                    break loop;
                }

                // 벽을 만났을 경우
                if(map[nx][ny] == '1') {
                    // k가 있을 때 k를 1 줄여주고 진행시킨다.
                    if(k > 0) {
                        // 이미 지나간 값이면 지나감
                        if(time[k-1][nx][ny] > 0) continue;

                        time[k-1][nx][ny] = time[k][x][y] + 1;
                        arr = new int[3];
                        arr[0] = nx;
                        arr[1] = ny;
                        arr[2] = k-1;
                        queue.offer(arr);
                    }
                    // k가 없을 때는 진행 못하므로 지나감
                    else continue;
                }
                // 벽을 만나지 않았을 경우
                else {
                    // 이미 지나간 값이면 지나감
                    if(time[k][nx][ny] > 0) continue;

                    time[k][nx][ny] = time[k][x][y] + 1;
                    arr = new int[3];
                    arr[0] = nx;
                    arr[1] = ny;
                    arr[2] = k;
                    queue.offer(arr);
                }
            }
        }

        // 탈출했다면 answer 출력, 그냥 끝나버리면 -1 출력
        if (isEscape) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}