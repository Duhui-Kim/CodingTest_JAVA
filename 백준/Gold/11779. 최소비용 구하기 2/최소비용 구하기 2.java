import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시의 개수 N과 버스의 개수 M
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 간선 입력해둘 인접리스트
        ArrayList<Pair>[] map = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        StringTokenizer st;

        // 간선 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[a].add(new Pair(a, b, cost));
        }

        // 다익스트라 배열과 어디서 왔는지 저장할 배열
        long[] dijkstra = new long[N+1];
        int[] from = new int[N+1];

        // 초기값 설정
        Arrays.fill(dijkstra, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // queue 선언 후 시작 값 넣기
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dijkstra[start] = 0;
        for(Pair p : map[start]) {
            queue.offer(p);
        }

        // 다익스트라 진행
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            if (dijkstra[pair.b] > dijkstra[pair.a] + pair.cost) {
                dijkstra[pair.b] = dijkstra[pair.a] + pair.cost;
                from[pair.b] = pair.a;

                for(Pair p : map[pair.b]) {
                    queue.offer(p);
                }
            }
        }
        // 최소비용 저장
        StringBuilder sb = new StringBuilder();
        sb.append(dijkstra[end]).append("\n");

        // 경로를 스택에 저장
        Stack<Integer> stack = new Stack<>();
        stack.push(end);
        while (from[end] != start) {
            stack.push(from[end]);
            end = from[end];
        }
        stack.push(start);

        // 도시 수 저장
        sb.append(stack.size()).append("\n");

        // 스택에서 하나씩 빼서 경로 저장
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    static class Pair {
        int a;
        int b;
        int cost;

        private Pair(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}