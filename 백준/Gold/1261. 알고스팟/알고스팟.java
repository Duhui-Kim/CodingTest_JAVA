import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 열의 크기 N과 행의 크기 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 해당 크기의 map과 부순 벽의 수 wall 저장
        char[][] map = new char[M][N];
        int[][] wall = new int[M][N];

        // map 입력받기
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 초기값 채우기
        for (int i = 0; i < M; i++) {
            Arrays.fill(wall[i], 10000);
        }

        // BFS용 delta
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // queue에 시작점 넣고 초기화
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        wall[0][0] = 0;

        // queue가 빌 때까지 진행
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 범위 밖이면 지나감
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                // 현재 값보다 출발값 + 기존값이 작으면 바꾸고 queue에 넣음
                if (wall[nx][ny] > wall[x][y] + (map[nx][ny] - '0')) {
                    queue.offer(new int[] {nx, ny});
                    wall[nx][ny] = wall[x][y] + (map[nx][ny] - '0');
                }
            }
        }
        System.out.println(wall[M-1][N-1]);
    }
}