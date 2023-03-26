import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 문제의 수 N과 정보의 개수 M을 입력받는다
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 간선을 담을 리스트배열 problem과 문제번호를 작은 순서대로 꺼내기 위한 priority queue
        // indegree를 체크할 배열을 만든다.
        ArrayList<Integer>[] problem = new ArrayList[N+1];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int[] indegree = new int[N+1];

        // 입력을 받으며 indegree값과 간선을 저장한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            if(problem[a] == null) {
                problem[a] = new ArrayList<>();
                problem[a].add(b);
            } else {
                problem[a].add(b);
            }
        }

        // indegree가 0인 문제는 바로 풀 수 있는 문제이므로 저장한다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // queue가 빌 때까지 반복한다.
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int nxt = queue.poll();
            sb.append(nxt + " ");
            
            // 해당 문제에 연결된 간선이 없으면 다른 문제로
            if (problem[nxt] == null) continue;

            // 연결된 간선이 있으면 indegree값을 줄여주고,
            // indegree값이 0이면 queue에 추가한다.
            for (int a : problem[nxt]) {
                indegree[a]--;
                if(indegree[a] == 0) {
                    queue.offer(a);
                }
            }
        }
        // 정답 출력
        System.out.println(sb);
    }
}