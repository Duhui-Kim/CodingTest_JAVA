import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();


        for (int t = 1; t <= testCase; t++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();

            // PriorityQueue 준비
            PriorityQueue<Integer> queue = new PriorityQueue<>();

            // flag를 하나 세운다
            boolean isPossible = true;

            // 입력받다가 M보다 작은 수가 있으면 입력 중지 후 Impossible 출력
            // M보다 작은 수가 없으면 다음 step으로 넘어간다.
            for (int i = 0; i < N; i++) {
                int num = sc.nextInt();
                if (num < M) {
                    isPossible = false;
                } else {
                    queue.offer(num);
                }
            }

            // time을 M으로 놓는다.
            int time = M;

            check:
            while (isPossible) {
                // M의 시간이 지나면 K만큼의 붕어빵이 만들어지므로
                // K만큼의 사람을 촉박한 순으로 제거한다.
                for (int i = 0; i < K; i++) {
                    // queue가 비었다면 모든 사람에게 준 것이므로 종료한다.
                    if (queue.isEmpty()) {
                        break check;
                    }
                    // 이 때 queue에서 poll한 값이 time보다 작으면 불가능하므로
                    // flag를 false로 하고 break한다.
                    if(time > queue.poll()) {
                        isPossible = false;
                        break check;
                    }
                }
                time += M;
            }

            // flag에 따라 결과를 출력한다.
            if(isPossible) {
                System.out.printf("#%d %s\n", t, "Possible");
            } else {
                System.out.printf("#%d %s\n", t, "Impossible");
            }
        }

    }
}
