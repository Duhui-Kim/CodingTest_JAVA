import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // map의 크기는 12 * 6이므로 해당 크기만큼 배열 생성
        char[][] map = new char[12][6];


        // BFS용 delta
        int[] dx = {0, 0, 1, -1};
        int[] dy = {-1, 1, 0, 0};

        // BFS용 queue
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // map 입력받기
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = 0;

        while (true) {
            boolean explosion = false;
            boolean[][] check = new boolean[12][6];
            boolean[][] BFS = new boolean[12][6];

            // 반복문 돌며 BFS 진행
            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (!check[i][j] && map[i][j] != '.') {
                        check[i][j] = true;
                        int color = 1;
                        queue.offer(i);
                        queue.offer(j);
                        while (!queue.isEmpty()) {
                            int x = queue.poll();
                            int y = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;

                                if(!check[nx][ny] && map[nx][ny] == map[i][j]) {
                                    check[nx][ny] = true;
                                    queue.offer(nx);
                                    queue.offer(ny);
                                    color++;
                                }
                            }
                        }

                        if(color >= 4) {
                            explosion = true;

                            BFS[i][j] = true;
                            queue.offer(i);
                            queue.offer(j);
                            while (!queue.isEmpty()) {
                                int x = queue.poll();
                                int y = queue.poll();
                                for (int k = 0; k < 4; k++) {
                                    int nx = x + dx[k];
                                    int ny = y + dy[k];
                                    if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;

                                    if(!BFS[nx][ny] && map[nx][ny] == map[i][j]) {
                                        BFS[nx][ny] = true;
                                        queue.offer(nx);
                                        queue.offer(ny);
                                        map[nx][ny] = '.';
                                    }
                                }
                            }
                            map[i][j] = '.';
                        }
                    }
                }
            }
            if (explosion) {
                sortMap(map);
                cnt++;
            } else {
                break;
            }
        }
        System.out.println(cnt);
    }

    private static void sortMap(char[][] map) {

        // 배열의 각 열마다 아래에서 위로 올라오면서
        // 문자가 있으면 맨 아래부터 차례로 넣어줌.
        for (int i = 0; i < 6; i++) {
            int idx = 11;
            for (int j = 11; j >= 0; j--) {
                if(map[j][i] != '.') {
                    map[idx--][i] = map[j][i];
                }
            }
            // 문자가 다 입력되었으면 위쪽은 다 .이 입력된다.
            while(idx >= 0) {
                map[idx--][i] = '.';
            }
        }
        
        
    }
}