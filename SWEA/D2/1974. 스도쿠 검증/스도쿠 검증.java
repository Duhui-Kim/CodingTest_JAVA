import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {

            // 스도쿠배열을 만들 map과 가로 체크를 위한 check 배열을 선언
            int[][] map = new int[9][9];
            int[] check = new int[9];
            boolean flag = true;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int num = sc.nextInt();
                    map[i][j] = num;

                    // 가로 체크 i줄마다 1~9는 i개씩 있기 때문에
                    if(check[num-1] != i) {
                        flag = false;
                    } else {
                        check[num-1]++;
                    }
                }
            }

            // 세로 체크 (가로체크 통과 시에만)
            if(flag) {
                for (int i = 0; i < 9; i++) {
                    flag = checkCol(map, i);
                    if(!flag) {
                        break;
                    }
                }
            }

            // 3*3 체크
            if(flag) {
                loop:
                for (int i = 0; i < 9; i += 3) {
                    for (int j = 0; j < 9; j += 3) {
                        flag = checkSquare(map, i, j);
                        if(!flag) {
                            break loop;
                        }
                    }
                }
            }
            if (flag) {
                System.out.printf("#%d %d\n", t, 1);
            } else {
                System.out.printf("#%d %d\n", t, 0);
            }
        }
    }
    // 세로 체크 method
    // 10칸짜리 boolean 배열을 만들어서 true가 입력된 값이 있으면 중복이므로 false 반환
    private static boolean checkCol(int[][] map, int col) {
        boolean[] check = new boolean[10];

        for (int i = 0; i < 9; i++) {
            if(!check[map[i][col]]) {
                check[map[i][col]] = true;
            } else {
                return false;
            }
        }
        return true;
    }
    // 사각형 체크 method (세로 체크와 동일)
    // 10칸짜리 boolean 배열을 만들어서 true가 입력된 값이 있으면 중복이므로 false 반환
    private static boolean checkSquare(int[][] map, int row, int col) {
        boolean[] check = new boolean[10];

        for (int i = row; i < row+3; i++) {
            for (int j = col; j < col+3; j++) {
                if(!check[map[i][j]]) {
                    check[map[i][j]] = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
