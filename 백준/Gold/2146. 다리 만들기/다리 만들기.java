import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // map 저장할 배열과 다리 놓을 배열
        int[][] map = new int[N][N];
        int[][] sea = new int[N][N];

        // 전체 땅 방문표시할 배열과 현재 땅 방문표시 배열
        boolean[][] allCheck = new boolean[N][N];
        boolean[][] curCheck = new boolean[N][N];

        // 땅 저장할 Queue와 해안 저장할 Queue
        ArrayDeque<Integer> land = new ArrayDeque<>();
        ArrayDeque<Integer> coast = new ArrayDeque<>();

        // 사방탐색을 위한 delta 배열
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // map을 입력받음
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 결과 저장용
        int bridge = Integer.MAX_VALUE;

        // BFS 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 현재 땅이 방문하지 않은 땅이면 BFS 시작
                if(!allCheck[i][j] && map[i][j] == 1) {
                    land.offer(i);
                    land.offer(j);
                    allCheck[i][j] = true;
                    curCheck[i][j] = true;
                    while (!land.isEmpty()) {
                        int x = land.poll();
                        int y = land.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            // 범위 밖이거나 이미 방문한 곳이면 지나간다.
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if(curCheck[nx][ny]) continue;

                            // 땅이면 BFS 이어서 진행하고, 바다면 해당 좌표를 coast에 저장
                            if(map[nx][ny] != 0) {
                                land.offer(nx);
                                land.offer(ny);
                                allCheck[nx][ny] = true;
                                curCheck[nx][ny] = true;

                            // 해안가 중복 저장을 막기 위해 해안가를 2로 표시해둔다.
                            // 주변에 바다가 있다는 것은 해당 좌표가 해안가이므로 x, y를 저장
                            } else if (map[nx][ny] == 0 && map[x][y] != 2){
                                coast.offer(x);
                                coast.offer(y);
                                map[x][y] = 2;
                            }
                        }
                    }
                }

                // 해안가 좌표가 coast에 모두 저장되어있음.
                // 바닷길 탐험 시작. 처음 땅을 만나면 종료
                loop:
                while (!coast.isEmpty()) {
                    int x = coast.poll();
                    int y = coast.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        // 범위 밖이거나 이미 방문한 곳이면 지나간다.
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if(curCheck[nx][ny]) continue;

                        // 바다면 현재 값 + 1을 표시하며 진행
                        if(map[nx][ny] == 0) {
                            coast.offer(nx);
                            coast.offer(ny);
                            curCheck[nx][ny] = true;
                            sea[nx][ny] = sea[x][y] + 1;

                        // 땅이면 종료 후 현재 값 반환
                        } else {
                            bridge = Math.min(bridge, sea[x][y]);
                            break loop;
                        }
                    }
                }
                // 해안큐에 남아있는 값 없앰
                coast.clear();

                // 현재 땅에서 BFS가 종료되었으므로 curCheck는 초기화한다.
                for (int k = 0; k < N; k++) {
                    Arrays.fill(curCheck[k], false);
                }
            }
        }
        System.out.println(bridge);
    }
}