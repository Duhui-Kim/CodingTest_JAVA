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

        // 행의 길이 N, 열의 길이 M, 벽 부술 수 있는 횟수 K
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 1, 1 크기이면 1 출력
        if(N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        // map을 입력받는다.
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // 중복 이동 방지용 배열
        boolean[][][] check = new boolean[K+1][N][M];
        check[K][0][0] = true;

        // BFS 진행용 queue
        Queue<int[]> queue = new LinkedList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // x좌표, y좌표, 벽부술 횟수 k, time t 4개를 저장
        // 시작좌표는 0,0, 벽부수는 횟수는 k, 시작지점 t는 1, day 저장
        int[] arr = new int[5];
        arr[2] = K;
        arr[3] = 1;
        arr[4] = 1;

        queue.offer(arr);

        // queue가 빌 때까지 진행
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            int k = queue.peek()[2];
            int t = queue.peek()[3];
            int day = queue.poll()[4];

            // 사방탐색 진행
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 밖이거나 이미 지나간 곳이면 지나가지 않음
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 도착점을 만나면 종료
                if(nx == N-1 && ny == M-1) {
                    System.out.println(t+1);
                    return;
                }
                
                // 벽을 만났을 때, 낮인 경우와 밤인 경우가 다름
                if(map[nx][ny] == '1') {
                    // 벽 부술 수 있는 횟수가 남아있으면
                    if(k > 0) {
                        if(check[k-1][nx][ny]) continue;

                        // 낮인 경우에는 벽 부수고 진행
                        if(day > 0) {
                            check[k-1][nx][ny] = true;
                            arr = new int[5];
                            arr[0] = nx;
                            arr[1] = ny;
                            arr[2] = k-1;
                            arr[3] = t+1;
                            arr[4] = - day;
                            queue.offer(arr);
                        }
                        // 밤인 경우에는 제자리에 머무른다.
                        else {
                            arr = new int[5];
                            arr[0] = x;
                            arr[1] = y;
                            arr[2] = k;
                            arr[3] = t+1;
                            arr[4] = - day;
                            queue.offer(arr);
                        }
                    }
                    // k가 0 이하이면 벽 못부수므로 지나감
                    else {
                        continue;
                    }
                // 벽을 만나지 않았을 때는 그냥 지나감
                } else {
                    if(check[k][nx][ny]) continue;

                    check[k][nx][ny] = true;
                    arr = new int[5];
                    arr[0] = nx;
                    arr[1] = ny;
                    arr[2] = k;
                    arr[3] = t+1;
                    arr[4] = - day;
                    queue.offer(arr);
                }
            }
        }
        // 끝까지 진행했는데 while문을 빠져나온 경우는 도착 못함
        System.out.println(-1);
    }
}