import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (binary(B, A[i])) {
                sb.append(A[i]).append(" ");
                cnt++;
            }
        }
        if (cnt != 0) {
            System.out.println(cnt);
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }

    private static boolean binary(int[] arr, int num) {
        int st = 0;
        int ed = arr.length - 1;
        int mid = 0;

        while (st <= ed) {
            mid = (st + ed) / 2;
            if (arr[mid] == num) {
                break;
            } else if (arr[mid] > num) {
                ed = mid - 1;
            } else {
                st = mid + 1;
            }
        }
        if (arr[mid] == num) return false;

        return true;
    }
}