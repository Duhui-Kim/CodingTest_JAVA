import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0) {
                plus.offer(num);
            } else {
                minus.offer(num);
            }
        }

        int answer = 0;
        while (!plus.isEmpty()) {
            int a = plus.poll();
            if(plus.isEmpty()) {
                answer += a;
                break;
            }
            int b = plus.poll();
            if(a > 1 && b > 1) {
                answer += a * b;
            } else {
                answer += a + b;
            }
        }
        while (!minus.isEmpty()) {
            int a = minus.poll();
            if(minus.isEmpty()) {
                answer += a;
                break;
            }
            int b = minus.poll();
            answer += a * b;
        }
        System.out.println(answer);
    }
}
