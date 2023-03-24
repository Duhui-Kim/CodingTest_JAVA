import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int B;
    private static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 주어지는 사람 N과 높이 B를 입력받고, 답을 저장할 answer을 max로 두고 시작한다.
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;

            // 배열을 생성하고 수를 입력받는다.
            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 백트래킹을 진행한다
            backTracking(arr, 0, 0);

            // answer - B 값을 출력한다.
            sb.append(String.format("#%d %d\n", tc, answer-B));
        }
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int k, int sum) {
        // k가 N에 도달하면 종료하고, 더한 값이 B보다 크거나 같을 경우에 answer과 비교
        if(k == N) {
            if(sum >= B) {
                answer = Math.min(answer, sum);
            }
            return;
        }

        // 해당 숫자를 선택한 경우와 선택하지 않은 경우를 나눠서 진행
        backTracking(arr, k+1, sum);
        backTracking(arr, k+1, sum+arr[k]);
    }
}