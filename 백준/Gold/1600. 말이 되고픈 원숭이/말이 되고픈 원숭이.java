import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 점프 가능 횟수 입력
        int k = Integer.parseInt(br.readLine());

        // map의 가로와 세로 길이 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        
        // 1, 1 크기이면 0 출력 후 종료
        if(W == 1 && H == 1) {
            System.out.println(0);
            return;
        }

        // map 생성
        int[][][] map = new int[H][W][k+1];

        // BFS를 위한 queue 생성
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 일반 이동
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 말처럼 이동
        int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] hdy = {1, -1, 2, -2, 2, -2, 1, -1};

        // 입력은 2차원 배열의 값이 주어지므로 3차원 배열에도 동일하게 입력함
        // 이 때, 이동도 이 map 안에서 실행하기 위해 장애물을 -1로 표시
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                // num을 입력받고 장애물을 -1로 설정
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    for (int l = 0; l < k+1; l++) {
                        map[i][j][l] = -1;
                    }
                }
            }
        }

        // 도착지에 도착했는지 여부를 표시할 flag
        boolean flag = false;
        int answer = 0;

        // BFS 진행 (시작점은 항상 0, 0, 점프횟수 k)
        queue.offer(0);
        queue.offer(0);
        queue.offer(k);
        
        loop:
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int j = queue.poll();

            // 일반이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                // nx, ny 지점에 도착하면 종료
                if(nx == H-1 && ny == W-1)  {
                    answer = map[x][y][j] + 1;
                    flag = true;
                    break loop;
                }

                if (map[nx][ny][j] == 0) {
                    map[nx][ny][j] = map[x][y][j] + 1;
                    queue.offer(nx);
                    queue.offer(ny);
                    queue.offer(j);
                }
            }

            if(j != 0) {
                // 말처럼 이동
                for (int i = 0; i < 8; i++) {
                    int nx = x + hdx[i];
                    int ny = y + hdy[i];
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    
                    // nx, ny 지점에 도착하면 종료
                    if(nx == H-1 && ny == W-1)  {
                        answer = map[x][y][j] + 1;
                        flag = true;
                        break loop;
                    }

                    if (map[nx][ny][j-1] == 0) {
                        map[nx][ny][j-1] = map[x][y][j] + 1;
                        queue.offer(nx);
                        queue.offer(ny);
                        queue.offer(j-1);
                    }
                }
            }
        }

        if (flag) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}