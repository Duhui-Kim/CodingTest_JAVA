import java.util.Scanner;

public class Main {
    // 1은 1
    // 2는 2
    // 3은 3
    // 4는 5
    // 5는 6
    // 6은 8
    // 7은 9
    // 8은 11
    // 9는 13
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N+1];

        if(N <= 3) {
            System.out.println(N);
            return;
        }

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        for (int i = 4; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= 100; j++) {
                if(i%j == 0 && i/j >= j) {
                    cnt++;
                } else if (i%j == j) break;
            }
            arr[i] = arr[i-1] + cnt;
        }
        System.out.println(arr[N]);
    }
}
