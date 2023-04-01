import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static ArrayList<Integer> elecric;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수 N, 간선의 개수 M, 발전소의 개수 K
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 발전소 담을 리스트
        elecric = new ArrayList<>();

        // 발전소 담기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            elecric.add(Integer.parseInt(st.nextToken()));
        }

        // 부모 배열을 만들고 초기화
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // distance를 기준으로 정렬할 queue 선언
        PriorityQueue<Edge> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.cost > o2.cost) return 1;
            else return -1;
        }));

        // 입력받으면서 바로 queue에 넣어준다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            queue.offer(new Edge(a, b, c));
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
            if (cnt == N-K) break;
        }
        System.out.println(answer);
    }

    private static boolean diffGroup(int x, int y) {
        x = find(x);
        y = find(y);

        // 발전소에 포함되어있는지 확인
        boolean containX = elecric.contains(x);
        boolean containY = elecric.contains(y);

        if (x == y) return false;
        if (containX && containY) return false;

        if(containX) parent[y] = x;
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