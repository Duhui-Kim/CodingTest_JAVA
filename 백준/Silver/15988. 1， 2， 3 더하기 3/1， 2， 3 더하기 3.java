import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        int[] num = new int[1000001];
        num[0] = 1;

        for (int i = 1; i <= 1000000; i++) {
            if(i < 2) {
                num[i] = num[i-1] % 1000000009;
            } else if (i < 3) {
                num[i] = (num[i-1] + num[i-2]) % 1000000009;
            } else {
                num[i] = ((num[i-1] + num[i-2]) % 1000000009 + num[i-3]) % 1000000009;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < testCase; tc++) {
            int N = sc.nextInt();

            sb.append(num[N]).append("\n");
        }
        System.out.println(sb);
    }
}