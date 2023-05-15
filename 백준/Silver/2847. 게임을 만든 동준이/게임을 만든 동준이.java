import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        int answer = 0;
        for (int i = N-1; i >= 0; i--) {
            if (arr[i+1] <= arr[i]) {
                answer += arr[i] - (arr[i+1] - 1);
                arr[i] = arr[i+1] - 1;
            }
        }
        System.out.println(answer);
    }
}