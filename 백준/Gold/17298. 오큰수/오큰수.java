import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input을 저장할 deque, 임시로 저장할 tmp, 정답을 저장할 answer
        ArrayDeque<Integer> input = new ArrayDeque<>();
        ArrayDeque<Integer> tmp = new ArrayDeque<>();
        ArrayDeque<Integer> answer = new ArrayDeque<>();

        // 숫자를 입력받는다.
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input.offerFirst(Integer.parseInt(st.nextToken()));
        }

        // 입력값이 빌 때까지 진행
        while (!input.isEmpty()) {
            // tmp가 비어있으면 오른쪽에 그 숫자보다 큰 숫자가 없는 것이므로 -1 출력
            if(tmp.isEmpty()) {
                answer.offerFirst(-1);
                tmp.offerFirst(input.pollFirst());
                
            // input의 값이 tmp의 가장 위에 있는 값보다 작으면 오큰수는 tmp.peekFirst이다.
            } else if (input.peekFirst() < tmp.peekFirst()) {
                answer.offerFirst(tmp.peekFirst());
                tmp.offerFirst(input.pollFirst());
                
            // tmp가 더 작을 경우 tmp에 들어있는 값은 오큰수가 될 수 없으므로 빼준다.
            } else {
                tmp.pollFirst();
            }
        }

        // answer의 값을 차례로 꺼내서 출력한다.
        StringBuilder sb = new StringBuilder();
        while (!answer.isEmpty()) {
            sb.append(answer.pollFirst()).append(" ");
        }
        System.out.println(sb);
    }
}