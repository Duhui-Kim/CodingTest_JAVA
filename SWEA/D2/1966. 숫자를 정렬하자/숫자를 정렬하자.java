import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // testCase만큼 반복을 진행한다.
        int testCase = sc.nextInt();
        for (int t = 1; t <= testCase; t++) {
            
            // N을 입력받고 N 크기의 배열을 만든다.
            int N = sc.nextInt();
            int[] arr = new int[N];
            
            // N개의 숫자를 배열에 입력한다.
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
        
            /*
             이중반복문을 돌리며 i와 비교했을 때 더 작은 값의 idx를 찾고,
             두 숫자를 바꿔준다.
             */
            for (int i = 0; i < N; i++) {
                int min = i;
                for (int j = i; j < N; j++) {
                    if (arr[min] > arr[j]) {
                        min = j;
                    }
                }
                swapNum(arr, i, min);
            }
            
            // 결과를 출력한다.
            System.out.print("#" + t);
            for (int i = 0; i < N; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }

    private static void swapNum(int[] arr, int i, int min) {
        int tmp = arr[i];
        arr[i] = arr[min];
        arr[min] = tmp;
    }
}
