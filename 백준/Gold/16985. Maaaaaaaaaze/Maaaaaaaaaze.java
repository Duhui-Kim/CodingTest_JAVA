import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;

    // delta 탐색을 위한 배열 선언
    private static int[] dx = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, -1, 1};
    private static boolean[] check = new boolean[5];

    // 정답 저장용
    private static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 5;

        // 1번 idx는 층, 2, 3번 idx는 평면
        int[][][] map = new int[N][N][N];

        // 값을 입력받기
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 층의 순서 정하는 용도 배열
        int[] arr = new int[N];
        backTracking(map, arr, 0);

        // minTime이 초기값과 같으면 답이 없는 것이므로 -1 출력 / 그렇지 않을 경우 최솟값 출력
        if(minTime == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }
    }

    // 각 판을 어느 위치에 놓을지 정하는 method (중복 없이 선택)
    private static void backTracking(int[][][] map, int[] arr, int k) {
        // 12가 최소의 이동이므로 12보다 빠를 수 없음
        if(minTime == 12) return;

        // k == N일 때 해당 층수에 맞게 tmp에 넣어줌
        if(k == N) {
            int[][][] tmp = new int[N][N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int l = 0; l < N; l++) {
                        tmp[arr[i]][j][l] = map[i][j][l];
                    }
                }
            }
            // 각 층 회전 method 실행
            rotateMap(tmp);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                arr[k] = i;
                backTracking(map, arr, k+1);
                check[i] = false;
            }
        }

    }

    // 1~5층까지 90도로 회전시키는 method
    private static void rotateMap(int[][][] map) {
        // 12가 최소의 이동이므로 12보다 빠를 수 없음
        if(minTime == 12) return;

        // 0일 땐 그대로, 1, 2, 3일 땐 회전
        for (int f1 = 0; f1 < 4; f1++) {
            if(f1 != 0) map[0] = turn90(map[0]);
            // 시작점이 0이면 진행 불가
            if(map[0][0][0] == 0) continue;
            for (int f2 = 0; f2 < 4; f2++) {
                if(f2 != 0) map[1] = turn90(map[1]);
                for (int f3 = 0; f3 < 4; f3++) {
                    if(f3 != 0) map[2] = turn90(map[2]);
                    for (int f4 = 0; f4 < 4; f4++) {
                        if(f4 != 0) map[3] = turn90(map[3]);
                        for (int f5 = 0; f5 < 4; f5++) {
                            if(f5 != 0) map[4] = turn90(map[4]);
                            // 도착점이 0이면 진행 불가
                            if(map[N-1][N-1][N-1] == 0) continue;
                            BFS(map);
                        }
                    }
                }
            }
        }
    }

    // bfs 진행 method
    private static void BFS(int[][][] map) {
        // 12가 최소의 이동이므로 12보다 빠를 수 없음
        if(minTime == 12) return;

        // queue와 check 배열 생성
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] check = new boolean[N][N][N];

        int[] arr = new int[4];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 0;
        arr[3] = 0;
        queue.offer(arr);
        check[0][0][0] = true;

        // BFS 시작
        loop:
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            int z = queue.peek()[2];
            int t = queue.poll()[3];

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if(nx < 0 || ny < 0 || nz < 0 || nx >= N || ny >= N || nz >= N) continue;
                if(check[nz][nx][ny]) continue;

                if(nx == 4 && ny == 4 && nz == 4) {
                    minTime = Math.min(minTime, t+1);
                    break loop;
                }

                if(map[nz][nx][ny] == 1) {
                    check[nz][nx][ny] = true;
                    int[] arr2 = new int[4];
                    arr2[0] = nx;
                    arr2[1] = ny;
                    arr2[2] = nz;
                    arr2[3] = t+1;
                    queue.offer(arr2);
                }
            }
        }
    }

    // 90도 돌리는 method
    public static int[][] turn90(int[][] map) {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[j][N-1-i];
            }
        }

        return tmp;
    }
}