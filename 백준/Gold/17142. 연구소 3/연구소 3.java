import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Pair {
        int r, c, time;
        private Pair(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    private static int[][] map;
    private static int N, M, blank, minTime;
    private static ArrayList<Pair> virus = new ArrayList<>();
    private static Queue<Pair> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵의 크기 N과 고를 수 있는 바이러스 M개
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map 입력받기
        blank = N * N;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    virus.add(new Pair(i, j, 1));
                } else if (num == 1) {
                    blank--;
                }
                map[i][j] = num;
            }
        }
        // 최소 시간
        minTime = Integer.MAX_VALUE/2;

        // 백트래킹
        backTracking(0, 0, 0, virus.size());

        // minTime이 초기값과 같다면 -1 출력 아닌 경우 minTime 출력
        if (minTime == Integer.MAX_VALUE/2) System.out.println(-1);
        else System.out.println(minTime);
    }

    private static void backTracking(int k, int select, int num, int size) {
        // M개를 고르면 BFS 진행
        if (select == M) {
            int[][] check = new int[N][N];
            for (int i = 0; i < size; i++) {
                if((num & 1 << i) > 0) {
                    queue.offer(virus.get(i));
                    check[virus.get(i).r][virus.get(i).c] = 1;
                }
            }
            minTime = Math.min(minTime, BFS(check));
            return;
        }
        // 바이러스 끝까지 가면 더 선택 or 미선택 불가
        if (k == size) return;

        // 바이러스 선택
        backTracking(k+1, select+1, num | 1 << k, size);

        // 미선택
        backTracking(k+1, select, num, size);
    }

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int BFS(int[][] check) {
        // 최대 시간 측정용
        int time = 1;

        // 바이러스 퍼진 칸 측정
        int cnt = 0;

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            cnt++;
            for (int j = 0; j < 4; j++) {
                int nx = pair.r + dx[j];
                int ny = pair.c + dy[j];

                // 범위 밖, 체크, 벽일 경우 지나감
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (check[nx][ny] > 0) continue;
                if (map[nx][ny] == 1) continue;
                queue.offer(new Pair(nx, ny, pair.time+1));

                // 빈칸일 경우 그냥 시간 표시하고 지나감
                if (map[nx][ny] == 0) check[nx][ny] = pair.time+1;
                // 비활성 바이러스일 경우 map에는 시간 표시 안함
                else if (map[nx][ny] == 2) check[nx][ny] = 1;

                // 최대 시간 저장
                time = Math.max(time, check[nx][ny]);
            }
        }

        if (cnt == blank) return time-1;
        else return Integer.MAX_VALUE/2;
    }
}