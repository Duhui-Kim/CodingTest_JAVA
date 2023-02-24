import com.sun.org.apache.xpath.internal.operations.Neg;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 테스트케이스의 수만큼 반복 진행
        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {

            // N을 입력받고 N 크기의 boolean 배열을 만들었다.
            int N = sc.nextInt();

            boolean[][] map = new boolean[N][N];

            // 입력받으면서 W가 있으면 max에 1을 할당한다.
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(sc.next().contains("W")) {
                        map[i][j] = true;
                        max = 1;
                    }
                }
            }

            // true를 만날 경우 주변의 true의 개수를 세서 max와 비교한다.
            int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
            int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = 0;
                    // 맵에서 W를 만나면 탐색을 시작한다.
                    if(map[i][j]) {
                        for (int k = 0; k < 8; k++) {
                            int x = i + dx[k];
                            int y = j + dy[k];
                            if (x >= 0 && y >= 0 && x < N && y < N) {
                                if(map[x][y]) cnt++;
                            }
                        }
                        // max값과 비교하여 넣어준다.
                        max = max < cnt? cnt: max;
                    }
                }
            }
            System.out.printf("#%d %d\n", t, max);
        }
    }
}
