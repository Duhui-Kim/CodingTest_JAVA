import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char[] input = sc.nextLine().toCharArray();
            sb.append(input[0] + "" + input[input.length-1]).append("\n");
        }
        System.out.println(sb);
    }
}