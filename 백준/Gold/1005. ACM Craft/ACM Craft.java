import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Pair {
        int x, time;
        private Pair(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        loop:
        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 건물의 개수 N과 간선의 개수 K
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 간선을 담을 list와 indegree 담을 int 배열, 시간 저장할 time 배열 선언
            ArrayList<Integer>[] list = new ArrayList[N+1];
            int[] indegree = new int[N+1];
            int[] time = new int[N+1];

            // ArrayList 초기화
            for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

            // 소요시간 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            // 간선 입력받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                indegree[b]++;
            }
            // 승리를 위한 건물 번호
            int victory = Integer.parseInt(br.readLine());

            // 시간 짧은 순으로 빼내는 PriorityQueue
            PriorityQueue<Pair> queue = new PriorityQueue<Pair>((o1, o2) -> o1.time - o2.time);
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    queue.offer(new Pair(i, time[i]));
                }
            }

            // 시간을 세어줄 cnt
            int cnt = 0;

            // queue가 빌 때까지 진행
            while (!queue.isEmpty()) {
                // pair를 꺼내고 소요시간을 cnt에 저장한다.
                Pair pair = queue.poll();
                cnt = pair.time;

                // 승리건물이 완공되었으므로 시간 출력 후 종료
                if (pair.x == victory) {
                    sb.append(cnt).append("\n");
                    continue loop;
                }

                // 연결된 간선의 indegree 줄여줌
                for(int a : list[pair.x]) {
                    indegree[a]--;
                    
                    // 먼저 짓는 건물이 없어졌다면, 현재 시간 + 소요시간을 저장한 채로 queue에 넣음
                    if (indegree[a] == 0) {
                        queue.offer(new Pair(a, time[a] + cnt));
                    }
                }
            }
        }
        System.out.println(sb);
    }
}