import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        // k가 0보다 작거나 n이 k보다 작으면 0을 반환
        if(K<0) {
            System.out.println(0);
            return;
        } else if (N < K) {
            System.out.println(0);
            return;
        }

        int answer = 1;
        int division = 1;
        for (int i = N, j=1; j <= K; i--, j++) {
            answer *= i;
            division *= j;
        }
        System.out.println(answer/division);

    }
}
