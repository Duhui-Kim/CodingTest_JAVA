import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        // testCase만큼 반복 진행
        for (int tc = 0; tc < testCase; tc++) {

            // 
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            long distance = y - x;

            // 규칙 상 n제곱의 위치에 따라 홀수와 짝수가 정해지기에, 시작점을 루트 distance로 잡고 시작한다.
            long n = (long) Math.sqrt(distance);

            // n2-n < distance <= n2이면 홀수
            // n2 < distance <= n2+n이면 짝수가 나온다.
            while (true) {
                if (distance > n * n - n && distance <= n * n) {
                    sb.append(2 * n - 1).append("\n");
                    break;
                } else if (distance > n * n && distance <= n * n + n) {
                    sb.append(2 * n).append("\n");
                    break;
                }
                n++;
            }
        }
        // 결과 출력
        System.out.println(sb);
    }
}