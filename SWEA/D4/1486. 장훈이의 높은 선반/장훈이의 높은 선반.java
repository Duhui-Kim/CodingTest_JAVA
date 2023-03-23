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

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;

            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            backTracking(arr, 0, 0);

            sb.append(String.format("#%d %d\n", tc, answer-B));
        }
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int k, int sum) {
        if(k == N) {
            if(sum >= B) {
                answer = Math.min(answer, sum);
            }
            return;
        }

        backTracking(arr, k+1, sum);
        backTracking(arr, k+1, sum+arr[k]);
    }
}