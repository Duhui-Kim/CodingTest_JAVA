import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 맵을 입력받을 sea, 시간체크용 time, 중복이동 방지용 check
        int[][] sea = new int[N][N];
        int[][] time = new int[N][N];
        boolean[][] check = new boolean[N][N];

        // BFS를 진행하기 위한 Queue
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> tmp = new LinkedList<>();

        // 물고기 계산용 배열
        int[] fish = new int[7];

        int[] start = new int[2];

        // 입력을 받으며 상어의 위치와 물고기의 수를 카운팅한다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                sea[i][j] = num;
                if(num == 9) {
                    start[0] = i;
                    start[1] = j;
                    queue.offer(start);
                    sea[i][j] = 0;
                } else if (num != 0){
                    fish[num]++;
                }
            }
        }

        // 상어 크기별 먹을 수 있는 물고기 정리
        for (int i = 2; i < 7; i++) {
            fish[i] = fish[i] + fish[i-1] - i;
        }

        // 크기 1 물고기의 크기가 0이면 먹을 수 있는 물고기가 없으므로 바로 종료
        if(fish[1] == 0) {
            bw.write("0");
            bw.close();
            return;
        }

        // 탐색을 위한 delta
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        // BFS 진행
        int shark = 2;
        int cnt = 0;
        int checkTime = 0;

        // 이번에 상어가 물고기를 잡아먹었는지 체크하는 인자
        boolean isFull = false;

        loop:
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                int x = queue.peek()[0];
                int y = queue.poll()[1];
                check[x][y] = true;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    // 방문했던 곳 재방문 X
                    if (!check[nx][ny]) {
                        // shark와 같은 크기거나 0이면 이동만 한다.
                        if (!isFull && sea[nx][ny] == shark || sea[nx][ny] == 0) {
                            time[nx][ny] = time[x][y] + 1;
                            check[nx][ny] = true;
                            int[] arr = new int[2];
                            arr[0] = nx;
                            arr[1] = ny;
                            tmp.offer(arr);

                        // 자기보다 작은 물고기를 먹으면 boolean을 true로 바꾼다.
                        } else if (sea[nx][ny] < shark) {
                            time[nx][ny] = time[x][y] + 1;
                            checkTime = time[nx][ny];
                            isFull = true;
                        }
                    }
                }
            }
            // 한 time마다 종료되면 isFull 여부에 따라 달라진다.
            if(!isFull) {
                while(!tmp.isEmpty()) {
                    queue.offer(tmp.poll());
                }
            // 가장 왼쪽 위에 있는 물고기를 먹고 queue에 넣고 check 초기화
            } else {
                start = findTarget(sea, time, shark, start[0], start[1], checkTime);
                sea[start[0]][start[1]] = 0;
                cnt++;
                fish[shark-1]--;
                if(cnt == shark) {
                    shark++;
                    cnt = 0;
                    if(shark > 7) shark = 7;
                }
                if(fish[shark-1] <= 0) {
                    break loop;
                }

                tmp.clear();
                queue.offer(start);
                for (int i = 0; i < N; i++) {
                    Arrays.fill(check[i], false);
                }
                isFull = false;
            }
        }
        
        // 종료되면 마지막 좌표의 time값을 반환한다.
        bw.write(time[start[0]][start[1]] + "");
        bw.close();
    }

    private static int[] findTarget(int[][] map, int[][] time, int shark, int x, int y, int checkTime){
        int[] target = new int[2];
        int min = Integer.MAX_VALUE;
        int nx = Integer.MAX_VALUE;
        int ny = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 같은 시간대의 애들 중 상어가 먹을 수 있는 애일 때
                // 그 중에서 가장 왼쪽 위에 있는 애를 반환함
                if(time[i][j] == checkTime && map[i][j] != 0 && map[i][j] < shark) {
                    int distance = Math.abs(x - i) + Math.abs(y - j);
                    if(min >= distance) {
                        min = distance;
                        if(nx > i) {
                            nx = i;
                            ny = j;
                        } else if (nx == i && ny > j) {
                            ny = j;
                        }
                    }
                }
            }
        }
        target[0] = nx;
        target[1] = ny;
        return target;
    }
}
