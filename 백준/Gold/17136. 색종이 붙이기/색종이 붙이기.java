import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int[] paper = {0, 5, 5, 5, 5, 5};
    private static int min = Integer.MAX_VALUE;
    private static int cnt;
    private static int N = 10;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 종이 칸은 항상 10 * 10
        map = new int[N][N];

        // 몇 칸을 바꿔야하는지 체크용
        cnt = 0;

        // map을 입력받으며 1의 개수를 세준다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                cnt += num;
            }
        }
        // 백트래킹 진행
        backTracking(0, 0, 0, 0);

        // 초기값과 같으면 불가능하므로 -1 반환
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    /**
     * 이전에 진행한 좌표를 받고, 선택한 색종이 수 k와 몇 칸을 바꿨는지 change를 전달
     * @param x
     * @param y
     * @param k
     * @param change
     */
    private static void backTracking(int x, int y, int k, int change) {
        if (k >= min) return;

        // cnt만큼 바꿨을 때 색종이 수 세기
        if (change == cnt) {
            min = Math.min(min, k);
            return;
        }
        // y == 10이면 다음 행으로
        if (y == 10) {
            x++;
            y = 0;
        }
        // x가 10이면 돌아가기
        if (x == 10) {
            return;
        }

        // map[x][y]가 0이면 다음 열로
        if (map[x][y] == 0) {
            backTracking(x, y+1, k, change);
            return;
        }

        // 1 ~ 5 종이를 사용가능한지 체크
        for (int l = 5; l >= 1; l--) {
            // 종이가 없으면 지나감
            if (paper[l] <= 0) continue;

            // 색종이 붙이기
            if (check(x, y, l)) {
                change += coloring(x, y, l, 0);
                paper[l]--;
                backTracking(x, y+1, k+1, change);
                change -= coloring(x, y, l, 1);
                paper[l]++;
            }
        }

    }

    private static boolean check(int x, int y, int size) {
        // 범위 밖으로 벗어나면 false 반환
        if (x + size > N || y + size > N) return false;
        
        // 해당 사이즈가 모두 1로 되어있는지 체크
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if(map[i][j] != 1) {
                    return false;
                }
            }
        }
        // 위를 모두 통과했다면 붙일 수 있으므로 true
        return true;
    }

    // 색종이 칠하기
    private static int coloring(int x, int y, int size, int num) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = num;
            }
        }
        return size * size;
    }
}