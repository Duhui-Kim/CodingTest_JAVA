import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;

        // 나누어 떨어지는 경우에는 D 출력 후 종료
        if(D%P == 0 || D%Q == 0) {
            System.out.println(D);
            return;
        }

        if(P > Q) {
            min = Math.min(min, findMin(D, P, Q, D/P+1, 0, 0));
        } else {
            min = Math.min(min, findMin(D, Q, P, D/Q+1, 0, 0));
        }
        System.out.println(min);
    }

    private static int findMin(int d, int q, int p, int n, int m, int cnt) {
        if(min == d || n < 0 || cnt > p) return min;

        while(d > q * n + p * m) {
            m++;
        }

        min = Math.min(min, q * n + p * m);

        return findMin(d, q, p, n-1, m, cnt+1);
    }
}
