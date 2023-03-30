import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수 V와 간선의 개수 E
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 시작점 K
        int K = Integer.parseInt(br.readLine());

        // 간선을 담을 배열
        ArrayList<int[]>[] list = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<>();
        }

        // 간선 저장하기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new int[]{b, w});
        }

        // 최단거리를 담을 배열
        int[] dijkstra = new int[V+1];

        // 큰 값으로 초기화
        Arrays.fill(dijkstra, INF);

        // 시작점 초기화
        dijkstra[K] = 0;

        // PriorityQueue를 선언하고 시작점을 넣음 (거리 순으로 정렬)
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
        queue.offer(new int[] {K, dijkstra[K]});

        // queue가 빌 때까지 진행
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();

            // 이미 값이 표시되었으면 지나감
            if(dijkstra[arr[0]] != arr[1]) continue;
            for(int[] a : list[arr[0]]) {
                if (dijkstra[a[0]] <= dijkstra[arr[0]] + a[1]) continue;

                // 원래 있던 값보다 arr[0]를 거쳐 가는 값이 더 작을 경우
                // 해당 값을 갱신한다.
                dijkstra[a[0]] = dijkstra[arr[0]] + a[1];
                queue.offer(new int[]{a[0], dijkstra[a[0]]});
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dijkstra[i] == INF) sb.append("INF").append("\n");
            else sb.append(dijkstra[i]).append("\n");
        }
        System.out.println(sb);
    }
}