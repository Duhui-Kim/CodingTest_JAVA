import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] consulting = new int[2][N+1];

        // 날짜와 금액을 입력받는데, 날짜 + 입력날짜가 N+1보다 커지면 불가능하므로 금액 0으로 만듦
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            consulting[0][i] = Integer.parseInt(st.nextToken());
            consulting[1][i] = Integer.parseInt(st.nextToken());
            if(i + consulting[0][i] > N+1) {
                consulting[1][i] = 0;
            }
        }
        int max = 0;
        int[] arr = new int[N+2];
        for (int i = 1; i <= N; i++) {
            arr[i] += consulting[1][i];
            max = Math.max(max, arr[i]);
            int k = i + consulting[0][i];
            if(k > N) continue;
            int m = k+consulting[0][k];
            while(m > N+1) m--;

            for (int j = k; j < m; j++) {
                arr[j] = Math.max(arr[i], arr[j]);
            }
        }
        System.out.println(max);
    }
}
