import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 원소는 200000000까지 들어올 수 있으므로 Long 타입으로 선언
        long[] arr = new long[N];

        // 원소를 입력받는다.
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 정렬
        Arrays.sort(arr);

        // 두 수의 합을 저장할 배열 선언
        long[] sumArr = new long[N * N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumArr[N * i + j] = arr[i] + arr[j];
            }
        }

        // 정렬
        Arrays.sort(sumArr);

        // 배열의 가장 끝 원소 = 가장 큰 수
        // 해당 원소에서 binarySearch가 true이면 해당 수에서 끝
        long answer = 0;
        loop:
        for (int i = N-1; i >= 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                long num = arr[i] - arr[j];
                if(binarySearch(sumArr, num, N))  {
                    answer = arr[i];
                    break loop;
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean binarySearch(long[] sumArr, long num, int N) {
        int st = 0;
        int ed = N * N - 1;
        int mid = 0;

        while (st < ed) {
            mid = (st + ed) / 2;
            if (sumArr[mid] == num) {
                return true;
            } else if (sumArr[mid] < num) {
                st = mid + 1;
            } else {
                ed = mid - 1;
            }
        }
        return false;
    }
}