import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N은 격자의 크기, M은 상어의 수, k는 냄새 지속시간
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 전체 상어 정보 저장 배열(0 번호, 1 x좌표, 2 y좌표, 3 dir)
        // 상어번호 1부터 시작하므로 M+1크기로 만들기
        int[][] shark = new int[M+1][3];

        // 퇴출상어리스트
        ArrayList<Integer> list = new ArrayList<>();

        // 0, 1은  x, y좌표, 2는 현재 상어 위치와 냄새 번호, 남은시간
        int[][][] sea = new int[N][N][2];

        // 숫자를 입력받는데, 어차피 int 배열의 기본값은 0이므로
        // 숫자가 들어올 때만 map을 update 시켜주고, 해당 상어의 좌표까지 입력받는다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num != 0) {
                    sea[i][j][0] = num;
                    sea[i][j][1] = k;
                    shark[num][0] = i;
                    shark[num][1] = j;
                }
            }
        }
        // 상=1, 하=2, 좌=3, 우=4
        int[] dx = {0, -1, 1, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};

        // 상어 방향 입력
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            shark[i][2] = Integer.parseInt(st.nextToken());
        }

        // 이동 우선순위 저장 배열
        // 0번은 상어번호, 1번은 상어방향, 2번은 탐색 순서
        int[][][] priority = new int[M+1][5][4];

        for(int i=1; i<=M; i++) {
            for(int j=1; j<=4; j++) {
                st = new StringTokenizer(br.readLine());
                for(int m=0; m<4; m++) {
                    priority[i][j][m] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 1번 상어만 남거나 1000초가 넘어가면 종료
        // 처음에 1번 상어만 주어졌을 때 time은 0에서 종료해야하므로 순서 주의
        boolean flag = false;
        int time = 0;
        while(time <= 1000) {
            if(M-1 == list.size()) {
                flag = true;
                break;
            }
            time++;

            // 모든 상어 이동 시작
            loop:
            for(int i=1; i<=M; i++) {
                // 퇴출상어에 포함되었으면 진행 x
                if(list.contains(i)) continue;

                int x = shark[i][0];
                int y = shark[i][1];
                int dir = shark[i][2];

                // 빈 칸 찾기
                for(int j=0; j<4; j++) {
                    // dx[우선순위(번호, 방향, 순서)]
                    int nx = x + dx[priority[i][dir][j]];
                    int ny = y + dy[priority[i][dir][j]];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    // 빈 칸을 찾으면 이동시키기 (냄새는 나중에)
                    if(sea[nx][ny][0] == 0) {
                        shark[i][0] = nx;
                        shark[i][1] = ny;
                        shark[i][2] = priority[i][dir][j];
                        continue loop;
                    }
                }

                // 빈 칸 못찾았을 경우 자기 냄새 칸으로 가기
                for(int j=0; j<4; j++) {
                    int nx = x + dx[priority[i][dir][j]];
                    int ny = y + dy[priority[i][dir][j]];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    // 자기냄새 칸 찾으면 돌아가기
                    if(sea[nx][ny][0] == i) {
                        shark[i][0] = nx;
                        shark[i][1] = ny;
                        shark[i][2] = priority[i][dir][j];
                        break;
                    }
                }
            }

            // 모든 상어 냄새 -1하기
            // 냄새 카운트를 줄여주는데, 카운트가 0이 되면 상어번호도 같이 지워줌
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(sea[i][j][1] != 0) {
                        sea[i][j][1]--;
                        if(sea[i][j][1] == 0) sea[i][j][0] = 0;
                    }
                }
            }

            // 번호 큰 상어부터 냄새를 남기는데, 방금 내뿜은 냄새가 남아있다면
            // 그 상어 퇴출시키고 자기 번호로 교체
            for(int i=M; i>=1; i--) {
                if(list.contains(i)) continue;

                int x = shark[i][0];
                int y = shark[i][1];

                if(sea[x][y][0] != 0 && sea[x][y][1] == k) {
                    list.add(sea[x][y][0]);
                    sea[x][y][0] = i;
                } else {
                    sea[x][y][0] = i;
                    sea[x][y][1] = k;
                }
            }

        }

        if(flag) {
            System.out.println(time);
        } else {
            System.out.println(-1);
        }

    }
}