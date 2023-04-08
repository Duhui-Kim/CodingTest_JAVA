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

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(arr, Integer.parseInt(st.nextToken()))).append(" ");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int[] arr, int num) {
        int st = 0;
        int ed = arr.length-1;

        while (st <= ed) {
            int mid = (st + ed) / 2;

            if (arr[mid] == num) {
                return 1;
            } else if (arr[mid] > num) {
                ed = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        return 0;
    }
}