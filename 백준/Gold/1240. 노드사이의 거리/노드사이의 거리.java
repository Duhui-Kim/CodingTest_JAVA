import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드의 수 N과 거리를 알고 싶은 노드의 수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 간선과 거리를 담을 배열
        ArrayList<int[]>[] nodes = new ArrayList[N+1];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if(nodes[a] == null) nodes[a] = new ArrayList<>();
            if(nodes[b] == null) nodes[b] = new ArrayList<>();

            // 연결된 노드와 거리 입력
            nodes[a].add(new int[]{b, distance});
            nodes[b].add(new int[]{a, distance});
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            // 탐색을 위한 queue 생성
            Queue<int[]> queue = new LinkedList<>();
            // 재방문 안하기 위한 boolean 배열 생성
            boolean[] check = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            queue.offer(new int[]{start, end, 0});
            check[start] = true;

            while (!queue.isEmpty()) {
                int[] arr = queue.poll();

                // 시작과 끝이 같아지면 도착했으므로 종료
                if(arr[0] == arr[1]) {
                    sb.append(arr[2]).append("\n");
                    break;
                }

                for(int[] out : nodes[arr[0]]) {
                    if(check[out[0]]) continue;
                    check[out[0]] = true;

                    // 시작점 바꿔주고, 거리도 추가해줌
                    queue.offer(new int[]{out[0], arr[1], arr[2] + out[1]});
                }
            }
        }
        System.out.println(sb);
    }
}