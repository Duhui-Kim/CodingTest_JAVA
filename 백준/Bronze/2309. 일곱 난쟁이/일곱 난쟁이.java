import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = 0;

        // 배열을 입력받으며 전체의 키를 구한다.
        int[] arr = new int[9];
        for(int i=0; i<9; i++){
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        // 오름차순으로 정렬한다.
        Arrays.sort(arr);

        // 두 개의 포인터를 정하고 시작한다.
        // 일곱난쟁이를 찾을 수 없는 경우는 없으므로 조건이 간단하다.
        int hundred = 0;
        int p = 0;
        int q = 8;
        while (hundred != 100){
            hundred = total - arr[p] - arr[q];
            if(hundred < 100) q--;
            else if(hundred > 100) p++;
        }
        // p와 q를 제외한 아이들을 출력한다.
        for(int i=0; i<9; i++){
            if(i != p && i != q) System.out.println(arr[i]);
        }
    }
}
