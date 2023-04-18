import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static boolean[][] friends;
    private static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        friends = new boolean[N+1][N+1];

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a<0 && b<0) break;

            friends[a][b] = true;
            friends[b][a] = true;
        }

        ArrayList<Integer> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int cnt = BFS(i);

            if (min > cnt) {
                min = cnt;
                list.clear();
                list.add(i);
            } else if (min == cnt) {
                list.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min + " " + list.size()).append("\n");
        for (int a : list) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    private static int BFS(int start) {
        boolean[] check = new boolean[N+1];
        check[start] = true;

        queue.offer(start);

        int time = -1;
        while (!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int num = queue.poll();

                for (int j = 1; j <= N; j++) {
                    if (check[j]) continue;
                    if (!friends[num][j]) continue;

                    check[j] = true;
                    queue.offer(j);
                }
            }
        }

        return time;
    }
}