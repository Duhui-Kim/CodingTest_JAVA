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

        Set<Long> set = new HashSet<>();

        long[] square = new long[1000001];
        long cnt = 0;

        // max 이하의 제곱 수들을 저장
        for (int i = 2; Long.valueOf(i) * Long.valueOf(i) <= max; i++) {
            square[i] = Long.valueOf(i) * Long.valueOf(i);
            cnt++;
        }

        // min의 제곱근부터 max의 제곱근까지만 진행
        for (int i = 2; i < cnt+2; i++) {

            // 시작점을 설정한다.
            long start = 0;

            // min이 i의 제곱으로 나누어 떨어지면 min부터 진행
            if(min % square[i] == 0) start = min;
            // min이 i의 제곱으로 나누어 떨어지지 않으면 몫+1 * i의 제곱부터 진행
            else start = (min / square[i] + 1) * square[i];

            for (long j = start; j <= max; j += square[i]) {
                // set에 추가하기
                set.add(j);
            }
        }
        // cnt는 min 이상 max 이하인 제곱의 배수이므로 값을 빼준다.
        answer -= set.size();

        // 결과 출력
        System.out.println(answer);
    }
}