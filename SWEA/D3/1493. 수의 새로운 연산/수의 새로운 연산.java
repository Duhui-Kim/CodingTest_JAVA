import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 배열은 하나만 만들어서 아래에서 써주면 되기 때문에
        // testCase 밖에 선언했다.
        int[] arr = new int[501];
        for (int i = 1; i < 501; i++) {
            arr[i] = arr[i-1] + i;
        }

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            int ax = 0;
            int bx = 0;
            while (A > arr[ax]) ax++;
            while (B > arr[bx]) bx++;

            /*
            < A의 좌표, B의 좌표 계산 >
            &(A) 연산
            어떤 특정한 값 A의 좌표를 찾으려면, A보다 큰 (x, 1) 지점을 찾은 뒤
            (x, 1) - A의 값만큼 x좌표에서 빼주고, y좌표에서 더해주면 된다.
             */
            int xA = ax - (arr[ax] - A);
            int yA = 1 + (arr[ax] - A);

            int xB = bx - (arr[bx] - B);
            int yB = 1 + (arr[bx] - B);

            /*
             < A+B의 좌표를 통해 값 구하기 >
             #(A) 연산
             &연산과 반대로 y좌표 - 1만큼을 x에게 더해준 뒤 (x, 1) - 더해준 값을 반환하면 된다.
             */
            int num = xA + xB + yA + yB - 1;
            int answer = arr[num] - (yA + yB - 1);

            System.out.printf("#%d %d\n", t, answer);
        }
    }
}
