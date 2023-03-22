import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N과 L을 입력받는다
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        // deque와 StringBuilder를 선언하고 진행
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        // 숫자를 입력받으면서 진행한다.
        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 숫자를 입력받고 그 때의 idx도 저장해둔다
            int val = Integer.parseInt(st.nextToken());
            int[] arr = new int[2];
            arr[0] = val;
            arr[1] = i;

            // deque가 비어있지 않으면, 마지막에 넣은 숫자와 현재 숫자를 비교해서 queue에 들어있는 숫자가 더 크면
            // 그 숫자를 빼주고, queue의 숫자가 더 작으면 종료
            while (!deque.isEmpty()) {
                if(deque.peekLast()[0] > arr[0]) {
                    deque.pollLast();
                } else {
                    break;
                }
            }
            // deque에 입력받은 숫자를 넣어준다.
            deque.offer(arr);

            // queue의 맨 앞에 있는 숫자의 유효기간이 지난 것은 모두 빼준다.
            while(deque.peekFirst()[1] < i-L+1) {
                deque.pollFirst();
            }
            // 맨 앞에 있는 숫자가 제일 작은 수이다.
            sb.append(deque.peekFirst()[0] + " ");
        }
        // 정답 출력
        System.out.println(sb);
    }
}