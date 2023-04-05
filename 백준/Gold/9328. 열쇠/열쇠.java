import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int H;
    private static int W;
    private static char[][] map;
    private static int[][][] check;
    private static Queue<Pair> queue;
    private static int paper;
    public static void main(String[] args) throws IOException {
        // 좌표와 열쇠와 열쇠의 개수를 가지고 다님.
        // 갖고 있는 열쇠에 따라 다른 map 사용
        // 통로에 도착하면 다른 모든 통로의 좌표를 queue에 넣어줘야됌
        // -> 통로로 쓸 수 있는 경우는 . 이거나 갖고 있는 열쇠로 열 수 있거나

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        // testCase만큼 반복
        for (int tc = 0; tc < testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // map의 높이와 너비
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // 맵 입력받기
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 열쇠를 먹을 때마다 다른 맵 이용
            check = new int[27][H][W];

            Pair start = new Pair();

            // 처음 갖고 시작하는 키 받아오기
            String key = br.readLine();

            // key가 0이 아닌 경우 키 입력해두기
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) {
                    start.key = start.key | 1 << key.charAt(i) - 'a';
                    start.keyCnt++;
                }
            }
            // BFS 진행용 queue와 Delta
            queue = new LinkedList<>();
            int[] dx = {0, 0, -1, 1};
            int[] dy = {1, -1, 0, 0};

            // 훔친 종이의 수
            paper = 0;

            // 가장자리부터 진행.
            outSide(start);

            // BFS 진행
            while (!queue.isEmpty()) {
                Pair pair = queue.poll();

                // 사방탐색
                for (int i = 0; i < 4; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];

                    // 범위 밖이면 테두리 돌기
                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                        outSide(pair);
                        continue;
                    }

                    // 벽이면 지나감
                    if (map[nx][ny] == '*') continue;
                    // 체크되어있으면 지나감
                    if ((check[pair.keyCnt][nx][ny] & pair.key) == pair.key) continue;

                    // 문서를 만나면 paperCnt 올리고 체크하고 queue에 넣기
                    // . 인 경우는 그냥 체크하고 queue에 넣기
                    if (map[nx][ny] == '$' || map[nx][ny] == '.') {
                        if(map[nx][ny] == '$') {
                            map[nx][ny] = '.';
                            paper++;
                        }
                        check[pair.keyCnt][nx][ny] = check[pair.keyCnt][nx][ny] | pair.key;
                        queue.offer(new Pair(nx, ny, pair.key, pair.keyCnt));
                    }
                    // 키를 만나면 키 넣어주기
                    else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                        int tmpKey = pair.key;
                        int tmpKeyCnt = pair.keyCnt;

                        // 이미 있는 키가 아니라면 넣기
                        if ((tmpKey & 1 << (map[nx][ny] - 'a')) == 0) {
                            tmpKey = tmpKey | 1 << (map[nx][ny] - 'a');
                            tmpKeyCnt++;
                        }

                        if ((check[tmpKeyCnt][nx][ny] & tmpKey) == tmpKey) continue;

                        check[tmpKeyCnt][nx][ny] = check[tmpKeyCnt][nx][ny] | tmpKey;
                        queue.offer(new Pair(nx, ny, tmpKey, tmpKeyCnt));
                    }
                    // 문을 만나면
                    else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                        // 키가 있으면 갈 수 있음
                        if ((pair.key & 1 << (map[nx][ny] - 'A')) > 0) {
                            check[pair.keyCnt][nx][ny] = check[pair.keyCnt][nx][ny] | pair.key;
                            queue.offer(new Pair(nx, ny, pair.key, pair.keyCnt));
                        }
                    }
                }
            }
            sb.append(paper).append("\n");
        }
        System.out.println(sb);
    }

    private static void outSide(Pair pair) {
        // 가장자리 탐색 (행)
        for (int i = 0; i < W; i++) {
            // 방문표시가 안되어있을 경우
            if ((check[pair.keyCnt][0][i] & pair.key) != pair.key) {
                if (map[0][i] == '*') continue;

                // 가장자리가 . 이거나 키가 있는 경우 또는 문서인 경우
                if (map[0][i] == '$' || map[0][i] == '.' || (1 << (map[0][i] - 'A') & pair.key) > 0) {
                    if (map[0][i] == '$') {
                        map[0][i] = '.';
                        paper++;
                    }
                    check[pair.keyCnt][0][i] = check[pair.keyCnt][0][i] | pair.key;
                    queue.offer(new Pair(0, i, pair.key, pair.keyCnt));
                }
                // 가장자리가 키인 경우
                else if (map[0][i] >= 'a' && map[0][i] <= 'z') {

                    int tmpKey = pair.key;
                    int tmpKeyCnt = pair.keyCnt;

                    // 이미 있는 키가 아니라면 넣기
                    if ((tmpKey & 1 << (map[0][i] - 'a')) == 0) {
                        tmpKey = tmpKey | 1 << (map[0][i] - 'a');
                        tmpKeyCnt++;
                    }
                    if ((check[tmpKeyCnt][0][i] & tmpKey) == tmpKey) continue;

                    check[tmpKeyCnt][0][i] = check[tmpKeyCnt][0][i] | tmpKey;
                    queue.offer(new Pair(0, i, tmpKey, tmpKeyCnt));

                }
            }
        }
        // 가장자리 탐색 (행)
        for (int i = 0; i < W; i++) {
            // 방문표시가 안되어있을 경우
            if ((check[pair.keyCnt][H-1][i] & pair.key) != pair.key) {
                if (map[H-1][i] == '*') continue;

                // 가장자리가 . 이거나 키가 있는 경우 또는 문서인 경우
                if (map[H-1][i] == '$' || map[H-1][i] == '.' || (1 << (map[H-1][i] - 'A') & pair.key) > 0) {
                    if (map[H-1][i] == '$') {
                        map[H-1][i] = '.';
                        paper++;
                    }
                    check[pair.keyCnt][H-1][i] = check[pair.keyCnt][H-1][i] | pair.key;
                    queue.offer(new Pair(H-1, i, pair.key, pair.keyCnt));
                }
                // 가장자리가 키인 경우
                else if (map[H-1][i] >= 'a' && map[H-1][i] <= 'z') {

                    int tmpKey = pair.key;
                    int tmpKeyCnt = pair.keyCnt;

                    // 이미 있는 키가 아니라면 넣기
                    if ((tmpKey & 1 << (map[H-1][i] - 'a')) == 0) {
                        tmpKey = tmpKey | 1 << (map[H-1][i] - 'a');
                        tmpKeyCnt++;
                    }
                    if ((check[tmpKeyCnt][H-1][i] & tmpKey) == tmpKey) continue;

                    check[tmpKeyCnt][H-1][i] = check[tmpKeyCnt][H-1][i] | tmpKey;
                    queue.offer(new Pair(H-1, i, tmpKey, tmpKeyCnt));

                }
            }
        }

        // 가장자리 탐색 (열)
        for (int i = 1; i < H-1; i++) {
            // 방문표시가 안되어있을 경우
            if ((check[pair.keyCnt][i][0] & pair.key) != pair.key) {
                if (map[i][0] == '*') continue;

                // 가장자리가 . 이거나 키가 있는 경우 또는 문서인 경우
                if (map[i][0] == '$' || map[i][0] == '.' || (1 << (map[i][0] - 'A') & pair.key) > 0) {
                    if (map[i][0] == '$') {
                        map[i][0] = '.';
                        paper++;
                    }
                    check[pair.keyCnt][i][0] = check[pair.keyCnt][i][0] | pair.key;
                    queue.offer(new Pair(i, 0, pair.key, pair.keyCnt));
                }
                // 가장자리가 키인 경우
                else if (map[i][0] >= 'a' && map[i][0] <= 'z') {

                    int tmpKey = pair.key;
                    int tmpKeyCnt = pair.keyCnt;

                    // 이미 있는 키가 아니라면 넣기
                    if ((tmpKey & 1 << (map[i][0] - 'a')) == 0) {
                        tmpKey = tmpKey | 1 << (map[i][0] - 'a');
                        tmpKeyCnt++;
                    }
                    if ((check[tmpKeyCnt][i][0] & tmpKey) == tmpKey) continue;

                    check[tmpKeyCnt][i][0] = check[tmpKeyCnt][i][0] | tmpKey;
                    queue.offer(new Pair(i, 0, tmpKey, tmpKeyCnt));

                }
            }
        }
        // 가장자리 탐색 (열)
        for (int i = 1; i < H-1; i++) {
            // 방문표시가 안되어있을 경우
            if ((check[pair.keyCnt][i][W-1] & pair.key) != pair.key) {
                if (map[i][W-1] == '*') continue;

                // 가장자리가 . 이거나 키가 있는 경우 또는 문서인 경우
                if (map[i][W-1] == '$' || map[i][W-1] == '.' || (1 << (map[i][W-1] - 'A') & pair.key) > 0) {
                    if (map[i][W-1] == '$') {
                        map[i][W-1] = '.';
                        paper++;
                    }
                    check[pair.keyCnt][i][W-1] = check[pair.keyCnt][i][W-1] | pair.key;
                    queue.offer(new Pair(i, W-1, pair.key, pair.keyCnt));
                }
                // 가장자리가 키인 경우
                else if (map[i][W-1] >= 'a' && map[i][W-1] <= 'z') {

                    int tmpKey = pair.key;
                    int tmpKeyCnt = pair.keyCnt;

                    // 이미 있는 키가 아니라면 넣기
                    if ((tmpKey & 1 << (map[i][W-1] - 'a')) == 0) {
                        tmpKey = tmpKey | 1 << (map[i][W-1] - 'a');
                        tmpKeyCnt++;
                    }
                    if ((check[tmpKeyCnt][i][W-1] & tmpKey) == tmpKey) continue;

                    check[tmpKeyCnt][i][W-1] = check[tmpKeyCnt][i][W-1] | tmpKey;
                    queue.offer(new Pair(i, W-1, tmpKey, tmpKeyCnt));

                }
            }
        }

    }

    // 좌표와 키, 키의 개수를 갖고 다닐 class
    static class Pair {
        int x, y, key, keyCnt;

        private Pair() {
            this.x = 0;
            this.y = 0;
            this.key = 1 << 27;
            this.keyCnt = 0;
        }
        private Pair(int x, int y, int key, int keyCnt) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.keyCnt = keyCnt;
        }
    }
}