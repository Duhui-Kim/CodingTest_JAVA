import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        /*
            크루스칼 알고리즘

            1. 간선들을 비용 순으로 정렬
            2. 비용이 적은 간선부터 시작해서 두 노드가 연결되어있는지 확인
            3. 같은 부모이면 false 반환 / 같은 부모가 아니면 연결시키고 true 반환
            4. 위의 값이 true일 때 answer에 가중치를 더해주고 cnt도 증가
            5. cnt가 V-1이 되면 종료

         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 정답을 저장할 answer 선언
        long answer = 0;

        // 부모를 저장할 배열 선언하고 -1로 초기화
        int[] parent = new int[V+1];
        Arrays.fill(parent, -1);

        int[][] nodes = new int[E][3];

        // 연결된 노드와 가중치 입력받기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());
            nodes[i][2] = Integer.parseInt(st.nextToken());
        }
        // 가중치를 기준으로 정렬
        Arrays.sort(nodes, ((o1, o2) -> {
            return o1[2] - o2[2];
        }));

        // 간선의 개수 세어주기(V-1)이 되면 종료
        int cnt = 0;

        // 최소 비용의 간선부터 진행
        for (int i = 0; i < E; i++) {
            int a = nodes[i][0];
            int b = nodes[i][1];

            // 부모가 같으면 이미 연결되어있으므로 지나감
            if(!diffParent(parent, a, b)) continue;

            // 부모가 다르므로 정답에 가중치를 더해주고 cnt를 올림
            answer += Long.valueOf(nodes[i][2]);
            cnt++;
            
            // cnt가 V-1이 되면 종료(V개의 노드를 연결하는 최소 간선의 수는 V-1)
            if (cnt == V-1) break;
        }
        System.out.println(answer);
    }

    private static boolean diffParent(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        // 이미 같은 부모이면 false 반환
        if (a == b) return false;

        // 다른 부모이면 연결시켜주고 true 반환
        if (parent[a] == parent[b]) parent[a]--;
        if (parent[a] < parent[b]) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    // 부모 찾는 method
    private static int find(int[] parent, int cur) {
        if(parent[cur] < 0) return cur;

        return find(parent, parent[cur]);
    }

}