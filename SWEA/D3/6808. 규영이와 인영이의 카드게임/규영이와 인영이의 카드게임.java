import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;

public class Solution {
    private static int win;
    private static int lose;
    private static int[] gyu;
    private static int[] inyoung;
    private static int[] select;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            win = 0;
            lose = 0;
            StringTokenizer st;

            // 인영이의 숫자 체크용 boolean
            boolean[] check = new boolean[19];

            // 규영이의 숫자
            gyu = new int[9];
            st = new StringTokenizer(sc.nextLine());
            for (int i = 0; i < 9; i++) {
                gyu[i] = Integer.parseInt(st.nextToken());
                check[gyu[i]] = true;
            }

            // 인영이의 숫자는 규영이가 고르지 않은 숫자
            inyoung = new int[9];

            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if(check[i]) continue;
                inyoung[idx++] = i;
            }

            // 인영이의 숫자 중 섞은 숫자 넣을 배열
            select = new int[9];

            backTracking(0, 0);

            sb.append(String.format("#%d %d %d\n", tc, win, lose));
        }
        System.out.println(sb);
    }

    private static void backTracking(int k, int num) {
        if(k == 9) {
            int pointG = 0;
            int pointI = 0;
            for (int i = 0; i < 9; i++) {
                // 규영이가 이기면 규영포인트 증가
                if(gyu[i] > select[i]) {
                    pointG += gyu[i] + select[i];
                }

                // 인영이가 이기면 인영포인트 증가
                else {
                    pointI += gyu[i] + select[i];
                }
            }
            // 포인트 비교해서 규영포인트가 크면 win 증가 / 그렇지 않으면 lose 증가
            if(pointG > pointI) win++;
            else lose++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if((num & 1<<i) != 0) continue;

            select[k] = inyoung[i];
            backTracking(k+1, num | 1<<i);
        }

    }
}