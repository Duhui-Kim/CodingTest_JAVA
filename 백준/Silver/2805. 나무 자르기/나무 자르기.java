import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        long left = 0;
        long right = Integer.MAX_VALUE - 2;
        long mid = 0;
        while (left < right) {
            mid = (left + right + 1) / 2;
            long trees = 0;
            for (int i = 0; i < N; i++) {
                trees += arr[i] - mid > 0? arr[i] - mid : 0;
            }
            if (trees < M) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        System.out.println(left);
    }
}