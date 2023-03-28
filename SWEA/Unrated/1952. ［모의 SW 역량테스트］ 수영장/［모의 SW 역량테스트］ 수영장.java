import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int three = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            // 요금 저장용 배열
            int[] fee = new int[13];

            // 1개월부터 12개월 요금 채우기
            st = new StringTokenizer(sc.nextLine());
            for (int i = 1; i < 13; i++) {
                int num = Integer.parseInt(st.nextToken());
                // 월별 요금 비교
                if(num * day > month) {
                    fee[i] = month + fee[i-1];
                } else {
                    fee[i] = num * day + fee[i-1];
                }
                // 3개월 요금과도 비교
                if(i-3 >= 0) {
                    if (fee[i] > three + fee[i-3]) {
                        fee[i] = three + fee[i-3];
                    }
                } else {
                    if (fee[i] > three) {
                        fee[i] = three;
                    }
                }

            }

            // 정답 저장용
            int answer = 0;

            // 12개월까지 합산된 요금보다 연요금이 작다면 연간 요금으로 교체
            if (fee[12] > year) {
                answer = year;
            } else {
                answer = fee[12];
            }
            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }
}