import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 지도의 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 주사위의 좌표
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        // 명령어 개수
        int cNum = Integer.parseInt(st.nextToken());

        // 지도와 주사위 배열 생성
        int[][] map = new int[N][M];
        int[] dice = new int[6];

        // 지도 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 이동 시 사용할 delta 배열 생성 (동쪽 1, 서쪽 2, 북쪽 3, 남쪽 4)
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, 1, -1, 0, 0};

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cNum; i++) {
            int cmd = Integer.parseInt(st.nextToken());
            int nx = x + dx[cmd];
            int ny = y + dy[cmd];
            // 범위 밖이면 명령 수행 X 출력도 X
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            // 범위 안이면 이동하고 dice 숫자를 바꿔줌
            x = nx;
            y = ny;
            changeDice(dice, cmd);
            
            // 이동한 map의 값이 0일 때는 주사위 2번의 값을 넣어주고,
            // 값이 있을 때는 주사위 2번에 값을 복사하고 0으로 바꿈
            if(map[x][y] == 0) {
                map[x][y] = dice[2];
            } else {
                dice[2] = map[x][y];
                map[x][y] = 0;
            }
            sb.append(dice[0] + "\n");
        }
        System.out.println(sb);
    }

    private static void changeDice(int[] dice, int cmd) {
        int tmp = dice[0];

        // 방향에 따라 값 바꿔줌
        switch (cmd) {
            case 1:
                dice[0] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[4];
                dice[4] = tmp;
                break;
            case 2:
                dice[0] = dice[4];
                dice[4] = dice[2];
                dice[2] = dice[5];
                dice[5] = tmp;
                break;
            case 3:
                dice[0] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = tmp;
                break;
            case 4:
                dice[0] = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[1];
                dice[1] = tmp;
                break;
        }
    }
}