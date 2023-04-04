import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][] check;
    private static int answer;
    private static int N;
    private static int K;

    // 좌표와 이동한 길이, 현재 땅의 높이, 땅 팔 수 있는지 여부를 저장
    static class Pair {
        int x, y, road, height;
        boolean dig;
        private Pair(int x, int y, int road, int height, boolean dig) {
            this.x = x;
            this.y = y;
            this.road = road;
            this.height = height;
            this.dig = dig;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        // testCase만큼 반복 진행
        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());

            // 지도의 크기 N과 팔 수 있는 땅의 크기 K
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            check = new boolean[N][N];

            int maxHeight = 0;

            // 지도 입력받으면서 최대 높이 찾기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;
                    if (num > maxHeight) maxHeight = num;
                }
            }
            // 정답 초기화해두기
            answer = 0;

            // 최대높이를 찾으면 BFS 진행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == maxHeight) {
                        check[i][j] = true;
                        DFS(new Pair(i, j, 1, maxHeight, true));
                        check[i][j] = false;
                    }
                }
            }

            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }

    private static void DFS(Pair pair) {
        answer = Math.max(answer, pair.road);

        for (int i = 0; i < 4; i++) {
            int nx = pair.x + dx[i];
            int ny = pair.y + dy[i];

            // 범위 밖이면 지나감
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            // 체크 되어있으면 지나감
            if (check[nx][ny]) continue;

            if (map[nx][ny] < pair.height) {
                check[nx][ny] = true;
                DFS(new Pair(nx, ny, pair.road + 1, map[nx][ny], pair.dig));
                check[nx][ny] = false;
            } else {
                // 땅을 팔 수 없으면 지나감
                if (!pair.dig) continue;

                // 땅을 팔 수 있지만, K 이상 차이가 나면 지나감
                if (map[nx][ny] - K >= pair.height) continue;
                    
                // 둘 다 아닐 경우 체크해주고, nx, ny의 높이를 현재 높이 - 1로 해주고 넘어감
                check[nx][ny] = true;
                DFS(new Pair(nx, ny, pair.road + 1, pair.height - 1, false));
                check[nx][ny] = false;
            }
        }

    }

}