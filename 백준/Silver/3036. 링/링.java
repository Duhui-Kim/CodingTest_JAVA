import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(sc.nextLine());

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        int large = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N-1; i++) {
            int next = Integer.parseInt(st.nextToken());

            int a = next;
            int b = large;
            if (next > large) {
                a = large;
                b = next;
            }

            int c = 0;
            // 유클리드호제법
            while (b % a != 0) {
                c = b % a;
                b = a;
                a = c;
            }
            sb.append(large/a).append("/").append(next/a).append("\n");
        }
        System.out.println(sb);
    }
}