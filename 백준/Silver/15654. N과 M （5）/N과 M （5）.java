import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        boolean[] check = new boolean[N];

        int[] ans = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTracking(arr, ans, check, 0);
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int[] ans, boolean[] check, int k) {
        if(k == ans.length) {
            for (int i = 0; i < ans.length; i++) {
                sb.append(ans[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!check[i]) {
                check[i] = true;
                ans[k] = arr[i];
                backTracking(arr, ans, check, k+1);
                check[i] = false;
            }
        }

    }
}