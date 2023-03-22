import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < 10; tc++) {
            int test = sc.nextInt();

            // 거듭제곱 밑수 N과 거듭제곱을 할 횟수 M을 입력받는다.
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 거듭제곱 값을 구한다.
            int answer = pow(N, M);

            // 값을 저장
            sb.append(String.format("#%d %d\n", test, answer));
        }
        // 출력
        System.out.println(sb);
    }

    public static int pow(int C, int N) {
        // 기저조건
        if(N == 1) return C;

        // 재귀조건
        int x = pow(C, N/2);

        // 1. 짝수일 때
        if(N%2 == 0) {
            return x * x;
        }
        // 2. 홀수일 때
        else {
            return x * x * C;
        }
    }
}