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
        StringBuilder sb = new StringBuilder();
        int testCase = 0;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            testCase++;

            // 0, 0이 나오면 종료
            if(N == M && N == 0) break;

            // 입력 트리를 저장할 input 배열
            ArrayList<Integer>[] input = new ArrayList[N+1];

            // 재방문하지 않기 위한 boolean 배열
            boolean[] check = new boolean[N+1];

            // 부모 정보를 저장할 int 배열
            int[] parent = new int[N+1];

            // 트리 입력받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(input[a] == null) {
                    ArrayList<Integer> A = new ArrayList<>();
                    A.add(b);
                    input[a] = A;
                } else {
                    input[a].add(b);
                }
                if(input[b] == null) {
                    ArrayList<Integer> B = new ArrayList<>();
                    B.add(a);
                    input[b] = B;
                } else {
                    input[b].add(a);
                }
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if(check[i]) continue;

                boolean isTree = true;
                check[i] = true;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);

                loop:
                while (!queue.isEmpty()) {
                    int nxt = queue.poll();

                    // 연결된 간선이 없으면 지나간다
                    if(input[nxt] == null) continue;

                    // 해당 노드에 연결된 간선에 대해 반복
                    for(int cur : input[nxt]) {
                        if(parent[nxt] == cur) continue;
                        parent[cur] = nxt;

                        // 해당 노드가 체크되지 않았다면 체크하고 queue에 넣기
                        if(!check[cur]) {
                            check[cur] = true;
                            queue.offer(cur);
                        }
                        // 이미 체크된 노드라면, 트리가 아니므로 isTree false로 바꾸기
                        else {
                            isTree = false;
                            break loop;
                        }
                    }
                }
                if (isTree) cnt++;
            }
            if(cnt == 0) {
                sb.append(String.format("Case %d: No trees.\n", testCase));
            } else if (cnt == 1) {
                sb.append(String.format("Case %d: There is one tree.\n", testCase));
            } else {
                sb.append(String.format("Case %d: A forest of %d trees.\n", testCase, cnt));
            }

        }
        System.out.println(sb);
    }
}