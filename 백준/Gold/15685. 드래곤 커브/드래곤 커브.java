import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[101][101];

        // 0 오른쪽, 1 위쪽, 2 왼쪽, 3 아래쪽
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        // 사각형 찾기위한 delta (본인, 오른쪽, 아래, 대각선 체크)
        int[] dr = {0, 1, 0, 1};
        int[] dc = {0, 0, 1, 1};

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            // 세대 크기에 맞는 size의 curve 배열 생성
            int size = 1;
            for (int j = 0; j < gen; j++) {
                size *= 2;
            }
            int[] curve = new int[size];
            curve[0] = dir;

            // curve 배열에 세대와 처음 dir에 맞는 방향 채우기 완료
            saveCurve(curve, dir, size, gen);

            // 시작점을 1로 놓고 curve배열에 맞게 회전하며 1 생성
            map[x][y] = 1;
            for (int j = 0; j < size; j++) {
                x += dx[curve[j]];
                y += dy[curve[j]];
                map[x][y] = 1;
            }
        }

        int answer = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                int cnt = 0;
                if(map[i][j] == 0) continue;

                // 사각형 탐색
                for (int k = 0; k < 4; k++) {
                    int r = i + dr[k];
                    int c = j + dc[k];

                    // 범위 벗어나면 어차피 사각형 못만듦
                    if(r < 0 || c < 0 || r > 100 || c > 100) break;

                    if(map[r][c] == 1) cnt++;
                }
                // 1의 개수 4개면 answer 증가
                if(cnt == 4) answer++;
            }
        }

        // answer 출력
        System.out.println(answer);
    }

    private static void saveCurve(int[] curve, int dir, int size, int gen) {
        if(gen == 0) return;
        if(gen > 1) saveCurve(curve, dir, size/2, gen-1);

        // 맨 앞에서부터 차례로 dir 채우기
        for (int i = size/2; i < size; i++) {
            curve[i] = (curve[size-1-i] + 1)%4;
        }
    }
}