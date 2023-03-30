import java.lang.annotation.Native;
import java.util.*;

public class Solution {
    private static int[] parent;
    private static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int testCase = Integer.parseInt(sc.nextLine());

        // 테스트케이스만큼 반복 진행
        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());

            // 입력받는 input 배열
            int[][] input = new int[N][N];

            // 각 지점에서의 최소비용 저장할 map 배열
            int[][] map = new int[N][N];

            // input값 입력받기
            for (int i = 0; i < N; i++) {
                char[] arr = sc.nextLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    input[i][j] = arr[j] - '0';
                }
            }

            // map은 큰 수로 초기화해둠
            for (int i = 0; i < N; i++) {
                Arrays.fill(map[i], INF);
            }

            // 시작값 초기화
            map[0][0] = 0;

            // queue를 선언하고 0, 0을 넣어둔다.
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[] {0, 0});

            // queue가 빌 때까지 반복 진행
            while (!queue.isEmpty()) {
                // x와 y를 꺼낸다.
                int[] arr = queue.poll();
                int x = arr[0];
                int y = arr[1];

                // 사방탐색을 진행
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // 범위 밖이면 지나감
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    // 해당 좌표의 값이 지금 좌표 + 해당좌표의 값보다 크면 바꿔주고, queue에 넣는다.
                    if(map[nx][ny] <= map[x][y] + input[nx][ny]) continue;

                    map[nx][ny] = map[x][y] + input[nx][ny];
                    queue.offer(new int[] {nx, ny});
                }
            }
            // 정답은 N-1, N-1 지점의 값
            int answer = map[N-1][N-1];
            // 출력
            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }
}