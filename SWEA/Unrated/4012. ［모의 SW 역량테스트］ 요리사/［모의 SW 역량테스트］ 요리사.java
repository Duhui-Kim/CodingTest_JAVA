import java.util.Scanner;

public class Solution {
    private static int min;
    private static int[][] map;
    private static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = sc.nextInt();

        for (int tc = 1; tc <= testCase; tc++) {

            // 요리 재료 수 N과 시너지를 입력받는다.
            N = sc.nextInt();
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 요리는 N/2개씩 나뉘므로, N개 중 N/2개를 선택하기 위해 boolean 배열을 만든다.
            boolean[] check = new boolean[N];

            // 정답을 저장할 값을 max로 두고 시작한다.
            min = Integer.MAX_VALUE;

            // 재귀로 구해보기
            powerSet(check, -1, 0);

            sb.append(String.format("#%d %d\n", tc, min));
        }
        System.out.println(sb);
    }

    private static void powerSet(boolean[] check, int sel, int idx) {
        // idx가 N의 절반 개수를 선택하면 진행
        if(idx == N/2) {
            int A = 0;
            int B = 0;
            
            // 두 값의 차이의 최솟값 저장하기
            for (int i = 0; i < N-1; i++) {
                if(check[i]) continue;
                for (int j = i+1; j < N; j++) {
                    if (check[j]) continue;
                    A += map[i][j] + map[j][i];
                }
            }

            for (int i = 0; i < N-1; i++) {
                if(!check[i]) continue;
                for (int j = i+1; j < N; j++) {
                    if (!check[j]) continue;
                    B += map[i][j] + map[j][i];
                }
            }

            min = Math.min(min, Math.abs(A-B));
            return;
        }

        // N/2개 선택하기
        for (int i = sel+1; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                powerSet(check, i,idx + 1);
                check[i] = false;
            }
        }

    }
    
}