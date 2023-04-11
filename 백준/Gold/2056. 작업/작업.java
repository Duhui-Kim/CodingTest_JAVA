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

        int N = Integer.parseInt(br.readLine());

        // 간선 연결용 인접리스트배열
        ArrayList<Integer>[] list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) list[i] = new ArrayList<>();

        // 작업시간 저장용 배열와 indegree 저장용 배열
        int[] time = new int[N+1];
        int[] indegree = new int[N+1];

        // 숫자를 입력받으며 선행작업과 작업시간을 입력받는다.
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            indegree[i] = M;

            for (int j = 0; j < M; j++) {
                list[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        // 현재 시간
        int cnt = 0;

        // queue가 비었다면 종료
        while (!queue.isEmpty()) {
            // queue의 size만큼씩 진행하여 한 사이클씩 진행한다
            int size = queue.size();
            // 시간 증가
            cnt++;

            for (int i = 0; i < size; i++) {
                int num = queue.poll();

                // 작업시간이 현재시간에 도달하지 못했다면 다시 queue에 넣어주기
                if (time[num] != cnt) {
                    queue.offer(num);
                    continue;
                }

                // 작업이 완료되었으면, 해당 작업이 선행작업인 작업들의 indegree 줄여주기
                for(int a : list[num]) {
                    indegree[a]--;

                    // indegree가 0이 되었다면 선행작업이 없으므로 queue에 넣어주는데,
                    // time은 현재 시간 기준으로 흘러야하므로 현재 시간을 더해주기
                    if (indegree[a] == 0) {
                        time[a] += cnt;
                        queue.offer(a);
                    }
                }
            }
        }
        // 시간 출력
        System.out.println(cnt);
    }
}