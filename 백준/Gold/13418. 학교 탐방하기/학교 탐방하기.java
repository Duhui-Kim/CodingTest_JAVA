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

        // 건물의 개수 N과 입력받을 간선의 개수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 연결된 학교를 저장할 인접리스트 배열
        ArrayList<int[]>[] road = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            road[i] = new ArrayList<int[]>();
        }

        // 재방문하지 않기 위한 check 배열
        boolean[] check = new boolean[N+1];

        // 간선 입력받기
        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            road[a].add(new int[]{b, c});
            road[b].add(new int[]{a, c});
        }

        // 최악 도로부터 찾기 위해 PriorityQueue를 내림차순으로 정렬
        PriorityQueue<int[]> worst = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        // 임의의 정점을 하나 선택하고 방문체크, 간선 모두 queue에 넣기
        check[0] = true;
        for(int[] a : road[0]) {
            worst.offer(a);
        }

        // 최악경로 찾기 시작
        int cnt = 0;
        int worstPath = 0;
        while (!worst.isEmpty()) {
            int[] arr = worst.poll();

            // 이미 방문한 노드이면 지나감
            if(check[arr[0]]) continue;

            // 방문하지 않은 노드이면 방문표시 후 cnt와 피로도 증가
            check[arr[0]] = true;
            if(arr[1] == 0) worstPath++;
            cnt++;
            if (cnt == N) break;

            // 해당 노드와 연결된 간선 queue에 넣어줌
            for(int[] a : road[arr[0]]) {
                worst.offer(a);
            }

        }

        // 썼던 check 배열 초기화
        Arrays.fill(check, false);

        // 최적경로는 오름차순으로 정렬
        PriorityQueue<int[]> best = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        // 임의의 정점을 하나 선택하고 방문체크, 간선 모두 queue에 넣기
        check[0] = true;
        for(int[] a : road[0]) {
            best.offer(a);
        }

        // 최적경로 찾기 시작
        cnt = 0;
        int bestPath = 0;
        while (!best.isEmpty()) {
            int[] arr = best.poll();

            // 이미 방문한 노드이면 지나감
            if(check[arr[0]]) continue;

            // 방문하지 않은 노드이면 방문표시 후 cnt와 피로도 증가
            check[arr[0]] = true;
            if(arr[1] == 0) bestPath++;
            cnt++;
            if (cnt == N) break;

            // 해당 노드와 연결된 간선 queue에 넣어줌
            for(int[] a : road[arr[0]]) {
                best.offer(a);
            }
        }

        // worst에 저장된 값의 제곱과 best에 저장된 값의 제곱 차이 출력
        System.out.println(Long.valueOf(worstPath) * Long.valueOf(worstPath) - Long.valueOf(bestPath) * Long.valueOf(bestPath));
    }
}