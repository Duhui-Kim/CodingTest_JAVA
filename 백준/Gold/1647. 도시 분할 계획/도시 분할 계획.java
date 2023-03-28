import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 개수 N과 길의 개수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // road 배열 생성
        int[][] road = new int[M][3];

        // 연결된 두 노드와 가중치 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            road[i][0] = Integer.parseInt(st.nextToken());
            road[i][1] = Integer.parseInt(st.nextToken());
            road[i][2] = Integer.parseInt(st.nextToken());
        }

        // 가중치 순으로 정렬
        Arrays.sort(road, (o1, o2) -> o1[2] - o2[2]);

        // 부모 배열을 선언하고
        int[] parent = new int[N+1];
        Arrays.fill(parent, -1);

        // 연결된 간선의 수를 셀 cnt 선언
        int cnt = 0;

        int answer = 0;

        // 가중치 순으로 진행
        for (int i = 0; i < M; i++) {
            int a = road[i][0];
            int b = road[i][1];

            // 같은 부모를 갖고 있다면 연결시켜주고 지나감
            if(!diffParent(parent, a, b)) continue;

            // 다른 부모를 갖고 있었으면 정답에 답 더해주고 cnt 올림
            answer += road[i][2];
            cnt++;
            // 도시를 반으로 나눌 것이므로 N-2개의 간선이 생기면 종료
            if (cnt == N-2) break;
        }

        System.out.println(answer);
    }

    private static boolean diffParent(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a == b) return false;
        if (parent[a] == parent[b]) parent[a]--;
        if (parent[a] < parent[b]) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    private static int find(int[] parent, int a) {
        if(parent[a] < 0) return a;

        return find(parent, parent[a]);
    }

}