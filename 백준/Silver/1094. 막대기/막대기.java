import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int cnt = 0;
        for (int i = 0; i <= 6; i++) {
            if ((N & 1 << i) > 0) cnt++;
        }
        System.out.println(cnt);
    }
}