import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 밑면이 idx일 때 value는 윗면의 idx
        int[] upper = {5, 3, 4, 1, 2, 0};

        int[][] dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int i = 0; i < 6; i++) {
            // 초기 세팅
            // 맨 처음 주사위에 따라 달라짐

            int sumOfDice = 0;
            
            // A는 밑면, B는 윗면
            int A = dice[0][i];
            int B = dice[0][upper[i]];

            // A, B가 5, 6일 때 최대의 옆면은 4
            // 위의 경우 제외했을 때 A나 B에 6이 있으면 최대는 5
            // 모두 아닐 경우 6
            if(A + B == 11) sumOfDice += 4;
            else if (A == 6 || B == 6) sumOfDice += 5;
            else sumOfDice += 6;

            for (int j = 1; j < N; j++) {

                // 밑면 윗면 찾기
                // 이전 주사위의 윗면과 같은 값을 찾으면 그것을 밑면으로 바꾸고, 윗면은 새롭게 추가
                for (int k = 0; k < 6; k++) {
                    if(dice[j][k] == B) {
                        A = B;
                        B = dice[j][upper[k]];
                        break;
                    }
                }
                // 또 주사위의 밑면과 윗면을 기준으로 옆면의 값을 더 해줌
                if(A + B == 11) sumOfDice += 4;
                else if (A == 6 || B == 6) sumOfDice += 5;
                else sumOfDice += 6;
            }

            max = Math.max(max, sumOfDice);
        }
        System.out.println(max);
    }
}