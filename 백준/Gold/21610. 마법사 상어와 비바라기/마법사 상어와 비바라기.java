import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    1. map을 입력받고 반복 횟수만큼 반복
    2. boolean 배열 선언 (구름 사라진 자리 체크용)
    3. 배열을 돌며 구름 사라진 자리가 아닌 곳에서 2 이상인 좌표들을 queue에 넣으며 2씩 빼줌
    4. check 배열 초기화
    5. queue의 size만큼 queue에서 꺼내면서 좌표 이동 후 해당 좌표의 물 1씩 올리고 다시 queue의 뒤에 넣어줌
    6. queue에서 하나씩 꺼내며 대각선에 물이 있으면 cnt++
    7. check 진행
    8. 종료 후 남은 물의 양 출력
     */
    static class Pair {
        int x;
        int y;
        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int[][] map;
    private static boolean[][] check;
    private static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵의 크기 N과 입력 숫자 M 받기
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 물동이 배열과 구름 사라짐 체크 배열, queue 선언
        map = new int[N][N];
        check = new boolean[N][N];
        Queue<Pair> queue = new LinkedList<>();

        // map 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // queue에 초기값 넣어주기
        queue.offer(new Pair(N-1, 0));
        queue.offer(new Pair(N-1, 1));
        queue.offer(new Pair(N-2, 0));
        queue.offer(new Pair(N-2, 1));

        int answer = 0;

        // M번 반복
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // 이동 방향과 거리 입력받기
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            // 구름 이동
            moveC(queue, d, s);

            // 물 증가시키기
            increaseC(queue);

            // 새로운 구름 만들기
            makeC(queue);
            check = new boolean[N][N];

            // 마지막이면 물 세주기
            if (i == M-1) {
                answer = countW();
            }

        }
        System.out.println(answer);
    }

    private static int countW() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += map[i][j];
            }
        }
        return cnt;
    }

    private static void makeC(Queue<Pair> queue) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 구름 사라진 곳이면 패스
                if (check[i][j]) continue;
                
                // 2 이상인 경우 구름 만들어서 queue에 넣기
                if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    queue.offer(new Pair(i, j));
                }
            }
        }
    }

    private static void increaseC(Queue<Pair> queue) {
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            // 대각선 방향만 체크
            for (int i = 2; i <= 8; i += 2) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                // 범위 밖이면 지나감
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                // 범위 안일 경우 대각선에 물이 있으면 증가
                if (map[nx][ny] != 0) {
                    map[pair.x][pair.y]++;
                }
            }
        }
    }

    private static void moveC(Queue<Pair> queue, int d, int s) {
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            Pair pair = queue.poll();

            // 좌표가 음수가 되지 않도록 보정
            pair.x = (pair.x + dx[d] * s + s * N) % N;
            pair.y = (pair.y + dy[d] * s + s * N) % N;

            // 물 올려주고 체크하기
            map[pair.x][pair.y]++;
            check[pair.x][pair.y] = true;
            
            // 이따 대각선 탐색 해야하므로 queue에 다시 넣기
            queue.offer(pair);
        }
    }
}