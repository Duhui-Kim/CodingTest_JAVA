import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        // 각 성 별로 BFS 진행하기 위해 queue 배열 생성
        Queue<int[]>[] queue = new LinkedList[P+1];
        int[] castle = new int[P+1];
        long[] repeat = new long[P+1];

        st = new StringTokenizer(br.readLine());

        // 각 idx별 반복횟수 입력받기
        for (int i = 1; i <= P; i++) {
            repeat[i] = Integer.parseInt(st.nextToken());
        }

        // queue 배열에 queue 넣어주기
        for (int i = 1; i <= P; i++) {
            Queue<int[]> nq = new LinkedList<>();
            queue[i] = nq;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // map 배열 생성
        int[][] map = new int[N][M];

        // map 입력받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '.') continue;
                if (s.charAt(j) == '#') {
                    map[i][j] = -1;
                } else {
                    // 숫자가 들어있으면 좌표와 성의 개수 저장
                    map[i][j] = s.charAt(j) - '0';
                    castle[map[i][j]]++;
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    queue[map[i][j]].offer(arr);
                }
            }
        }

        int cnt = 0;
        int idx = 1;

        loop:
        while (true) {
            int rpCnt = 0;
            while (!queue[idx].isEmpty()) {
                int size = queue[idx].size();

                for (int i = 0; i < size; i++) {
                    int[] xy = queue[idx].poll();
                    int x = xy[0];
                    int y = xy[1];

                    for (int j = 0; j < 4; j++) {
                        int nx = x + dx[j];
                        int ny = y + dy[j];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (map[nx][ny] == 0) {
                            int[] arr = new int[2];
                            arr[0] = nx;
                            arr[1] = ny;
                            map[nx][ny] = map[x][y];
                            queue[idx].offer(arr);
                            castle[map[nx][ny]]++;
                            cnt++;
                        }
                    }
                }
                rpCnt++;
                if (rpCnt == repeat[idx]) break;
            }
            idx++;
            if (idx > P) {
                if (cnt == 0) break;
                cnt = 0;
                idx = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= P; i++) {
            sb.append(castle[i]).append(" ");
        }
        System.out.println(sb);
    }
}