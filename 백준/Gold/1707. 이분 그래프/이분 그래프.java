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
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 두 집합을 구분하기 위한 check와 방문체크를 위한 visited
            boolean[] check = new boolean[V+1];
            boolean[] visited = new boolean[V+1];

            ArrayList<Integer>[] edge = new ArrayList[V+1];

            for (int i = 1; i <= V; i++) edge[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                edge[a].add(b);
                edge[b].add(a);
            }

            Queue<Integer> queue = new LinkedList<>();

            visited[1] = true;
            queue.offer(1);
            int cnt = 1;

            // 이분그래프 여부를 따지기 위한 flag
            boolean flag = false;
            loop:
            while (!queue.isEmpty()) {
                while (!queue.isEmpty()) {
                    int cur = queue.poll();

                    for (int nxt : edge[cur]) {
                        // 방문했던 정점인데 체크값이 같다면 이분 불가능
                        if (visited[nxt] && check[nxt] == check[cur]) {
                            flag = true;
                            break loop;
                        }
                        // 방문하지 않은 정점이면 방문체크 + 현재 값과 반대값 넣기
                        // Queue에 넣어주기
                        else if (!visited[nxt]) {
                            visited[nxt] = true;
                            check[nxt] = !check[cur];
                            queue.offer(nxt);
                        }
                    }
                }
                // 방문하지 않은 정점이 있다면 queue에 넣어주기
                for (;cnt <= V; cnt++) {
                    if (!visited[cnt]) {
                        visited[cnt] = true;
                        queue.offer(cnt);
                        continue loop;
                    }
                }
            }

            // flag에 따라서 YES NO 입력
            if (!flag) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}
