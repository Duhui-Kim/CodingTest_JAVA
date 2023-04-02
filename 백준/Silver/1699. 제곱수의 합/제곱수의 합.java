import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int start = (int) Math.sqrt(N) + 1;

        int[] arr = new int[N+1];
        Arrays.fill(arr, Integer.MAX_VALUE/2);
        arr[0] = 0;

        for (int i = start; i >= 1; i--) {
            for (int j = 1; j <= N; j++) {
                if (j-i*i < 0) continue;
                arr[j] = arr[j] < arr[j-i*i] + 1 ? arr[j] : arr[j-i*i] + 1;
            }
        }
        System.out.println(arr[N]);
    }
}