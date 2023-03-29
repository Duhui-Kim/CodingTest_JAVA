import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 부모 배열 만들고 초깃값 입력
        parent = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            parent[i] = i;
        }

        // 좌표 입력받을 배열 선언
        Double[][] stars = new Double[N+1][2];

        // 좌표 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        // 이미 입력된 애들 묶어주기
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if(diffGroup(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                cnt++;
            };
        }

        // distance를 기준으로 정렬할 queue 선언
        PriorityQueue<Edge> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.d > o2.d) return 1;
            else return -1;
        }));

        // 각 좌표별 거리를 구해주고 queue에 넣는다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                Double x = stars[i][0] - stars[j][0];
                Double y = stars[i][1] - stars[j][1];

                double distance = Math.sqrt(x * x + y * y);

                Edge edge = new Edge(i, j, distance);
                queue.offer(edge);
            }
        }

        // queue에서 거리가 가까운 애들부터 꺼내주면서 연결시켜준다.
        double answer = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            if(!diffGroup(edge.x, edge.y)) continue;

            cnt++;
            answer += edge.d;
            if (cnt == N-1) break;
        }

        System.out.printf("%.2f", Math.round(answer * 100) / 100.0);
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
        double d;
        public Edge (int x, int y, double d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}