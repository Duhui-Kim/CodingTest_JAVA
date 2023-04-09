import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) return o1 - o2;
            return Math.abs(o1) - Math.abs(o2);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int cmd = Integer.parseInt(br.readLine());

            if (cmd == 0) {
                if (queue.isEmpty()) sb.append(0).append("\n");
                else sb.append(queue.poll()).append("\n");
            } else {
                queue.offer(cmd);
            }
        }
        System.out.println(sb);
    }
}