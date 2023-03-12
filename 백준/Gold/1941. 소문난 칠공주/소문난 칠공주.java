import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int cnt = 0;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // map의 크기는 항상 5 * 5이므로 5를 저장
        int N = 5;

        // 학생정보를 저장할 배열과 어떤 학생을 선택할지를 체크할 배열 생성
        char[][] classRoom = new char[N][N];
        boolean[][] check = new boolean[N][N];

        // BFS 진행할 queue도 생성
        Queue<int[]> queue = new LinkedList<>();

        // classRoom 배열에 입력받기
        for (int i = 0; i < N; i++) {
            classRoom[i] = br.readLine().toCharArray();
        }

        // 백트래킹 진행
        backTracking(classRoom, check, N, -1, -1, 0, 0, queue);

        // 경우의 수 반환
        System.out.println(cnt);
    }

    private static void backTracking(char[][] classRoom, boolean[][] check, int N, int x, int y, int k, int S, Queue<int[]> queue) {
        // 7명이 선택되면 이다솜파(S)가 4개인지 체크한다.
        // 4명보다 작으면 return
        if(k == 7) {
            if(S < 4) return;

            boolean[][] bfs = new boolean[N][N];

            // 4명보다 클 경우 BFS를 진행해서 붙어있는지 체크한다.
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(check[i][j]) {
                        int[] arr = new int[2];
                        arr[0] = i;
                        arr[1] = j;
                        queue.offer(arr);
                        bfs[i][j] = true;
                        count++;
                        while (!queue.isEmpty()) {
                            int nx = queue.peek()[0];
                            int ny = queue.poll()[1];
                            for (int l = 0; l < 4; l++) {
                                int cx = nx + dx[l];
                                int cy = ny + dy[l];
                                if(cx < 0 || cy < 0 || cx >= N || cy >= N) continue;
                                if(check[cx][cy] && !bfs[cx][cy]) {
                                    bfs[cx][cy] = true;
                                    count++;
                                    arr = new int[2];
                                    arr[0] = cx;
                                    arr[1] = cy;
                                    queue.offer(arr);
                                }
                            }
                        }
                        // 7명이 붙어있다면 경우의 수 증가 후 return
                        if(count >= 7) {
                            cnt++;
                            return;
                        }
                        // 붙어있지 않다면 그냥 return
                        return;
                    }
                }
            }
            return;
        }

        // 반복문을 진행하는데, 중복 체크를 방지하기 위해서 이전에 넣었던 좌표값을 가져온다.
        // 이전에 넣었던 행보다 작은 행은 skip, 이전에 넣었던 행과 같을 경우 이전에 넣었던 열보다 작거나 같으면 skip
        for (int i = 0; i < N; i++) {
            if(i < x) continue;
            for (int j = 0; j < N; j++) {
                if (i == x && j <= y) continue;

                // i, j 좌표를 체크하고, 해당 좌표가 S이면 S count 증가 후 백트래킹
                // S가 아니면 S count 그대로 두고 백트래킹
                check[i][j] = true;
                if(classRoom[i][j] == 'S') {
                    backTracking(classRoom, check, N, i, j, k+1, S+1, queue);
                } else {
                    backTracking(classRoom, check, N, i, j, k+1, S, queue);
                }
                check[i][j] = false;
            }
        }
    }
}