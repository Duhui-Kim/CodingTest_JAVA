import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = sc.nextInt();

        // 테스트케이스만큼 반복 진행
        for (int tc = 1; tc <= testCase; tc++) {
            // 숫자 N과 M을 입력받는다.
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 마지막 N자리 수가 1인지를 체크해야하므로, ans를 N개의 자리가 1인 숫자로 변형한다.
            int ans = 1;
            for (int i = 0; i < N; i++) ans *= 2;

            // 4일 경우 1111이므로 15, 5의 경우 11111이므로 31 ...
            ans -= 1;

            // M과 ans에 & 연산자를 사용한 값을 M에 저장한다.
            M = M & ans;

            // M과 ans이 같은 경우에만 마지막 N자리가 1인 경우이다.
            if(ans == M) {
                sb.append(String.format("#%d %s\n", tc, "ON"));
            } else {
                sb.append(String.format("#%d %s\n", tc, "OFF"));
            }
        }
        // 정답을 출력
        System.out.println(sb);
    }
}