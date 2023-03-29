import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 부모 배열을 만들고 초기화
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        // distance를 기준으로 정렬할 queue 선언
        PriorityQueue<Edge> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.cost > o2.cost) return 1;
            else return -1;
        }));

        // 입력받으면서 바로 queue에 넣어준다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                long num = Long.parseLong(st.nextToken());
                if (i == j) continue;

                queue.offer(new Edge(i, j, num));
            }
        }

        // 간선의 수를 세어줄 cnt와 정답 저장용 answer
        int cnt = 0;
        long answer = 0;

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            // 이미 같은 그룹이면 지나가고 다른 그룹이면 값을 더해준다.
            if (!diffGroup(edge.x, edge.y)) continue;
            cnt++;
            answer += edge.cost;

            // cnt가 N-1개가 되면 종료
            if (cnt == N-1) break;
        }
        System.out.println(answer);
    }

    private static boolean diffGroup(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if(parent[x] < parent[y]) parent[y] = x;
        else parent[x] = y;

        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 간선을 담을 객체
    static class Edge {
        int x;
        int y;
        long cost;
        public Edge (int x, int y, long cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}