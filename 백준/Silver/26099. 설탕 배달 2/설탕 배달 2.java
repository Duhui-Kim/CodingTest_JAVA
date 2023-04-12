import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();

        long five = 0;
        long three = 0;

        // 먼저 N을 5로 나눈다.
        five = N / 5;

        // 5 곱하기 5로나눈몫이 N이면 봉투의 최소 개수는 five
        if (five * 5 == N) {
            System.out.println(five);
            return;
        }

        // five를 하나씩 줄이고 three를 늘리며 N을 맞춰본다.
        // 15마다(최소공배수) 같은 수가 반복되므로 15회가 넘어가면 종료
        int cnt = 0;
        while (five >= 0 && 5 * five + 3 * three != N) {
            if (5 * five + 3 * three > N) {
                five--;
                cnt++;
            }
            while (5 * five + 3 * three < N) three++;
            if (cnt == 15) break;
        }

        // five랑 three 곱하고 더한 값이 N과 같으면 두 수를 더한 값이 정답
        // 아닌 경우 -1 출력
        if (five >= 0 && three >= 0 && 5 * five + 3 * three == N) {
            System.out.println(five + three);
        } else {
            System.out.println(-1);
        }
    }
}