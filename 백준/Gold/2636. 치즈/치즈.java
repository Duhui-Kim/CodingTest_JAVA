import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] cheeze = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheeze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();


        boolean flag = true;
        int cnt = -1;
        int chz;
        int answer = 0;

        while (flag) {
            chz = 0;
            cnt++;
            flag = false;
            boolean[][] check = new boolean[N][M];
            queue.offer(new int[] {0, 0});
            check[0][0] = true;

            while (!queue.isEmpty()) {
                int[] arr = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = arr[0] + dx[j];
                    int ny = arr[1] + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if (check[nx][ny]) continue;

                    check[nx][ny] = true;
                    if (cheeze[nx][ny] == 1) {
                        chz++;
                        cheeze[nx][ny] = 0;
                        flag = true;
                        continue;
                    }
                    queue.offer(new int[] {nx, ny});
                }
            }
            if (flag) answer = chz;
        }
        System.out.println(cnt);
        System.out.println(answer);
    }
}