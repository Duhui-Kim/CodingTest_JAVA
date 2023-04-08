import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 입력용 map과 사각형 구하기 위한 check 배열
        char[][] map = new char[N+2][M+2];
        int[][] check = new int[N+2][M+2];

        // map 입력받기
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            // check에 초기 맵 입력받기
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '1') check[i][j] = 1;
            }
        }

        // 오른쪽, 아래, 오른쪽아래 탐색용 delta
        int[] dx = {1, 1, 0};
        int[] dy = {0, 1, 1};

        int max = 0;

        // 이중 반복문을 진행
        for (int i = N; i >= 0; i--) {
            for (int j = M; j >= 0; j--) {
                // 0 체크된 곳은 지나감
                if (check[i][j] == 0) continue;

                // 해당 좌표의 오른쪽, 오른쪽 아래, 아래쪽 중 가장 작은 수 + 1한 값을 대입
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    min = Math.min(check[nx][ny], min);
                }
                // 최댓값을 저장
                check[i][j] = min + 1;
                max = Math.max(max, check[i][j]);
            }
        }
        // 최댓값 * 최댓값이 사각형의 넓이
        System.out.println(max * max);
    }
}