import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int E = sc.nextInt();
        int S = sc.nextInt();
        int M = sc.nextInt();

        int e = 15;
        int s = 28;
        int m = 19;

        if(e == E) E = 0;
        if(m == M) M = 0;

        int year = S;

        while (true) {
            if(year%e == E && year%m == M) {
                break;
            }
            year += s;
        }

        System.out.println(year);
    }
}