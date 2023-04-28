import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int cnt = -1;
    private static int N;
    private static List<Long> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        if (N <= 9) {
            System.out.println(N);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            backTracking(1, i, i);
        }

        if (list.size() <= N) {
            System.out.println(-1);
            return;
        }
        
        Collections.sort(list);

        System.out.println(list.get(N));
    }

    private static void backTracking(int k, long pre, long num) {
        list.add(num);

        for (long i = pre + 1; i <= 9; i++) {
            backTracking(k+1, i, (long) (Math.pow(10, k) * i + num));
        }
    }
}