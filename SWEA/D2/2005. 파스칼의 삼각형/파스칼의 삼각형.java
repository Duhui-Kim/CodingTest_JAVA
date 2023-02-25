import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        for (int t = 1; t <= testCase; t++) {
            int N = sc.nextInt();

            // arr와 tmp를 선언한 뒤, 첫 번째 값을 1로 선언한다.
            int[] arr = new int[N];
            int[] tmp = new int[N];

            arr[0] = 1;
            tmp[0] = 1;
            
            // 1을 먼저 출력하고,
            // 2 ~ N-1까지는 이전에 입력되었던 숫자를 바탕으로 더해준 뒤 출력한다.
            System.out.println("#" + t);
            System.out.println(arr[0]);
            for (int i = 1; i < N; i++) {
                System.out.print(1 + " ");
                for (int j = 1; j <= i; j++) {
                    if(i%2 == 0){
                        arr[j] = tmp[j] + tmp[j-1];
                        System.out.print(arr[j] + " ");
                    } else {
                        tmp[j] = arr[j] + arr[j-1];
                        System.out.print(tmp[j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
