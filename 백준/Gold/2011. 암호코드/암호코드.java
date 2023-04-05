import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        if (input == null || input == "") {
            System.out.println(0);
            return;
        }

        // number 받기
        char[] number = input.toCharArray();

        // 시작 숫자가 0이면 해석불가능한 암호
        if (number[0] == '0') {
            System.out.println(0);
            return;
        }

        int N = number.length - 1;

        if (N == 0) {
            System.out.println(1);
            return;
        }

        // dp 배열 채워놓기
        long[] dp = new long[N+2];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
        }

        long answer = 1;

        // 뒤에서부터 진행
        int cnt = 1;
        for (int i = N; i >= 1; i--) {
            if (number[i] == '0') {
                // 1이나 2일 경우 지금까지 쌓인 cnt 곱해주고 진행
                if (number[i-1] == '1' || number[i-1] == '2') {
                    answer = answer * dp[cnt] % 1000000;
                    cnt = 1;
                    i--;
                }
                // 0 앞에 1이나 2가 아니면 불가능한 암호
                else {
                    System.out.println(0);
                    return;
                }
            } else {
                // 지금 숫자가 0이 아닐 때, 7 미만이면 1이나 2가 나오면 모두 가능
                if (number[i] - '0' < 7) {
                    if (number[i-1] == '1' || number[i-1] == '2') {
                        cnt++;
                    } else {
                        answer = (answer * dp[cnt]) % 1000000;
                        cnt = 1;
                    }
                }
                // 지금 숫자가 7 이상이면 앞의 숫자가 1일 경우에만 cnt 증가
                else {
                    if (number[i-1] == '1') {
                        cnt++;
                    } else {
                        answer = (answer * dp[cnt]) % 1000000;
                        cnt = 1;
                    }
                }
            }
        }
        answer = answer * dp[cnt] % 1000000;
        System.out.println(answer);
    }
}