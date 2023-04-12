import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] a = sc.nextLine().toCharArray();
        char[] b = sc.nextLine().toCharArray();

        int[] failure = new int[b.length];

        int j = 0;
        for (int i = 1; i < b.length; i++) {
            while (j > 0 && b[i] != b[j]) j = failure[j-1];
            if (b[i] == b[j]) failure[i] = ++j;
        }

        boolean exist = false;
        j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= '0' && a[i] <= '9') continue;

            while (j > 0 && a[i] != b[j]) j = failure[j-1];
            if (a[i] == b[j]) j++;

            if (j >= b.length) {
                exist = true;
                break;
            }
        }

        if (exist) System.out.println(1);
        else System.out.println(0);
    }
}