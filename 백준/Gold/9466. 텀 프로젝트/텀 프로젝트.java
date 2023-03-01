import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 전체 cnt를 N으로 두고 루프인 숫자가 나타날 때마다 빼주는 형식으로 나중에 계산 여부를 줄였다.
            int cnt = N;
            int[] check = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if(i == arr[i]) {
                    check[i] = 1;
                    cnt--;
                }
            }

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            // 순회를 하며 각 숫자를 썼는지 여부를 check를 통해 확인한다.
            // check가 이미 되어있다면 지나가고 안되어있다면, queue에 넣는다.

            // 해당 idx의 값이 이미 체크되어있으면 그 값과 일치하는 값이 나올 때 or queue가 빌 때까지
            // queue 뽑아주면서 check false로 변경 queue 남아있는 원소들의 check를 true로 만듦.

            // 1 -> 3 -> 3 x
            // 2 -> 1 -> 3 x
            // 6 -> 4 -> 7 -> 6 -> 4 x

            for (int i = 1; i <= N; i++) {
                int a = 0;
                if (check[i] == 0 && i <= arr[i]) {
                    check[i] = 1;
                    cnt--;
                    queue.offer(i);
                    while (!queue.isEmpty()) {
                        a = queue.peekLast();
                        if (check[arr[a]] == 0) {
                            check[arr[a]] = 1;
                            cnt--;
                            queue.offer(arr[a]);
                        } else {
                            break;
                        }
                    }
                }
                while (!queue.isEmpty() && queue.peekFirst() != arr[a]) {
                    check[queue.poll()] = 2;
                    cnt++;
                }
                queue.clear();
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}