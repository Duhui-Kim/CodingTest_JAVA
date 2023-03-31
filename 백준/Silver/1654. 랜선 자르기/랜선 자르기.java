import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long left = 0;
        long right = Integer.MAX_VALUE;
        long mid = 0;

        while (left < right) {
            mid = (left + right + 1) / 2;
            int cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += arr[i] / mid;
            }
            if (cnt < N) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        System.out.println(left);
    }
}