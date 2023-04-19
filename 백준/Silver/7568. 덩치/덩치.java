import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] heavy = new int[2][N];

        for (int i = 0; i < N; i++) {
            heavy[0][i] = sc.nextInt();
            heavy[1][i] = sc.nextInt();
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = 1;
            for (int j = 0; j < N; j++) {
                if (heavy[0][i] < heavy[0][j] && heavy[1][i] < heavy[1][j]) answer[i]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}