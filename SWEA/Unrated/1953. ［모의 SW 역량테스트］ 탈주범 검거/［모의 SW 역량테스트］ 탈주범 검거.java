import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    // 파이프에 따라 사방탐색 개수를 저장해둠
    static int[] dir = {0, 4, 2, 2, 2, 2, 2, 2};

    // 각 파이프별 이동할 수 있는 방향 표시
    static int[][] dx = {{}, {-1, 1, 0, 0}, {-1, 1}, {0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] dy = {{}, {0, 0, -1, 1}, {0, 0}, {-1, 1}, {0, 1}, {0, 1}, {-1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());

            // 맵의 크기 N * M
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 시작점 R, C
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 이동거리 L
            int L = Integer.parseInt(st.nextToken());

            // 맵 만들기
            int[][] map = new int[N][M];

            // 체크 배열 만들기
            boolean[][] check = new boolean[N][M];

            // 맵 입력받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // BFS 진행을 위한 queue 선언
            Queue<int[]> queue = new LinkedList<>();

            // queue에 초기값 넣고 시작하기
            queue.offer(new int[] {R, C});
            check[R][C] = true;

            // 이동거리 측정용
            int cycle = 0;

            // 있을 수 있는 위치 저장용 (정답 저장)
            int cnt = 0;

            // queue가 비어있거나 이동거리가 L이 되었으면 종료
            while (!queue.isEmpty() && cycle < L) {

                // 현재 queue의 size만큼만 진행하면 1 사이클 진행
                int tmp = queue.size();

                for (int kk = 0; kk < tmp; kk++) {
                    int[] arr = queue.poll();
                    int x = arr[0];
                    int y = arr[1];
                    int num = map[x][y];
                    cnt++;

                    // dir[]에는 방향이 몇개인지 저장돼있음
                    for (int i = 0; i < dir[num]; i++) {
                        int nx = x + dx[num][i];
                        int ny = y + dy[num][i];

                        // 범위 밖이거나
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                        // 이미 지나갔거나
                        if (check[nx][ny]) continue;

                        // 파이프의 방향이 안맞거나
                        if (!link(map, x, y, nx, ny)) continue;

                        check[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
                cycle++;
            }
            sb.append(String.format("#%d %d\n", tc, cnt));
        }
        System.out.println(sb);
    }

    /**
     * 기존 좌표인 x, y와 이동할 좌표인 nx, ny를 받아서
     * 이동할 지점의 파이프가 연결될 수 있는 파이프인지 확인하는 method
     * @param map
     * @param x
     * @param y
     * @param nx
     * @param ny
     * @return
     */
    private static boolean link(int[][] map, int x, int y, int nx, int ny) {
        if(x == nx) {
            // 왼쪽으로 이동 시
            if(y > ny) {
                switch (map[nx][ny]) {
                    case 1: case 3: case 4: case 5:
                        return true;
                    default:
                        return false;
                }
            }
            // 오른쪽으로 이동 시
            else {
                switch (map[nx][ny]) {
                    case 1: case 3: case 6: case 7:
                        return true;
                    default:
                        return false;
                }
            }
        } else {
            // 위로 이동 시
            if (x > nx) {
                switch (map[nx][ny]) {
                    case 1: case 2: case 5: case 6:
                        return true;
                    default:
                        return false;
                }
            }
            // 아래로 이동 시
            else {
                switch (map[nx][ny]) {
                    case 1: case 2: case 4: case 7:
                        return true;
                    default:
                        return false;
                }
            }
        }

    }
}