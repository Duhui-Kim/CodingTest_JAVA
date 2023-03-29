import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 부모 정보를 담을 배열을 선언하고 부모를 자기 자신으로 담아준다.
        int[] parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        // Edge의 거리를 기준으로 정렬
        PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.d > o2.d) return 1;
            else return -1;
        });

        // 별의 좌표를 담을 배열
        double[][] stars = new double[2][N];

        // 별의 좌표 저장하기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[0][i] = Double.parseDouble(st.nextToken());
            stars[1][i] = Double.parseDouble(st.nextToken());
        }

        // 각 별의 거리를 구하고 간선을 만든다.
        // PriorityQueue에 넣는다.
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                double dx = Math.pow(stars[0][i] - stars[0][j] ,2);
                double dy = Math.pow(stars[1][i] - stars[1][j] ,2);
                double distance = Math.sqrt(dx + dy);

                queue.offer(new Edge(i, j, distance));
            }
        }

        int cnt = 0;
        double answer = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();

            if(!diffGroup(parent, edge.x, edge.y)) continue;

            cnt++;
            answer += edge.d;
            if (cnt == N-1) break;
        }
        System.out.printf("%.2f", answer);

    }

    private static boolean diffGroup(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        // 이미 같은 그룹이면 false 반환
        if (x == y) return false;

        // 한 쪽으로 부모 맞추기
        if (parent[x] < parent[y]) parent[y] = x;
        else parent[x] = y;

        return true;
    }

    // 부모 찾는 method 압축도 시켜주기
    private static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent, parent[x]);
    }

    // 간선 정보를 담을 객체 생성
    static class Edge {
        int x;
        int y;
        double d;
        public Edge(int x, int y, double d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}