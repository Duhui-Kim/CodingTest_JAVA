import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        for (int t = 1; t <= testCase; t++) {

            // 숫자 개수 N을 입력받고, N 크기의 배열을 만들어 값을 넣는다.
            int N = sc.nextInt();
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            // 답을 저장할 max를 선언하고 이중 반복문을 돌며 모든 경우의 수를 추출한다.
            int max = 0;
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    // 곱한 값을 자릿수별로 체크해야하므로 tmp에 담아 테스트를 진행한다.
                    int multi = arr[i] * arr[j];
                    int tmp = multi;
                    boolean isIncrease = true;

                    // tmp를 10으로 나눈 값이 0이 아닐 때 진행한다.
                    while(tmp/10 != 0){
                        // 아래의 자릿수의 값이 위의 자릿수의 값보다 작으면 false로 변경하고 종료한다.
                        if(tmp%10 < (tmp/10)%10) {
                            isIncrease = false;
                            break;
                        } else {
                            tmp /= 10;
                        }
                    }
                    // boolean이 true일 경우에만 max와 비교한다
                    if(isIncrease) {
                        max = max<multi? multi:max;
                    }
                }
            }
            if(max != 0) {
                System.out.printf("#%d %d\n", t, max);
            } else {
                System.out.printf("#%d %d\n", t, -1);
            }
        }
    }
}
