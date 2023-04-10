import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        long value, cnt;
        private Pair(long value, long cnt) {
            this.value = value;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            // deque 선언하고 초기값 넣어주기
            ArrayDeque<Pair> deque = new ArrayDeque<>();
            deque.offer(new Pair(0, 0));

            // 최댓값 초기화
            long maxSize = 0;

            // 입력받으면서 진행한다.
            while (st.hasMoreTokens()){
                long nxt = Long.parseLong(st.nextToken());

                // 다음 값이 이전에 들어간 값과 같으면 이전 값의 cnt만 증가시킨다.
                if (deque.peekLast().value == nxt) {
                    deque.peekLast().cnt++;
                    continue;
                }
                // 다음 값이 이전 값보다 크면 새롭게 넣어준다.
                if (deque.peekLast().value < nxt) {
                    deque.offer(new Pair(nxt, 1));
                    continue;
                }

                // 다음 값이 이번 값보다 작은 경우엔 아래 진행
                // (현재 deque 안은 내림차순으로 구성되어있는 상태)
                long cnt = 0;
                while (deque.peekLast().value > nxt) {
                    // cnt를 뽑아주고,
                    cnt = deque.peekLast().cnt;

                    // value와 곱한 값을 maxSize와 비교한다.
                    long square = deque.pollLast().value * cnt;
                    if (maxSize < square) {
                        maxSize = square;
                    }
                    if (deque.peekLast().value >= nxt) deque.peekLast().cnt += cnt;
                }

                // 같은 값에서 끝나지 않았다면 stack에 넣기
                if (deque.peekLast().value != nxt) {
                    deque.offer(new Pair(nxt, cnt + 1));
                } else {
                    deque.peekLast().cnt++;
                }
            }

            // 여기까지 왔으면 deque는 내림차순 구성되어있음
            long cnt = 0;
            while (!deque.isEmpty()) {
                deque.peekLast().cnt = deque.peekLast().cnt + cnt;

                // 하나씩 뽑아서 최댓값 구해주기
                cnt = deque.peekLast().cnt;
                long square = deque.pollLast().value * cnt;

                if (maxSize < square) {
                    maxSize = square;
                }
            }
            sb.append(maxSize).append("\n");
        }
        System.out.println(sb);
    }
}