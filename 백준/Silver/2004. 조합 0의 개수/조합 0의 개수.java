import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long m = sc.nextLong();

        long cnt_2 = 0;
        long cnt_5 = 0;

        cnt_2 += find(2, n);
        cnt_2 -= find(2, n-m);
        cnt_2 -= find(2, m);

        cnt_5 += find(5, n);
        cnt_5 -= find(5, n-m);
        cnt_5 -= find(5, m);

        System.out.println(Math.min(cnt_2, cnt_5));
    }

    private static long find(long n, long target) {

        long cnt = 0;
        for (long i = n; i <= target; i *= n) {
            cnt += target / i;
        }
        return cnt;
    }
}