import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    1. 친구비를 입력받는데, 이 때 친구비 - 친구번호를 짝지어서 친구비 순으로 오름차순 정렬해둔다.
        (PriorityQueue 활용)
    2. 친구를 입력받으며 Union 시킨다.
    3. queue에서 값을 꺼내서 이미 그 친구 무리에 지불했다면 pass
    4. 아닌 경우 해당 친구 check
     */

    static class Pair {
        int x;
        int cost;
        private Pair (int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 친구의 수 N과 간선의 수 M, 갖고있는 금액 k
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // queue 선언
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        // queue에 친구비와 친구 집어넣기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            queue.offer(new Pair(i, Integer.parseInt(st.nextToken())));
        }

        // 부모 배열 만들고 초기화
        int[] parent = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 간선 입력받으며 부모 일치화시키기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Union(parent, a, b);
        }

        // 이미 체크된 애한테는 친구비 안줘도 됌
        boolean[] check = new boolean[N+1];

        // 필요한 총 친구비
        int cost = 0;
        
        // queue가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            // 이미 체크되어있다면 같은 무리에게 지불했으므로 지나감
            if (check[find(parent, pair.x)]) continue;

            // 아닌 경우 체크하고 값을 지불함
            check[find(parent, pair.x)] = true;
            cost += pair.cost;

            // 값이 k보다 커지면 불가능
            if (cost > k) {
                System.out.println("Oh no");
                return;
            }
        }
        // 값을 출력
        System.out.println(cost);
    }

    private static void Union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a == b) return;

        if (parent[a] > parent[b]) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int[] parent, int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent, parent[a]);
    }
}