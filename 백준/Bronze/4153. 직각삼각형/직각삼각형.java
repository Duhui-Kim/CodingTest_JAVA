import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (a == 0 && b == 0 && c == 0) break;

            int A = a*a;
            int B = b*b;
            int C = c*c;

            if (A + B == C || A + C == B || B + C == A) {
                sb.append("right\n");
            } else {
                sb.append("wrong\n");
            }
        }
        System.out.println(sb);
    }
}