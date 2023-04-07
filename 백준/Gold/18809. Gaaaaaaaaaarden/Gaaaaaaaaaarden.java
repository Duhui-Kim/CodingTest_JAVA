import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    /*
    1. 맵을 입력받으며 황토칸 수 세고 좌표 넣어둠
    2. 황토칸 크기의 배열 생성하고 백트래킹 -> 빨간배양액, 초록배양액, 안 넣기 중 하나 선택
     남은 배양액 > 황토칸 -> return
    3. 배양액 위치 모두 선정되면, 초록배양액 queue, 빨간배양액 queue 넣고 BFS 진행
    4. 한 사이클씩 진행하며, 서로 다른 배양액끼리 만나면 꽃 피우기
    5. queue에서 poll한 좌표에 꽃이 피어있다면 아무것도 안하고 지나가기
    6. 꽃의 수 저장
     */

    private static int N;
    private static int M;
    private static int G;
    private static int R;
    private static boolean[][] map;
    private static List<Pair> seed = new ArrayList<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int answer = 0;
    private static int[] test;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵의 행과 열의 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배양액 숫자
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                
                // 호수면 map true로 바꾸고, 2이면 심을 수 있는 땅이므로 seed에 넣기
                if (num == 0) map[i][j] = true;
                if (num == 2) seed.add(new Pair(i, j));
            }
        }
        // 씨앗 심을 수 있는 땅의 크기
        int size = seed.size();
        
        // 배양액 심을 땅을 고를 배열
        int[] select = new int[size];

        // 어떤 배양액을 쓸 지 선택
        backTracking(select, G, R, 0, size);

        // 최댓값 출력
        System.out.println(answer);
    }

    private static void backTracking(int[] select, int g, int r, int k, int size) {
        // 남은 배양액보다 남은 땅이 적으면 return
        if (g + r > size - k) return;

        // size만큼 골라질 경우
        if (k == size && g == 0 && r == 0) {
            // queue 선언
            Queue<Pair> green = new LinkedList<>();
            Queue<Pair> red = new LinkedList<>();
            int[][] check = new int[N][M];

            test = select.clone();

            for (int i = 0; i < size; i++) {
                // 0이면 선택하지 않은 경우이므로 지나감
                if (select[i] == 0) continue;

                // 1이면 green / 2면 red
                if (select[i] == 1) {
                    green.offer(seed.get(i));
                    check[seed.get(i).x][seed.get(i).y] = 1;
                } else {
                    red.offer(seed.get(i));
                    check[seed.get(i).x][seed.get(i).y] = 2;
                }
            }
            // BFS 진행
            BFS(check, green, red);
            return;
        }
        
        // 초록색 고른 경우
        if (g > 0) {
            select[k] = 1;
            backTracking(select, g-1, r, k+1, size);
        }
        // 빨간색 고른 경우
        if (r > 0) {
            select[k] = 2;
            backTracking(select, g, r-1, k+1, size);
        }
        // 안 고른 경우
        select[k] = 0;
        backTracking(select, g, r, k+1, size);
    }

    private static void BFS(int[][] check, Queue<Pair> green, Queue<Pair> red) {
        int cnt = 0;
        while (!green.isEmpty() || !red.isEmpty()) {
            int size = green.size();
            for (int i = 0; i < size; i++) {
                Pair pair = green.poll();

                // 꽃이 피었다면 진행 X
                if (check[pair.x][pair.y] == 3) continue;

                // 동일한 시간이 아니면 바꿔버림
                check[pair.x][pair.y] = 5;

                // 사방탐색 진행
                for (int j = 0; j < 4; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];

                    // 범위 밖이면 지나감
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    // 호수이면 지나감
                    if (map[nx][ny]) continue;

                    // 그냥 땅일 경우 배양액 뿌림
                    if (check[nx][ny] == 0){
                        check[nx][ny] = 1;
                        green.offer(new Pair(nx, ny));
                    }
                }
            }
            size = red.size();
            for (int i = 0; i < size; i++) {
                Pair pair = red.poll();

                // 꽃이 피었다면 진행 X
                if (check[pair.x][pair.y] == 3) continue;

                // 사방탐색 진행
                for (int j = 0; j < 4; j++) {
                    int nx = pair.x + dx[j];
                    int ny = pair.y + dy[j];

                    // 범위 밖이면 지나감
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    // 호수이면 지나감
                    if (map[nx][ny]) continue;
                    // 이미 방문했으면 지나감
                    if (check[nx][ny] == 2) continue;
                    if (check[nx][ny] == 3) continue;

                    // 초록 배양액이 있을 경우 꽃 피워주기
                    if (check[nx][ny] == 1) {
                        check[nx][ny] = 3;
                        cnt++;
                    }
                    // 그냥 땅일 경우 배양액 뿌리기
                    else if (check[nx][ny] == 0){
                        check[nx][ny] = 2;
                        red.offer(new Pair(nx, ny));
                    }
                }
            }
        }
        answer = Math.max(cnt, answer);
    }

    static class Pair {
        int x;
        int y;
        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}