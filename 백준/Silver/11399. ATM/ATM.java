import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        // 숫자를 입력받으면서 PriorityQeueu에 넣는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        // queue에서 하나씩 꺼내면서 N부터 1까지 곱해준다.
        int answer = 0;
        while(!queue.isEmpty()) {
            answer += queue.poll() * N;
            N--;
        }

        // 결과를 출력
        System.out.println(answer);
    }
}
