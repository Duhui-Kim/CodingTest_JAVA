import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 두 수를 고르고, 해당 숫자들과 더해서 0이 되는 숫자의 개수를 세준다.
        long cnt = 0;
        for (int i = 0; i < N-2; i++) {
            for (int j = i+1; j < N-1; j++) {
                cnt += binary(arr, j+1, N, -(arr[i] + arr[j]));
            }
        }

        System.out.println(cnt);
    }

    private static int binary(int[] arr, int st, int ed, int num) {
        int mid;

        int s = st;
        int e = ed;

        // lowerbound
        while (s < e) {
            mid = (s + e) / 2;
            if (num <= arr[mid]) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        int s2 = st;
        int e2 = ed;

        // upperbound
        while (s2 < e2) {
            mid = (s2 + e2) / 2;
            if (num < arr[mid]) {
                e2 = mid;
            } else {
                s2 = mid + 1;
            }
        }

        return s2 - s;
    }
}