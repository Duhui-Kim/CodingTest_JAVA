import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int N, M, K;
    private static int[][] map;
    private static int[][] god;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 주어진 문자열 개수
        K = Integer.parseInt(st.nextToken());

        // map을 int로 바꿔서 저장
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - 96;
            }
        }

        // 0번 값은 문자열을 숫자로 바꾼 값, 1번은 등장 횟수
        god = new int[2][K];

        // 0번 값에 숫자로 변경하여 저장
        int length = 0;
        for (int i = 0; i < K; i++) {
            char[] input = br.readLine().toCharArray();
            length = Math.max(length, input.length);
            for (int j = 0; j < input.length; j++) {
                god[0][i] = god[0][i] * 100 + (input[j] - 96);
            }
        }

        // 문자 하나를 고른 후 백트래킹을 한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                backTracking(map[i][j], i, j, 1);
            }
        }

        // god[1][i]에 들어있는 값이 god[0][i] 문자열이 등장한 횟수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            sb.append(god[1][i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void backTracking(int num, int x, int y, int k) {
        // k가 5를 넘어가는 경우엔 되돌아가기
        if (k == 5) {
            return;
        }

        // 배열에 있는 값과 비교하기
        for (int i = 0; i < K; i++) {
            if (god[0][i] == num) {
                god[1][i]++;
            }
        }

        // 8방 탐색을 통해 문자 하나를 정해서 넘김
        for (int i = 0; i < 8; i++) {
            int nx = (x + dx[i] + N) % N;
            int ny = (y + dy[i] + M) % M;

            backTracking(num * 100 + map[nx][ny], nx, ny, k+1);
        }
    }
}