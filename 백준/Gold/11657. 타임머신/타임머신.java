import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Pair {
        int to;
        long time;
        private Pair(int to, long time) {
            this.to = to;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] city = new long[N+1];
        ArrayList<Pair>[] Edge = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) Edge[i] = new ArrayList<>();
        Arrays.fill(city, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            Edge[a].add(new Pair(b, t));
        }

        city[1] = 0;

        boolean check = false;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (city[j] == Integer.MAX_VALUE) continue;

                for(Pair p : Edge[j]) {
                    if (city[p.to] > city[j] + p.time) {
                        city[p.to] = city[j] + p.time;

                        // N일 때 업데이트가 된다면 사이클 존재
                        if (i == N) check = true;
                    }
                }
            }
        }
        // check되었다면 -1 출력
        if (check) {
            System.out.println(-1);
        }
        // 아닌 경우 경로 출력
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                // 업데이트가 안됐다면 불가능한 경로
                if (city[i] == Integer.MAX_VALUE) {
                    sb.append(-1).append("\n");
                    continue;
                }
                // 경로 저장
                sb.append(city[i]).append("\n");
            }
            System.out.println(sb);
        }
    }
}
