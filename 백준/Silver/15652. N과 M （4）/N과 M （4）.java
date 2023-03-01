import javax.naming.PartialResultException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] answer = new int[M];

        backTracking(answer, N, 0, sb);
        System.out.println(sb);
    }

    private static void backTracking(int[] answer, int n, int m, StringBuilder sb) {
        if(m == answer.length) {
            for (int i = 0; i < answer.length; i++) {
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if(m == 0) {
                answer[m] = i;
                backTracking(answer, n, m+1, sb);
            } else {
                if (answer[m-1] <= i) {
                    answer[m] = i;
                    backTracking(answer, n, m+1, sb);
                }
            }
        }
    }
}