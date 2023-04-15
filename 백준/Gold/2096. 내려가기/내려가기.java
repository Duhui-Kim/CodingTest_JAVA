import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 정답 저장용 인자
        int ansMin = Integer.MAX_VALUE;
        int ansMax = 0;

        // N이 1일 경우는 입력받은 수 중에서 최솟값과 최댓값 출력
        if (N == 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 3; i++) {
                int num = Integer.parseInt(st.nextToken());

                ansMax = Math.max(ansMax, num);
                ansMin = Math.min(ansMin, num);
            }

            System.out.println(ansMax + " " + ansMin);
            return;
        }

        // 0번엔 최솟값 저장, 1번엔 최댓값 저장
        int[][][] map = new int[2][N][3];

        // 초기값 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());

                map[0][i][j] = num;
                map[1][i][j] = num;
            }
        }
        
        // 반복문을 돌며 0번 idx에서는 자신의 왼쪽위, 위, 오른쪽위 중 최솟값을 자기자신과 더해줌
        // 1번 idx에서는 최댓값을 자기자신과 더해줌
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {

                // 최솟값 더해주기
                int min = map[0][i-1][j];

                if (j-1 < 0) {
                    min = Math.min(min, map[0][i-1][j+1]);
                } else if (j+1 >= 3) {
                    min = Math.min(min, map[0][i-1][j-1]);
                } else {
                    min = Math.min(min, map[0][i-1][j-1]);
                    min = Math.min(min, map[0][i-1][j+1]);
                }

                map[0][i][j] += min;

                // 최댓값 더해주기
                int max = map[1][i-1][j];

                if (j-1 < 0) {
                    max = Math.max(max, map[1][i-1][j+1]);
                } else if (j+1 >= 3) {
                    max = Math.max(max, map[1][i-1][j-1]);
                } else {
                    max = Math.max(max, map[1][i-1][j-1]);
                    max = Math.max(max, map[1][i-1][j+1]);
                }

                map[1][i][j] += max;

                // 마지막 줄에서 최솟값과 최댓값 저장
                if (i == N-1) {
                    ansMin = Math.min(ansMin, map[0][i][j]);
                    ansMax = Math.max(ansMax, map[1][i][j]);
                }
            }
        }
        // 결과 출력
        System.out.println(ansMax + " " + ansMin);
    }
}