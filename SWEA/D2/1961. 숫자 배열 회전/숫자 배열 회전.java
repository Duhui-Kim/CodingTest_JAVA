import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    private static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            // 숫자를 입력받고 N * N 크기의 배열을 만든다.
            N = sc.nextInt();
            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            // 90도, 180도, 270도 회전 후 출력을 위해 map 생성
            // 90도 회전 method
            int[][] map90 = rotate90(map);

            // 180도는 90도에서 90도 더 회전
            int[][] map180 = rotate90(map90);

            // 270도는 180도에서 90도 더 회전
            int[][] map270 = rotate90(map180);

            // 출력 method
            System.out.println("#" + t);
            printMap(map90, map180, map270);
        }
    }

    private static void printMap(int[][] map90, int[][] map180, int[][] map270) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map90[i][j]);
            }
            System.out.print(" ");
            for (int j = 0; j < N; j++) {
                System.out.print(map180[i][j]);
            }
            System.out.print(" ");
            for (int j = 0; j < N; j++) {
                System.out.print(map270[i][j]);
            }
            System.out.println();
        }
    }
    private static int[][] rotate90(int[][] map) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[j][N-1-i] = map[i][j];
            }
        }
        return tmp;
    }
}
