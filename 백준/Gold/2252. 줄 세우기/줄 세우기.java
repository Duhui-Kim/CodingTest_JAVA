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

        // N과 M을 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N크기의 ArrayList 배열과 indegree int 배열을 만든다.
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        int[] indegree = new int[N+1];

        // M개 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // indegree값 체크
            indegree[b]++;
            // 아직 아무것도 없으면 새로운 리스트를 만들어서 넣어주고 값을 넣어주기
            if(graph[a] == null) {
                graph[a] = new ArrayList<>();
                graph[a].add(b);
            // 이미 들어있다면 b를 추가해주기
            } else {
                graph[a].add(b);
            }
        }

        // 정답을 저장할 StringBuilder와 위상정렬 체크에 사용할 queue 선언
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        
        // indegree가 0인 숫자를 모두 queue에 넣는다.
        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // queue가 빌 때까지 진행
        while (!queue.isEmpty()) {
            
            // queue에서 값을 꺼내서 해당 값을 저장한다.
            int seleted = queue.poll();
            sb.append(seleted).append(" ");
            
            if(graph[seleted] == null) continue;

            // queue에서 꺼낸 값과 연결된 노드를 방문하고, indegree를 1 줄여준다.
            // indegree가 0이라면 queue에 넣어준다.
            for (int num : graph[seleted]) {
                indegree[num]--;
                if(indegree[num] == 0) {
                    queue.offer(num);
                }
            }
        }
        System.out.println(sb);
    }
}