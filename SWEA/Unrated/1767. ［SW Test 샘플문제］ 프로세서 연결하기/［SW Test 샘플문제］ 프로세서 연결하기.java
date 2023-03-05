import java.util.Scanner;

public class Solution {

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int maxCore;
    private static int minLine;
    private static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 테스트케이스를 입력받고 진행
        int testCase = sc.nextInt();

        // 테스트케이스만큼 반복 진행
        for (int t = 1; t <= testCase; t++) {

            // N을 입력받는다.
            N = sc.nextInt();

            // N * N 크기의 map을 생성하고, core 배열도 생성한다.
            int[][] map = new int[N][N];
            int[][] core = new int[13][3];

            // core 개수를 세고, 배열에 차례로 넣기 위해 idx 선언
            int idx = 0;
            int coreCnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 숫자를 입력받고 map에 입력한다.
                    int num = sc.nextInt();
                    map[i][j] = num;

                    // 가장자리가 아닌 core만 core배열에 넣고 cnt를 증가시킨다.
                    if(i != 0 && j != 0 && i != N-1 && j != N-1 && num == 1) {
                        core[idx][0] = i;
                        core[idx++][1] = j;
                        coreCnt++;
                    }
                }
            }

            // coreCnt가 0인 경우는 가장자리에만 core가 있거나 core가 아예 없는 것이므로 0 반환
            if(coreCnt == 0) {
                sb.append(String.format("#%d %d\n", t, 0));
                continue;
            }

            // 전선과 연결된 최대 코어 수와 라인의 최소 길이를 저장할 값
            maxCore = 0;
            minLine = 0;

            // 전체 경우의 수 탐색
            backTracking(map, core, 0, coreCnt);
            
            // 결과 저장
            sb.append(String.format("#%d %d", t, minLine));
            if(t != testCase) sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void backTracking(int[][] map, int[][] core, int k, int coreCnt) {
        // k가 코어의 수와 같아지면 재귀 종료
        if(k >= coreCnt) {

            // map과 동일한 크기의 tmp를 만든다.
            int[][] tmp = new int[N][N];

            // tmp에 map을 복제한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tmp[i][j] = map[i][j];
                }
            }

            // cntCore는 coreCnt와 동일하게 하고 연결 안 될 때마다 빼준다.
            // coreLine은 연결됐을 때 라인의 길이를 측정한다.
            int cntCore = coreCnt;
            int cntLine = 0;

            // 각 core에 대해 반복을 실행한다.
            for (int i = 0; i < coreCnt; i++) {

                // 이게 키 포인트였네!!!!!
                // cntCore가 maxCore보다 작아지면 더 진행할 필요가 없어지므로 return
                if(cntCore < maxCore) return;

                // x, y, dir을 받아온다.
                int x = core[i][0];
                int y = core[i][1];
                int dir = core[i][2];

                // core가 해당 방향으로 끝까지 도달하면 true, 막히면 false
                boolean isPossible = true;

                while (x + dx[dir] >= 0 && y + dy[dir] >= 0 && x + dx[dir] < N && y + dy[dir] < N) {
                    if(tmp[x + dx[dir]][y + dy[dir]] == 0) {
                        x += dx[dir];
                        y += dy[dir];
                    } else {
                        isPossible = false;
                        cntCore--;
                        break;
                    }
                }

                // true일 경우에만 전선을 연결한다.
                if(isPossible) {
                    int nx = core[i][0];
                    int ny = core[i][1];
                    while (nx + dx[dir] >= 0 && ny + dy[dir] >= 0 && nx + dx[dir] < N && ny + dy[dir] < N) {
                        tmp[nx + dx[dir]][ny + dy[dir]] = 2;
                        nx += dx[dir];
                        ny += dy[dir];
                        cntLine++;
                    }
                }
            }

            // maxCore와 cntCore를 비교해서 cntCore가 더 크면 line 값을 바꿔주고,
            // 같을 경우에는 line을 비교해서 더 작은 값을 넣는다.
            if(maxCore < cntCore) {
                maxCore = cntCore;
                minLine = cntLine;
            } else if (maxCore == cntCore) {
                if(minLine > cntLine) {
                    minLine = cntLine;
                }
            }
            return;
        }

        // 좌표값을 비교해서 경우의 수를 줄여주기 위한 boolean 변수
        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;

        // 각 코어를 순회하며 x좌표가 일치할 경우 y좌표를 비교해서 up 또는 down을 false로 바꾸고,
        // y좌표가 일치할 경우 x좌표를 비교해서 left 또는 right을 false로 바꿔줬다.
        for (int i = 0; i < coreCnt; i++) {
            if(i == k) continue;
            if(core[k][0] == core[i][0]) {
                if(core[k][1] > core[i][1]) {
                    left = false;
                } else {
                    right = false;
                }
            } else if(core[k][1] == core[i][1]) {
                if(core[k][0] > core[i][0]) {
                    up = false;
                } else {
                    down = false;
                }
            }
        }

        // 위에서 false인 경우는 넘어가고 아닌 경우에만 방향을 넣어줬다.
        for (int i = 0; i < 4; i++) {
            if(!up && i == 0) continue;
            if(!down && i == 1) continue;
            if(!left && i == 2) continue;
            if(!right && i == 3) continue;

            core[k][2] = i;
            backTracking(map, core, k + 1, coreCnt);
        }
    }
}