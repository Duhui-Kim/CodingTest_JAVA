import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    // 오른쪽아래, 오른쪽위, 왼쪽위, 왼쪽아래
    private static int[] dx = {1, -1, -1, 1};
    private static int[] dy = {1, 1, -1, -1};
    private static int[][] map;
    private static int N;
    private static boolean[] check;
    private static int maxNum;
    private static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(sc.nextLine());

            // map과 boolean 배열 선언
            map = new int[N][N];
            check = new boolean[101];
            maxNum = Integer.MIN_VALUE;

            // 맵 입력받기
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 시작점은 0 ~ N-2로 잡음 (왼쪽아래부터 반시계방향으로 회전할 것이므로 맨 왼쪽, 맨 아래쪽에서는 진행 불가능)
            for (int i = 0; i < N-1; i++) {
                for (int j = 0; j < N-1; j++) {
                    check[map[i][j]] = true;
                    findSquare(i, j, i, j, 0, 1);
                    check[map[i][j]] = false;
                }
            }
            // maxNum이 초기값과 같으면 불가능하므로 -1 출력, 그렇지 않으면 maxNum 출력
            if(maxNum == Integer.MIN_VALUE) {
                sb.append(String.format("#%d %d\n", tc, -1));
            } else {
                sb.append(String.format("#%d %d\n", tc, maxNum));
            }
        }
        System.out.println(sb);
    }

    private static void findSquare(int st, int ed, int x, int y, int idx, int cnt) {
        if(idx == 3) {
            // 마지막에는 왼쪽 아래로 내려가는 방향인데, 출발점과 같은 대각선상에 있지 않으면 도달할 수 없으므로 돌아가기
            if(st - x != y - ed) return;
        }

        // 해당 방향으로 이동
        int nx = x + dx[idx];
        int ny = y + dy[idx];

        // 범위 밖이면 돌아가기
        if(nx < 0 || ny < 0 || nx >= N || ny >= N) return;

        // 시작점에 도달하면 돌아가기
        if(nx == st && ny == ed) {
            maxNum = Math.max(maxNum, cnt);
            return;
        }

        // 이미 값이 체크되어있으면 돌아가기
        if(check[map[nx][ny]]) return;

        check[map[nx][ny]] = true;

        // 방향 전환 안하고 가기
        findSquare(st, ed, nx, ny, idx, cnt+1);

        // idx가 0, 1, 2일 때만 방향전환 가능
        if(idx < 3) {
            // 방향 전환하고 가기
            findSquare(st, ed, nx, ny, idx + 1, cnt + 1);
        }

        check[map[nx][ny]] = false;
    }
}