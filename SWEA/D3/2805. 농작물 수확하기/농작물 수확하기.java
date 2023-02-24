import java.lang.annotation.IncompleteAnnotationException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());

        for (int t = 1; t <= testCase ; t++) {

            // 홀수 N을 입력받고 진행
            int N = Integer.parseInt(sc.nextLine());

            // 정답을 저장할 인자 선언
            int corn = 0;
            int mid = N / 2;
            boolean isIncrease = true;

            // 기본 size를 0으로 설정한다.
            int size = 0;

            // String 형태로 입력받는다.
            for (int i = 0; i < N; i++) {
                String str = sc.nextLine();

                // 중앙값에서 1, 3, 5, ... 5, 3, 1개를 더해주기 위해
                // j를 mid값에서 size만큼 퍼진 값을 더해준다.
                for (int j = 0; j < N; j++) {
                    if (j >= mid - size && j <= mid + size) {
                        corn += Integer.parseInt(str.charAt(j) + "");
                    }
                }
                // i가 mid에 도달하면 isIncrease를 false로 전환한다.
                if (i == mid) isIncrease = false;

                // isIncrease에 따라서 size를 증가시키거나 감소시킨다.
                if (isIncrease) size++;
                else size--;
            }
            // 정답을 출력한다.
            System.out.printf("#%d %d\n", t, corn);
        }
    }
}
