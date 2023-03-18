import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // map 크기를 입력받고 해당 크기의 map 배열 생성
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        // 주어진 높이들에 대해서만 반복 진행하기 위해서 set을 선언
        HashSet<Integer> set = new HashSet<>();

        // map을 입력받을 때 높이도 중복되지 않게 입력받음
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                set.add(map[i][j]);
            }
        }
        // 비가 안왔을 경우 default == 1
        int max = 1;

        // BFS 진행용 queue
        Queue<Integer> queue = new LinkedList<>();

        // BFS 진행용 delta
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 각 높이별 땅덩어리 수 세기
        for (int height : set) {
            // 재방문 막기 위한 boolean 배열
            boolean[][] check = new boolean[N][N];

            // 몇 개의 땅으로 나눠있는지 체크하기 위한 cnt 선언
            int cnt = 0;
            
            // 전체 배열을 순회한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 방문하지 않았고 높이가 height보다 높은 땅을 만나면 queue에 넣고 cnt를 증가시킨다.
                    if(!check[i][j] && map[i][j] > height) {
                        cnt++;
                        check[i][j] = true;
                        queue.offer(i);
                        queue.offer(j);

                        // BFS 진행
                        while (!queue.isEmpty()) {
                            int x = queue.poll();
                            int y = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];

                                // nx, ny가 범위 안일 때,
                                // 체크되어있지 않고 height보다 크다면 BFS 진행
                                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                                if(check[nx][ny] || map[nx][ny] <= height) continue;

                                check[nx][ny] = true;
                                queue.offer(nx);
                                queue.offer(ny);
                            }
                        }
                    }
                }
            }
            max = Math.max(cnt, max);
        }
        System.out.println(max);
    }
}
