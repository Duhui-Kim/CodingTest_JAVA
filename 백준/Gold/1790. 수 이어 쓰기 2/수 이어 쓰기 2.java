import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextInt();
        long b = sc.nextInt();

        long len = maxLength(a, 9, 1);

        if (len < b) {
            System.out.println(-1);
            return;
        }

        long answer = findNum(b, 9, 1, 0);

        System.out.println(answer);
    }

    private static long findNum(long b, long i, long d, long num) {
        if (b > i*d) {
            return findNum(b-(i*d), i*10, d+1, num + i);
        } else {
            long tmp = b / d + num;

            if (b % d == 0) {
                return tmp % 10;
            } else {
                tmp++;
                char[] chars = (tmp+"").toCharArray();

                return chars[(int) (b % d - 1)] - '0';
            }
        }
    }

    private static long maxLength(long a, long i, long d) {
        if (a >= i) {
            return i * d + maxLength(a-i, i * 10, d+1);
        } else {
            return a * d;
        }
    }
}