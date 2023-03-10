import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int num = 0;
        int idx = 0;
        while (N > num) {
            num += ++idx;
        }
        if(idx%2 == 0) {
            System.out.println((idx-(num-N)) + "/" + (num-N+1));
        } else {
            System.out.println((num-N+1) + "/" + (idx-(num-N)));
        }

    }
}