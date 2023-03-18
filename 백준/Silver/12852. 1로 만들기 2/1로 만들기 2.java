import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();

        // N이 1이면 연산 최솟값은 0이고 원소도 1이므로 종료
        if (N == 1) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        // N+1개의 배열을 만듦
        int[] arr = new int[N+1];

        // arr[i]에 arr[i-1]을 넣고, 2로 나눠지면 2로 나눈 값과 비교
        // 3으로 나눠지면 3으로 나눈 값과 비교해서 작은 값 넣기. 최솟값이 들어갔으므로 ++해줌
        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i-1];
            if(i % 2 == 0) arr[i] = Math.min(arr[i], arr[i/2]);
            if(i % 3 == 0) arr[i] = Math.min(arr[i], arr[i/3]);

            arr[i]++;
        }
        // 최소 연산개수 기록
        sb.append(arr[N]).append("\n");

        // N번째 숫자로부터 내려오면서 1이 되는 동안 거치는 숫자 기록하기
        while (N > 1) {
            sb.append(N).append(" ");

            if(arr[N]-1 == arr[N-1]) {
                N--;
            } else if (N%2 == 0 && arr[N/2] == arr[N]-1) {
                N /= 2;
            } else if (N%3 == 0 && arr[N/3] == arr[N]-1) {
                N /= 3;
            }
        }
        sb.append(1);

        System.out.println(sb);
    }
}