import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 노드의 개수
        int N = Integer.parseInt(br.readLine());

        // 간선을 담은 field list 선언
        ArrayList<int[]> field = new ArrayList<>();

        // field 밖의 새로운 노드 N을 임의로 설정하고 우물파는 비용을 저장
        for (int i = 0; i < N; i++) {
            int W = Integer.parseInt(br.readLine());
            field.add(new int[] {i, N, W});
        }

        // map처럼 입력받지 않고 그냥 간선을 모두 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i == j) continue;
                field.add(new int[] {i, j, cost});
            }
        }

        // 비용 순으로 정렬
        Collections.sort(field, (o1, o2) -> o1[2] - o2[2]);

        int[] parent = new int[N+1];

        Arrays.fill(parent, -1);

        // 연결된 노드의 수를 셀 cnt와 정답을 저장할 answer 선언
        int cnt = 0;
        int answer = 0;
        
        for (int i = 0; i < field.size(); i++) {
            int[] arr = field.get(i);
            int a = arr[0];
            int b = arr[1];
            // 같은 그룹이면 지나감
            if(!diffParent(parent, a, b)) continue;

            // 다른 그룹이었을 경우 cnt 올려주고 비용도 더해줌
            answer += field.get(i)[2];
            cnt++;
            if (cnt == N+1) break;
        }

        System.out.println(answer);
    }

    // 다른 그룹이었는지 여부를 반환하고 같은 그룹으로 묶어주는 method
    private static boolean diffParent(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a == b) return false;
        if (parent[a] == parent[b]) parent[a]--;
        if (parent[a] < parent[b]) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    // 부모 찾는 method
    private static int find(int[] parent, int a) {
        if (parent[a] < 0) return a;

        return find(parent, parent[a]);
    }

}