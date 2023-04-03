import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(br.readLine());

            long[] arr = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long max = 0;
            long answer = 0;
            for (int i = N-1; i >= 0; i--) {
                max = Math.max(max, arr[i]);
                answer += max - arr[i];
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}