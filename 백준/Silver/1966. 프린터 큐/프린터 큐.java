import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];

            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                queue.offer(num);
            }

            int cnt = 1;
            int j = 0;
            while (!(j%N == idx && arr[idx] == queue.peek())) {
                if (arr[j%N] == queue.peek()) {
                    queue.poll();
                    cnt++;
                }
                j++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}