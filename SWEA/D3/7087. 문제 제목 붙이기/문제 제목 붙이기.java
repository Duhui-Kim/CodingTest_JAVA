import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        loop:
        for (int t = 1; t <= testCase; t++) {

            // A ~ Z까지 입력된 빈도수를 저장할 int배열
            int[] arr = new int[27];

            // N을 입력받고, N개의 문자를 입력받는다.
            int N = sc.nextInt();

            // 배열에서 (문자의 첫 글자 - 'A') idx 값을 증가시킨다.
            for (int i = 0; i < N; i++) {
                String input = sc.next();
                arr[input.charAt(0)-'A']++;
            }

            // 0을 만나면 그 때의 idx 값을 출력하고,
            // 끝까지 순회했는데 0이 없으면 26을 출력한다. (A ~ Z)
            int num = 0;
            for (int i = 0; i < 27; i++) {
                if(arr[i] == 0){
                    num = i;
                    break;
                }
            }
            System.out.printf("#%d %d\n", t, num);
        }
    }
}
