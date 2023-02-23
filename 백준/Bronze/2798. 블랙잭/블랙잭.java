import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        // 중복되지 않는 숫자여야하므로 3중 반복문에서 범위를 i=0부터, j는 i+1부터, k는 j+1부터로 정한다.
        int blackJack = 0;
        for (int i = 0; i < N-2; i++) {
            for (int j = i+1; j < N-1; j++) {
                for(int k = j+1; k < N; k++){
                    int num = arr[i] + arr[j] + arr[k];

                    // M을 넘지 않는 수 중 최댓값을 구한다.
                    if(num <= M) blackJack = Math.max(blackJack, num);
                }
            }
        }

        System.out.println(blackJack);
    }
}
