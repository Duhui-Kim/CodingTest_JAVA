import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 빌딩의 수 N개
        int N = Integer.parseInt(br.readLine());

        // 빌딩 입력 받기
        long[][] input = new long[N][2];

        // stack처럼 쓸 deque
        ArrayDeque<long[]> deque = new ArrayDeque<>();

        // 입력받으면서 input[i][1]은 초기값 1을 넣어줌
        for (int i = 0; i < N; i++) {
            input[i][0] = Long.parseLong(br.readLine());
            input[i][1] = 1;
        }

        // 정답을 저장할 인자 선언
        long answer = 0;

        // 배열의 뒤에서부터 반복문 실행
        for (int i = N-1; i >= 0; i--) {
            // deque가 비어있지 않고, 마지막 값이 들어갈 값보다 작으면 빼준다.
            while (!deque.isEmpty() && deque.peekLast()[0] < input[i][0]) {
                    input[i][1] += deque.peekLast()[1];
                    answer += deque.pollLast()[1];
            }
            deque.offerLast(input[i]);
        }

        System.out.println(answer);
    }
}