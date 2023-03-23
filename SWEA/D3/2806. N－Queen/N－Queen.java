import java.util.Scanner;

public class Solution {
    private static int N;
    private static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = sc.nextInt();

        for (int tc = 1; tc <= testCase; tc++) {
            // 숫자 N 입력받기
            N = sc.nextInt();

            // 퀸의 위치를 표시할 배열 생성 ( idx가 행, value가 열 )
            int[] queen = new int[N];
            cnt = 0;

            // 백트래킹 진행
            backTracking(0, queen);
            
            sb.append(String.format("#%d %d\n", tc, cnt));
        }
        System.out.println(sb);
    }

    private static void backTracking(int k, int[] queen) {
        // k가 N이면 N개를 모두 선택한 것이므로 cnt 증가
        if(k == N) {
            cnt++;
            return;
        }

        // 퀸 넣을 자리 찾기 (한 행 당 하나씩만 넣을 수 있음)
        for (int i = 0; i < N; i++) {
            if(check(i, k, queen)) {
                queen[k] = i;
                backTracking(k+1, queen);
            }
        }

    }

    private static boolean check(int col, int row, int[] queen) {
        // queen의 idx는 row이므로 row 이전까지만 체크
        // 같은 열에 있거나 대각선에 있는 퀸이 있으면 놓을 수 없음
        for (int j = 0; j < row; j++) {
            if(queen[j] == col || Math.abs(row - j) == Math.abs(col - queen[j])){
                return false;
            }
        }
        return true;
    }
}