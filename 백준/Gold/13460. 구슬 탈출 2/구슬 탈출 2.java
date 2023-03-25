import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static int[] red;
    private static int[] blue;
    private static int N;
    private static int M;
    // 왼쪽 오른쪽 위 아래
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // N * M 크기의 map 배열 생성
        map = new char[N][M];

        red = new int[2];
        blue = new int[2];

        // map 입력받기
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            // map을 입력받으면서 파란공과 빨간공 위치 저장하기
            for (int j = 0; j < M; j++) {
                if(red[0] != 0 && blue[0] != 0) break;
                if(map[i][j] == 'B') {
                    map[i][j] = '.';
                    blue[0] = i;
                    blue[1] = j;
                } else if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    red[0] = i;
                    red[1] = j;
                }
            }
        }
        // 각 방향별 시작
        for (int i = 0; i < 4; i++) {
            backTracking(1, i);
        }

        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void backTracking(int k, int dir) {
        if(k > 10 || k >= min) return;

        // 빨간공과 파란공 좌표값 저장해두고, 각 방향별로 굴리기
        int Rx = red[0];
        int Ry = red[1];
        int Bx = blue[0];
        int By = blue[1];

        // R을 먼저 움직여야하면 true, B를 먼저 움직여야하면 false
        boolean rb = false;

        // 굴리는 순서는 해당 방향에 더 가까운 애부터 굴리기
        switch (dir) {
            // 방향이 왼쪽일 때
            case 0:
                if(Ry < By) rb = true;
                break;
            // 오른쪽일 때
            case 1:
                if(Ry > By) rb = true;
                break;
            // 위쪽일 때
            case 2:
                if(Rx < Bx) rb = true;
                break;
            // 아래쪽일 때
            case 3:
                if(Rx > Bx) rb = true;
                break;
        }
        rollingBall(red, dir);
        rollingBall(blue, dir);

        // 파란공이 구멍에 도착했다면 공 원래대로 돌리고 리턴
        if (map[blue[0]][blue[1]] == 'O') {
            red[0] = Rx;
            red[1] = Ry;
            blue[0] = Bx;
            blue[1] = By;
            return;
        }

        // 빨간공이 구멍에 도착했다면 그 때의 시간값을 min과 비교하고 공 원래대로 돌리고 리턴
        if(map[red[0]][red[1]] == 'O') {
            min = Math.min(min, k);
            red[0] = Rx;
            red[1] = Ry;
            blue[0] = Bx;
            blue[1] = By;
            return;
        // 도착점에 도달하지 않았을 경우 좌표가 같다면 겹쳐있는 것이므로 안겹치게해주기
        } else if (red[0] == blue[0] && red[1] == blue[1]) {
            // 빨간공이 먼저 움직였으면 파란공 뒤로
            if(rb) {
                blue[0] -= dx[dir];
                blue[1] -= dy[dir];
            // 파란공이 먼저 움직였으면 빨간공 뒤로
            } else {
                red[0] -= dx[dir];
                red[1] -= dy[dir];
            }
        }

        // 왼쪽, 오른쪽으로 굴렸으면 다음엔 위나 아래로만 굴려야함
        if(dir == 0 || dir == 1) {
            backTracking(k+1, 2);
            backTracking(k+1, 3);
        }
        // 위, 아래로 굴렸으면 다음엔 오른쪽이나 왼쪽으로만 굴려야함
        else if (dir == 2 || dir == 3) {
            backTracking(k+1, 0);
            backTracking(k+1, 1);
        }

        // 빠져나가기 전에 공 위치 원래대로 해놓기
        red[0] = Rx;
        red[1] = Ry;
        blue[0] = Bx;
        blue[1] = By;
    }

    // 공 굴리는 method
    private static void rollingBall(int[] ball, int dir) {
        int x = ball[0] + dx[dir];
        int y = ball[1] + dy[dir];

        // .인 경우 계속해서 진행하고, #인 경우 뒤로 한 칸, O인 경우 해당 위치
        while (x >= 0 && y >= 0 && x < N && y < M) {
            if(map[x][y] == '.') {
                x += dx[dir];
                y += dy[dir];
            } else if (map[x][y] == 'O') {
                break;
            } else if (map[x][y] == '#') {
                x -= dx[dir];
                y -= dy[dir];
                break;
            }
        }
        ball[0] = x;
        ball[1] = y;
    }
}