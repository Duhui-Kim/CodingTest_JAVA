package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _2091_동전 {
    private static int max = 0;
    private static int[] answer = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int money = Integer.parseInt(st.nextToken());

        int[] coin = new int[4];
        for (int i = 0; i < 4; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        int[] price = {1, 5, 10, 25};

        int[][] dp = new int[money+1][5];

        if(money <= coin[0]) {
            System.out.println(money + " 0 0 0");
            return;
        }

        // 0 cent, 1 nickel, 2 dimer, 3 quarter
        // dp를 -1로 초기화시킨다. (0번과 money번은 0으로 놔둠)
        for (int i = 1; i < money; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 1번부터 money까지 반복문을 진행한다.
        for (int i = 1; i <= money; i++) {
            for (int j = 0; j < 4; j++) {
                // i번째 값에서 price만큼 뺀 값을 인덱스로 정한다.
                int x = i - price[j];

                // 이 때 x가 0보다 작거나, 해당 값의 동전 수가 -1개일 때는 지나간다. (-1이라는 것은 해당 금액은 진행 못했다는 뜻)
                if(x < 0) continue;
                if(dp[x][4] == -1) continue;

                // num보다 이전 동전의 수가 클 경우, 해당 동전의 개수가 최대치가 아닐 때 값을 더해준다.
                if(dp[i][4] < dp[x][4] + 1) {
                    if(dp[x][j] + 1 <= coin[j]) {
                        dp[i] = dp[x].clone();
                        dp[i][4]++;
                        dp[i][j]++;
                    }
                }
            }
        }
        // 결과를 출력 (money 이전 값이 다 -1이었으면 money는 0인 상태)
        for (int i = 0; i < 4; i++) {
            System.out.print(dp[money][i] + " ");
        }
    }
}
