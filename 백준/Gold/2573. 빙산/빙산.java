import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 맵을 입력받고, 중복을 방지하기 위해 체크 배열을 만든다.
        int[][] map = new int[N][M];
        boolean[][] check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 사방탐색을 위한 델타배열
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 끝까지 완료했는지 여부를 체크할 flooded 함수
        boolean flooded = false;
        int cycle = 0;
        loop:
        while(!flooded) {
            int num = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {

                    // 땅 중에서 탐색하지 않은 값이 있으면 BFS를 진행한다.
                    if(map[i][j] != 0 && !check[i][j]) {
                        num++;
                        // 대륙이 2개 이상이면 종료
                        if(num >= 2) break loop;
                        queue.offer(i);
                        queue.offer(j);
                        while (!queue.isEmpty()) {
                            int x = queue.poll();
                            int y = queue.poll();
                            check[x][y] = true;
                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                // 대륙에서 바다가 되었을 수도 있으므로 지나온 곳은 탐색하지 않는다.
                                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !check[nx][ny]) {
                                    // 4방 탐색을 하여 물을 만나면 값을 빼주고, 땅을 만나면 queue에 넣는다.
                                    if(map[nx][ny] == 0) {
                                        // map[x][y]가 0보다 클 때만 값을 빼준다.
                                        if(map[x][y] > 0) map[x][y]--;
                                    } else {
                                        // 0이 아니면 체크하고 queue에 넣어준다
                                        check[nx][ny] = true;
                                        queue.offer(nx);
                                        queue.offer(ny);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 사이클이 끝나면 check를 false로 채워줌
            for (int i = 0; i < N; i++) {
                Arrays.fill(check[i], false);
            }

            // 끝까지 진행할 때까지 대륙이 나눠지지 않았다면 flooded를 true로 바꿔서 종료한다.
            // 그렇지 않다면 다음 cycle을 진행한다.
            if(num == 0) flooded = true;
            cycle++;
        }

        if(flooded) {
            System.out.println(0);
        } else {
            System.out.println(cycle);
        }
    }
}
