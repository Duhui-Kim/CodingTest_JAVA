import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int plus = 0;
    private static boolean[][] check;
    private static int max = Integer.MIN_VALUE;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차 배열을 순회하며 모든 값들을 넣어준다.
        // 값을 넣어주면서 check하고, 넣은 값의 좌표값을 백트래킹의 인자로 보내준다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check[i][j] = true;
                plus += map[i][j];
                backTracking(map, i, j, 1, N, M);
                check[i][j] = false;
                plus -= map[i][j];
            }
        }

        // 최댓값을 출력한다.
        System.out.println(max);
    }

    // 백트래킹 method
    // INPUT : map, 이전에 넣은 값 좌표, 몇 개를 넣었는지 체크할 k, 배열의 크기 N과 M
    private static void backTracking(int[][] map,int x, int y, int k, int N, int M) {
        // 4개가 선택되었으면 plus 값과 max 값을 비교하여 큰 값을 max에 넣는다.
        if(k == 4) {
            if(plus <= max) return;
            max = plus;
            return;
        }

        // 배열을 순회하는데, 이전에 넣었던 값 -1 ~ +1 까지만 순회한다. 3 * 3
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                
                // 순회하는 값이 배열을 벗어나면 지나감
                // 이미 넣었던 값이어도 지나감
                if(i < 0 || i >= N || j < 0 || j >= M) continue;
                if(check[i][j]) continue;

                // 사방탐색을 진행하며 체크된 값이 주변에 있으면 백트래킹을 하고 종료
                for (int l = 0; l < 4; l++) {
                    int nx = i + dx[l];
                    int ny = j + dy[l];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if(check[nx][ny]) {
                        check[i][j] = true;
                        plus += map[i][j];
                        backTracking(map, i, j, k + 1, N, M);
                        check[i][j] = false;
                        plus -= map[i][j];
                        break;
                    }
                }
            }
        }
    }
}