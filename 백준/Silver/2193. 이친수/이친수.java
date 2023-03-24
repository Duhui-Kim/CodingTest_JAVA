import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // N이 2 이하일 땐 1 출력 후 종료
        if(N <= 2) {
            System.out.println(1);
            return;
        }

        // 최대 90까지 N이 주어지는데 이는 수가 엄청나게 커짐
        long[] arr = new long[N+1];
        arr[1] = 1;
        arr[2] = 1;

        // sumNum을 정의한다.
        // 5자리의 경우 3자리 + 2자리 + 1자리 + 1임
        // 6자리의 경우 4자리 + 3자리 + 2자리 + 1자리 + 1
        long sumNum = 0;
        for (int i = 3; i <= N; i++) {
            sumNum += arr[i-2];
            arr[i] = sumNum + 1;
        }
        // N번째 배열 출력
        System.out.println(arr[N]);
    }
}