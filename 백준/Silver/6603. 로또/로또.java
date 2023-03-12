import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            if(S == 0) break;

            int[] arr = new int[S];
            for (int i = 0; i < S; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[] answer = new int[6];
            backTracking(arr, answer, 0);

            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int[] answer, int k) {
        if (k == 6) {
            for (int a: answer) {
                sb.append(a + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(k == 0) {
                answer[k] = arr[i];
                backTracking(arr, answer, k+1);
            } else if (answer[k-1] < arr[i]) {
                answer[k] = arr[i];
                backTracking(arr, answer, k+1);
            }
        }
    }
}