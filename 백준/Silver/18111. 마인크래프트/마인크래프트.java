import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int min = 256;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int land = 0;
        int minTime = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int time = 0;
            int block = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] < i) {
                        time = time + i - map[j][k];
                        block += i - map[j][k];
                    } else if (map[j][k] > i) {
                        time = time + (map[j][k] - i) * 2;
                        block += i - map[j][k];
                    }
                }
            }
            if (block > B) break;

            if (minTime >= time) {
                minTime = time;
                land = i;
            }
        }
        System.out.println(minTime + " " + land);
    }
}