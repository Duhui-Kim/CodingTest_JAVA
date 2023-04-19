import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        x = x < w-x ? x : w-x;
        y = y < h-y ? y : h-y;

        System.out.println(x < y? x : y);
    }
}