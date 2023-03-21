import java.util.Scanner;

public class Solution {
    private static int max;
    private static int N;
    private static int L;
    private static int[][] burger;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = sc.nextInt();

        for (int tc = 1; tc <= testCase; tc++) {
            N = sc.nextInt();
            L = sc.nextInt();
            max = 0;

            // 0번 idx에 맛 저장, 1번 idx에 칼로리 저장
            burger = new int[2][N];
            for (int i = 0; i < N; i++) {
                burger[0][i] = sc.nextInt();
                burger[1][i] = sc.nextInt();
            }

            // 재귀적 방법으로 해보기
            powerSet(0, 0, 0);

            sb.append(String.format("#%d %d\n", tc, max));
        }
        System.out.println(sb);
    }

    private static void powerSet(int idx, int taste, int cal) {
        // 칼로리가 L을 벗어나지 않았을 경우에는 taste 중 최댓값을 저장한다.
        if(cal > L) return;
        max = Math.max(taste, max);

        if(idx >= N) return;

        // 선택 안한 경우 해당 idx를 건너뛰고 진행
        powerSet(idx+1, taste, cal);

        // 선택한 경우 해당 idx의 맛과 칼로리를 더해주고 진행
        powerSet(idx+1, taste+burger[0][idx], cal+burger[1][idx]);
    }
}