import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        long answer = max - min + 1;

        boolean[] check = new boolean[1000001];

        // max의 제곱근까지만 진행
        for (long i = 2; i*i <= max; i++) {

            // 시작점을 설정한다.
            // min이 i의 제곱으로 나누어 떨어지면 min부터 진행
            // min이 i의 제곱으로 나누어 떨어지지 않으면 몫+1 * i의 제곱부터 진행
            long start = (min % (i * i) == 0) ? min : (min / (i * i) + 1) * (i * i);

            for (long j = start; j <= max; j += (i * i)) {
                if(!check[(int)(j - min)]) {
                    check[(int)(j - min)] = true;
                    answer--;
                }
            }
        }

        // 결과 출력
        System.out.println(answer);
    }
}