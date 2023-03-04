import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기 N과 사과의 개수 K
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 보드 만들기
        int[][] board = new int[N+1][N+1];

        // 사과 입력받기
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        // 명령어 입력받기
        int cNum = Integer.parseInt(br.readLine());

        // 머리용 꼬리용 따로 저장
        int[][] hCmd = new int[cNum][2];
        int[][] tCmd = new int[cNum][2];

        for (int i = 0; i < cNum; i++) {
            st = new StringTokenizer(br.readLine());
            hCmd[i][0] = Integer.parseInt(st.nextToken());

            String d = st.nextToken();
            // D면 오른쪽으로 90도이므로 1, 왼쪽으로 90도이면 -1
            if(d.equals("D")) {
                hCmd[i][1] = 1;
            } else {
                hCmd[i][1] = -1;
            }

            tCmd[i] = hCmd[i].clone();
        }

        // 각각 x, y, dir
        int[] head = new int[3];
        head[0] = 1;
        head[1] = 1;
        int[] tail = head.clone();

        // 시간과 delay
        int time = 0;
        int delay = 0;

        // delta 배열 (우 하 좌 상)
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int hidx = 0;
        int tidx = 0;

        int x = head[0];
        int y = head[1];
        int dir = head[2];

        // 범위를 벗어나지 않을 때까지 진행
        while (x + dx[dir] >= 1 && y + dy[dir] >= 1 && x + dx[dir] < N+1 && y + dy[dir] < N+1) {
            // 시간을 올려주고 머리를 이동시킨다.
            time++;
            x += dx[dir];
            y += dy[dir];

            // 이동지점이 2이면 꼬리를 지연시키고, board를 1로 바꾼다.
            boolean delayed = false;
            if(board[x][y] == 2) {
                board[x][y] = 1;
                delayed = true;
                delay++;

            // 이동지점이 0이면 board만 1로 바꾼다.
            } else if (board[x][y] == 0) {
                board[x][y] = 1;

            // 그렇지 않으면 끝낸다.
            } else {
                time--;
                break;
            }

            // 사과를 먹지 않았을 때, 꼬리도 따라가주며 1 -> 0으로 바꾼다.
            if(!delayed) {
                board[tail[0]][tail[1]] = 0;
                tail[0] += dx[tail[2]];
                tail[1] += dy[tail[2]];
            }

            // 시간에 도달하면 방향 바꿔주기
            if(hidx < hCmd.length && time == hCmd[hidx][0]) {
                dir = (dir + hCmd[hidx][1] + 4) % 4;
                hidx++;
            }
            // 꼬리도 바꿔주기
            if(tidx < tCmd.length && time - delay == tCmd[tidx][0]) {
                tail[2] = (tail[2] + tCmd[tidx][1] + 4) % 4;
                tidx++;
            }

        }
        System.out.println(time+1);
    }
}