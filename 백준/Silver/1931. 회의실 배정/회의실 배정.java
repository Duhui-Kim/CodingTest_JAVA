import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            else return o1[1] - o2[1];
        }));

        // 입력할 강의 N개를 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // 강의 시간을 입력한다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[2];
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            queue.add(arr);
        }

        // 시작시간과 종료시간 순으로 정렬

        int cnt = 0;

        while (!queue.isEmpty()) {
            int end = queue.poll()[1];
            cnt++;
            while(!queue.isEmpty()) {
                if(end > queue.peek()[0]) {
                    queue.poll();
                } else {
                    end = queue.poll()[1];
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}