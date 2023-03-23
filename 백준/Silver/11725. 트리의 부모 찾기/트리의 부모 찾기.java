import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] arr = new ArrayList[N+1];
        int[] answer = new int[N+1];

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(arr[a] == null) {
                ArrayList<Integer> A = new ArrayList<>();
                A.add(b);
                arr[a] = A;
            } else {
                arr[a].add(b);
            }
            if(arr[b] == null) {
                ArrayList<Integer> B = new ArrayList<>();
                B.add(a);
                arr[b] = B;
            } else {
                arr[b].add(a);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int nxt = queue.poll();

            for (int cur : arr[nxt]) {
                if(answer[nxt] == cur) continue;
                answer[cur] = nxt;
                queue.offer(cur);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}