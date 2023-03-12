import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine());

        // Priorityqueue에 숫자를 모두 입력한다.
        for (int i = 0; i < N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        // max를 0으로 두고 반복을 진행한다.
        int max = 0;

        // priorityQueue에서 항상 가장 큰 수가 나오는데,
        // 가장 큰 수 * i 값이 현재 들 수 있는 무게이다. 이 값이 제일 클 때까지 반복한다.
        for (int i = 1; i <= N; i++) {
            int num = queue.poll() * i;
            if(num > max) {
                max = num;
            }
        }
        System.out.println(max);
    }
}
