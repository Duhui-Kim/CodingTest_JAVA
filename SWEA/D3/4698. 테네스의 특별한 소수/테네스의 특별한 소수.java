import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            int D = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();

            // 소수판별 불리안 배열을 만든다.
            // true일 경우 소수 X
            boolean[] Prime = new boolean[B+1];
            Prime[0] = true;
            Prime[1] = true;

            // 소수 배열 만들기
            for (int i = 2; i * i <= B; i++) {
                // 이미 합성수로 판별되었으면 아래 진행 X
                if(Prime[i]) continue;

                // 소수의 배수들을 모두 true로 바꿈
                for(int j=i*i; j <= B; j+=i) {
                    Prime[j] = true;
                }
            }
            // 소수 배열이 완성되었으므로 A부터 B까지 중 D를 포함하는 소수 찾기
            int cnt = 0;
            for (int i = A; i <= B; i++) {
                // 합성수이면 지나감
                if(Prime[i]) continue;

                // 소수인 경우 자릿수 중 D가 있으면 cnt를 증가시킴
                int num = i;
                while(num > 0){
                    if(num%10 == D) {
                        cnt++;
                        break;
                    } else {
                        num /= 10;
                    }
                }
            }
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
