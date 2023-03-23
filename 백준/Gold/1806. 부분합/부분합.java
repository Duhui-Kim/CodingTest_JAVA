import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자 N과 합 S 입력받기
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        // 배열 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int minLength = Integer.MAX_VALUE;

        // 투포인터로 부분합 구하기
        int p = 0;
        int q = 0;
        int sum = 0;
        while (q <= N) {
            while (sum < S && q < N) {
                sum += arr[q++];
            }
            while (sum - arr[p] >= S && p < q) {
                sum -= arr[p++];
            }
            if(sum >= S) {
                minLength = Math.min(minLength, q - p);
            }
            sum += arr[q++];
        }
        if(minLength == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }
    }
}