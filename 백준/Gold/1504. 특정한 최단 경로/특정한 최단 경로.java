import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Pair>[] pairs;
    // 0번이 1->a / 1번이 1->b / 2번이 a->b / 3번이 a->N / 4번이 b->N
    private static int N;
    private static class Pair {
        int from, dist;
        private Pair(int from, int dist) {
            this.from = from;
            this.dist = dist;
        }
    }
    private static final int INF = 500000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 최종 정답에 필요한 값 저장할 배열
        int[] answer = new int[5];

        // 간선 입력받을 인접리스트
        pairs = new ArrayList[N+1];

        for(int i=0; i<=N; i++) {
            pairs[i] = new ArrayList<>();
        }

        // 간선 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pairs[a].add(new Pair(b, c));
            pairs[b].add(new Pair(a, c));
        }

        // 거쳐야하는 두 정점 v1과 v2
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1. 1부터 N까지 진행하며 a와 b까지의 거리 최솟값 구하기
        int[] arr = new int[N+1];
        Arrays.fill(arr, INF);
        arr[1] = 0;
        dijkstra(1, arr);

        // 1부터 v1까지의 최소거리, 1부터 v2까지의 최소거리
        answer[0] = arr[v1];
        answer[1] = arr[v2];

        // 2. v1와 v2 사이의 거리 구하기
        Arrays.fill(arr, INF);
        arr[v1] = 0;
        dijkstra(v1, arr);

        // v1과 v2의 최소거리, v1부터 N까지의 최소거리
        answer[2] = arr[v2];
        answer[3] = arr[N];

        // 3. v2부터 N까지의 거리 구하기
        Arrays.fill(arr, INF);
        arr[v2] = 0;
        dijkstra(v2, arr);

        // v2에서 N까지의 최소거리
        answer[4] = arr[N];

        long min = INF;

        // 4. 두 거리 중 최솟값이 정답
        if(answer[0] < INF && answer[2] < INF && answer[4] < INF) {
            min = answer[0] + answer[2] + answer[4];
        }
        if (answer[1] < INF && answer[2] < INF && answer[3] < INF) {
            min = Math.min(answer[1] + answer[2] + answer[3], min);
        }

        if(min >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void dijkstra(int start, int[] arr) {
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);

        queue.offer(new Pair(start, 0));

        // 최단거리 갱신하며 진행
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for(Pair next : pairs[p.from]) {
                if(arr[next.from] > arr[p.from] + next.dist) {
                    arr[next.from] = arr[p.from] + next.dist;
                    queue.offer(new Pair(next.from, arr[next.from]));
                }
            }
        }
    }
}