import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // N이 1이면 0 출력 후 종료
        if (N == 1) {
            System.out.println(0);
            return;
        }
        
        // 작은 수부터 꺼내기 위해 pq 선언
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        // queue에 넣어주기
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        // queue에서 하나씩 꺼내주고 더 해줌
        int answer = 0;
        while (!queue.isEmpty()) {
            int a = queue.poll();

            // queue에서 꺼낸 수가 마지막 수였으면 종료
            if (queue.isEmpty()) break;

            int b = queue.poll();

            // 두 수를 더해준다.
            queue.offer(a + b);
            answer += a + b;
        }

        System.out.println(answer);
    }
}