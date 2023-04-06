import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] map = new int[N][N];

        int[] professor = new int[2];
        int[] sungyu = new int[2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = sc.nextInt();
                map[i][j] = num;
                if (num == 5) {
                    professor = new int[] {i, j};
                } else if (num == 2) {
                    sungyu = new int[] {i, j};
                }
            }
        }

        int x = Math.min(professor[0], sungyu[0]);
        int ex = Math.max(professor[0], sungyu[0]);
        int y = Math.min(professor[1], sungyu[1]);
        int ey = Math.max(professor[1], sungyu[1]);

        if ((x - ex) * (x - ex) + (y - ey) * (y - ey) < 25) {
            System.out.println(0);
            return;
        }

        int cnt = 0;
        for (int i = x; i <= ex; i++) {
            for (int j = y; j <= ey; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        if (cnt >= 3) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}