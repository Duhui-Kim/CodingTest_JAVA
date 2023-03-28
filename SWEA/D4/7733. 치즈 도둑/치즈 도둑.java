import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());
        Queue<Integer> queue = new LinkedList<>();

        // 사방탐색용 델타
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        // 테스트케이스만큼 반복 진행
        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());

            // 치즈의 최대크기와 정답 선언
            int maxNum = 0;
            int answer = 0;

            // 맵 생성
            int[][] map = new int[N][N];

            // map을 입력받으며 가장 큰 치즈 입력받기
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > maxNum) {
                        maxNum = map[i][j];
                        answer = 1;
                    }
                }
            }

            // maxNum만큼 반복
            for (int i = 1; i < maxNum; i++) {
                // 덩어리를 세어 줄 cnt
                int cnt = 0;

                // 방문체크 배열
                boolean[][] check = new boolean[N][N];

                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        // 체크되어 있으면 전에 방문했던 덩어리이므로 지나감
                        if(check[j][k]) continue;
                        // i번째 날보다 큰 치즈만 남아있으므로 진행
                        if(map[j][k] > i) {
                            cnt++;
                            queue.offer(j);
                            queue.offer(k);
                            check[j][k] = true;

                            // BFS 진행
                            while (!queue.isEmpty()) {
                                int x = queue.poll();
                                int y = queue.poll();
                                for (int l = 0; l < 4; l++) {
                                    int nx = x + dx[l];
                                    int ny = y + dy[l];

                                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                                    if (check[nx][ny]) continue;
                                    if (map[nx][ny] <= i) continue;

                                    check[nx][ny] = true;
                                    queue.offer(nx);
                                    queue.offer(ny);
                                }
                            }

                        }
                    }
                }
                // cnt가 answer보다 크거나 같으면 바꿔준다
                if (answer <= cnt) {
                    answer = cnt;
                }

            }

            sb.append(String.format("#%d %d\n", tc, answer));
            queue.clear();
        }
        System.out.println(sb);
    }
}