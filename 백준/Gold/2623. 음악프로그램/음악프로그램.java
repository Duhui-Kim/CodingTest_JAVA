import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] indegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 몇 명의 가수가 주어지는지 받고 그만큼 반복 진행
            int input = Integer.parseInt(st.nextToken());
            int prev = 0;
            for (int j = 0; j < input; j++) {
                int next = Integer.parseInt(st.nextToken());
                if (prev != 0) {
                    // 인디그리 값 올려주기
                    indegree[next]++;

                    // 그래프에 리스트가 없으면 만들어서 넣어주기
                    if (graph[prev] == null) graph[prev] = new ArrayList<>();

                    // 그래프에 넣어주기
                    graph[prev].add(next);
                }
                // next를 prev에 넣어주고 다음 값 받으러가기
                prev = next;
            }
        }
        // 정답을 저장할 StringBuilder와 위상정렬 시행시 사용할 queue 선언
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();

        // 몇 개의 노드를 저장했는지 카운트
        int cnt = 0;

        // indegree가 0인 수 넣기
        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // queue가 빌 때까지 진행
        while (!queue.isEmpty()) {
            // queue에서 값을 꺼내고 스트링빌더에 저장
            int num = queue.poll();
            sb.append(num).append("\n");

            // 카운트도 올림
            cnt++;

            // 해당 노드에 아무것도 없으면 연결된 노드 없으므로 지나감
            if(graph[num] == null) continue;

            // 해당 그래프와 연결된 간선들을 체크
            for(int nxt : graph[num]) {
                // 꺼낸 노드의 indegree를 1 줄여준다.
                indegree[nxt]--;
                // indegree가 0이면 queue에 넣어준다.
                if(indegree[nxt] == 0) queue.offer(nxt);
            }
        }
        // cnt가 N과 같으면 N개의 노드를 모두 넣었으므로 sb 출력
        if(cnt == N) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }
}