import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int c = a;
        int d = b;

        if (a < b) {
            c = b;
            d = a;
        }

        if (c % d != 0) {
            while (true) {
                int tmp = c % d;
                if (d % tmp == 0) {
                    c = tmp;
                    break;
                } else {
                    c = d;
                    d = tmp;
                }
            }
            System.out.println(c);
            System.out.println(a * b / c);

        } else {
            System.out.println(d);
            System.out.println(c);
        }
    }
}