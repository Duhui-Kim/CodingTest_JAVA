import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 으른쪽, 아래, 대각선 체크
    private static int[] dx = {0, 1, 1};
    private static int[] dy = {1, 0, 1};
    private static int[][] map;
    private static int N;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0, 1, 0);

        System.out.println(answer);
    }

    private static void backTracking(int x, int y, int dir) {
        // 끝에 도달했다면 cnt 증가
        if (x == N-1 && y == N-1) {
            answer++;
            return;
        }

        // 세로가 아닐 때 가로 체크
        if (dir != 1) {
            int nx = x + dx[0];
            int ny = y + dy[0];

            if (nx < N && ny < N && map[nx][ny] == 0) {
                backTracking(nx, ny, 0);
            }
        }
        // 가로가 아닐 때 세로 체크
        if (dir != 0) {
            int nx = x + dx[1];
            int ny = y + dy[1];

            if (nx < N && ny < N && map[nx][ny] == 0) {
                backTracking(nx, ny, 1);
            }
        }
        // 대각선 체크
        boolean check = true;
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= N || ny >= N || map[nx][ny] == 1) {
                check = false;
                break;
            }
        }
        if (check) {
            backTracking(x + dx[2], y + dy[2], 2);
        }
    }
}