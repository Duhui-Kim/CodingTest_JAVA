import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int start = N - 60;

        while (sum(start) != N) {
            start++;
            if(start > N) {
                start = 0;
                break;
            }
        }
        System.out.println(start);
    }
    private static int sum(int num) {
        int number = num;

        int tmp = num;
        while (tmp > 0) {
            number += tmp % 10;
            tmp /= 10;
        }

        return number;
    }
}