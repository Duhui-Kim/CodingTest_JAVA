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

        // P > Q이면 P를 앞에 쓰고, Q가 더 크면 Q를 앞에 써서 findMin 함수 진행
        if(P > Q) {
            min = Math.min(min, findMin(D, P, Q, D/P+1, 0, 0));
        } else {
            min = Math.min(min, findMin(D, Q, P, D/Q+1, 0, 0));
        }

        // min 값 출력
        System.out.println(min);
    }

    private static int findMin(int d, int q, int p, int n, int m, int cnt) {
        // min == d이면 더 작은 수가 없으므로 종료
        // n < 0이면 종료
        // cnt 0부터 시작해서 작은 수(p)보다 커지는 경우 공배수이므로 경우의 수가 반복되므로 종료
        if(min == d || n < 0 || cnt > p) return min;

        // 큰 수 * n과 작은 수 * m이 결과값보다 작을 때까지 m을 늘려준다.
        while(d > q * n + p * m) {
            m++;
        }

        // min값을 현재 min값과 결과값을 비교해서 더 작은 수를 입력한다.
        min = Math.min(min, q * n + p * m);

        // n을 1 줄여주고 cnt를 1 늘려준 뒤 findMin을 또 진행한다.
        return findMin(d, q, p, n-1, m, cnt+1);
    }
}
