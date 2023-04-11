import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] list = new ArrayList[N+1];
        boolean[] check = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (list[a] == null) list[a] = new ArrayList<>();
            if (list[b] == null) list[b] = new ArrayList<>();

            list[a].add(b);
            list[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        check[1] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (list[num] == null) continue;

            for(int a : list[num]) {
                if (check[a]) continue;

                check[a] = true;
                queue.offer(a);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}