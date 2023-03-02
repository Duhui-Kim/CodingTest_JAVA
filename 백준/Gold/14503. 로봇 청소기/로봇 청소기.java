import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 세로의 크기 N과 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 좌표 x,y 와 방향 dir
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        // N * M 크기의 map을 생성.
        int[][] map = new int[N][M];

        // map에 값을 입력받는다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 청소한 구역 cnt 진행
        int area = 0;

        // 시작 구역이 0일 경우 청소하고 시작
        if(map[x][y] == 0) {
            map[x][y] = 2;
            area++;
        }

        // 델타탐색을 위한 배열 (0 북, 1 동, 2 남, 3 서)
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 로봇청소기 작동 시작
        loop:
        while(true) {
            // 청소구역 탐색
            for (int i = 1; i <= 4; i++) {
                // 반시계 방향 탐색을 위해 현재 좌표부터 왼쪽으로 넘어가게 만듦
                int nDir = (dir - i + 4) % 4;
                int nx = x + dx[nDir];
                int ny = y + dy[nDir];

                // 범위 밖이면 다음 방향 탐색
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 범위 안일 때 nx, ny가 청소되지 않은 구역이라면 청소하고 그 구역으로 이동
                // 다음 구역에서 또 청소가능 여부부터 확인하기 위해 while문으로 돌아감
                if(map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    dir = nDir;
                    map[nx][ny] = 2;
                    area++;
                    continue loop;
                }
            }

            // 여기까지 왔다는건 청소할 구역이 없었다는 뜻이므로 뒤로 이동
            int nx = x - dx[dir];
            int ny = y - dy[dir];
            
            // 뒤로 이동하는 좌표가 범위 밖이면 종료 / 벽이어도 종료
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 1) break loop;
            
            // 그렇지 않다면 뒤로 이동
            x = nx;
            y = ny;
        }
        System.out.println(area);
    }
}