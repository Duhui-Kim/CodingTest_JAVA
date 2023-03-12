import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dr = {0, 0, 0, 0, -1, 1};
        int[] dc = {0, 0, -1, 1, 0, 0};
        int[] dh = {-1, 1, 0, 0, 0, 0};

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 0 0 0 이면 종료하라고 했음
            if(L == 0 && R == 0 && C == 0) break;

            // map 입력받기
            char[][][] map = new char[L][R][C];
            int[][][] check = new int[L][R][C];

            // BFS용 queue
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    map[i][j] = br.readLine().toCharArray();
                    for (int k = 0; k < C; k++) {
                        if(map[i][j][k] == 'S') {
                            // 행, 열, 높이 순으로 입력받음
                            queue.offer(j);
                            queue.offer(k);
                            queue.offer(i);
                        }
                    }
                }
                br.readLine();
            }

            // 탈출에 걸린 시간 저장 인자와 탈출여부 확인용 인자 선언
            int time = 0;
            boolean isEscape = false;

            // BFS 진행
            loop:
            while(!queue.isEmpty()) {
                int r = queue.poll();
                int c = queue.poll();
                int h = queue.poll();
                
                // 6방 탐색 진행
                for (int i = 0; i < 6; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    int nh = h + dh[i];
                    
                    // 범위 밖이면 지나가고, E이면 탈출
                    if(nr < 0 || nc < 0 || nh < 0 || nr >= R || nc >= C || nh >= L) continue;
                    if(map[nh][nr][nc] == 'E') {
                        isEscape = true;
                        time = check[h][r][c] + 1;
                        break loop;
                    }
                    // check가 0이고 map이 .일 경우 queue에 넣고 check도 업데이트
                    if(check[nh][nr][nc] == 0 && map[nh][nr][nc] == '.') {
                        queue.offer(nr);
                        queue.offer(nc);
                        queue.offer(nh);
                        check[nh][nr][nc] = check[h][r][c] + 1;
                    }
                }
            }

            if(isEscape) {
                sb.append(String.format("Escaped in %d minute(s).\n", time));
            } else {
                sb.append("Trapped!\n");
            }
        }
        System.out.println(sb.toString());
    }
}
