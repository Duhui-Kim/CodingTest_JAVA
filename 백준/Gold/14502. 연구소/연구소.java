import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int max = Integer.MIN_VALUE;
    private static int N;
    private static int M;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map 배열 생성
        int[][] map = new int[N][M];

        // 2의 위치 담을 배열
        ArrayList<Integer> list = new ArrayList<>();

        // map을 입력받으며 미리 0의 개수 체크
        // 2의 위치도 미리 체크
        int cntZero = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if(num==0) cntZero++;
                else if (num == 2) {
                    list.add(i);
                    list.add(j);
                }

            }
        }

        backTracking(map, list, 0, 0, 0, cntZero);

        System.out.println(max);
    }

    private static void backTracking(int[][] map, ArrayList<Integer> list, int x, int y, int k, int cntZero) {
        if(k == 3) {
            boolean[][] check = new boolean[N][M];
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            // 2의 좌표 queue에 넣어주기
            for (int i = 0; i < list.size(); i++) {
                queue.offer(list.get(i));
            }
            // max보다 tmp가 작아지면 종료 (1이 세개 생겼으므로 -3부터 시작)
            int tmp = cntZero - 3;

            // BFS 진행
            while (!queue.isEmpty()) {
                int r = queue.poll();
                int c = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if(!check[nr][nc] && map[nr][nc] == 0) {
                        check[nr][nc] = true;
                        queue.offer(nr);
                        queue.offer(nc);

                        // 0이 잠식될 때마다 1씩 줄어들고, max보다 작아지면 이 값은 필요없어짐
                        tmp--;
                        if(tmp < max) return;
                    }
                }
            }
            // 살아남으면 tmp와 max 비교
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(i < x) continue;
            for (int j = 0; j < M; j++) {
                if(i == x && j < y) continue;
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    backTracking(map, list, i, j, k+1, cntZero);
                    map[i][j] = 0;
                }
            }
        }
    }
}
