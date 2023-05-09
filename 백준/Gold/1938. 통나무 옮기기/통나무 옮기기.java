import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static class Pair {
        int x, y, dir, dist;
        private Pair(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }
    private static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static char[][] map;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // map 배열
        map = new char[N][N];

        // 방문체크용 숫자배열
        int[][] check = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<Pair> queue = new LinkedList<>();

        // 시작점 + 도착점 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    // B가 세로로 놓여있을 경우 중심점 + 방향 queue에 넣기
                    if(i + dx[1] >= 0 && i + dx[5] < N) {
                        if(map[i+dx[1]][j] == 'B' && map[i+dx[5]][j] == 'B') {
                            queue.offer(new Pair(i, j, 0, 0));
                            map[i+dx[1]][j] = '0';
                            map[i+dx[5]][j] = '0';
                            map[i][j] = '0';

                            check[i][j] = 1;
                        }
                    }
                    // B가 가로로 놓여있을 경우 중심점 + 방향 queue에 넣기
                    if (j + dy[7] >= 0 && j + dy[3] < N) {
                        if(map[i][j+dy[3]] == 'B' && map[i][j+dy[7]] == 'B') {
                            queue.offer(new Pair(i, j, 1, 0));
                            map[i][j+dy[3]] = '0';
                            map[i][j+dy[7]] = '0';
                            map[i][j] = '0';

                            check[i][j] = 1 << 1;
                        }
                    }

                } else if (map[i][j] == 'E') {
                    // E가 세로로 놓여있을 경우 중심점 + 방향 map에 입력
                    if(i + dx[1] >= 0 && i + dx[5] < N) {
                        if(map[i+dx[1]][j] == 'E' && map[i+dx[5]][j] == 'E') {
                            map[i+dx[1]][j] = '0';
                            map[i+dx[5]][j] = '0';
                            map[i][j] = 'C';
                        }
                    }
                    // E가 가로로 놓여있을 경우 중심점 + 방향 map에 입력
                    if (j + dy[7] >= 0 && j + dy[3] < N) {
                        if(map[i][j+dy[3]] == 'E' && map[i][j+dy[7]] == 'E') {
                            map[i][j+dy[3]] = '0';
                            map[i][j+dy[7]] = '0';
                            map[i][j] = 'R';
                        }
                    }
                }
            }
        }
        int answer = 0;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (map[p.x][p.y] == 'C' && p.dir == 0) {
                answer = p.dist;
                break;
            } else if (map[p.x][p.y] == 'R' && p.dir == 1) {
                answer = p.dist;
                break;
            }

            // U, D, L, R, rotate 방향에 대해서 체크
            // 0 1 2
            // 7 X 3
            // 6 5 4
            for (int i = 0; i < 5; i++) {
                Pair tmp = new Pair(p.x, p.y, p.dir, p.dist+1);

                // 이동불가능한 방향이면 지나가기
                if (!enableMove(tmp, i)) continue;
                // 방문체크 되어있다면 진행 X
                if ((check[tmp.x][tmp.y] & 1 << tmp.dir) != 0) continue;
                // 방문체크
                check[tmp.x][tmp.y] = check[tmp.x][tmp.y] | 1 << tmp.dir;

                queue.offer(tmp);
            }
        }
        System.out.println(answer);
    }

    // 해당 방향으로 움직일 수 있는지 체크
    private static boolean enableMove(Pair p, int i) {
        if (p.dir == 0) {
            // UP
            if (i == 0) {
                if (p.x-2 < 0) return false;
                if (map[p.x-2][p.y] == '1') return false;
                p.x -= 1;
            }
            // DOWN
            else if (i == 1) {
                if (p.x+2 >= N) return false;
                if (map[p.x+2][p.y] == '1') return false;
                p.x += 1;
            }
            // LEFT
            else if (i == 2) {
                if (p.x-1 < 0 || p.x+1 >= N || p.y-1 < 0) return false;
                if (map[p.x-1][p.y-1] == '1' || map[p.x][p.y-1] == '1' || map[p.x+1][p.y-1] == '1') return false;
                p.y -= 1;
            }
            // RIGHT
            else if (i == 3) {
                if (p.x-1 < 0 || p.x+1 >= N || p.y+1 >= N) return false;
                if (map[p.x-1][p.y+1] == '1' || map[p.x][p.y+1] == '1' || map[p.x+1][p.y+1] == '1') return false;
                p.y += 1;
            }
        } else {
            // UP
            if (i == 0) {
                if (p.y-1 < 0 || p.y+1 >= N || p.x-1 < 0) return false;
                if (map[p.x-1][p.y-1] == '1' || map[p.x-1][p.y] == '1' || map[p.x-1][p.y+1] == '1') return false;
                p.x -= 1;
            }
            // DOWN
            else if (i == 1) {
                if (p.y-1 < 0 || p.y+1 >= N || p.x+1 >= N) return false;
                if (map[p.x+1][p.y-1] == '1' || map[p.x+1][p.y] == '1' || map[p.x+1][p.y+1] == '1') return false;
                p.x += 1;
            }
            // LEFT
            else if (i == 2) {
                if (p.y-2 < 0) return false;
                if (map[p.x][p.y-2] == '1') return false;
                p.y -= 1;
            }
            // RIGHT
            else if (i == 3) {
                if (p.y+2 >= N) return false;
                if (map[p.x][p.y+2] == '1') return false;
                p.y += 1;
            }
        }
        // ROTATE
        if (i == 4) {
            for (int j = 0; j < 8; j++) {
                if (p.x + dx[j] < 0 || p.x + dx[j] >= N || p.y + dy[j] < 0 || p.y + dy[j] >= N) return false;
                if (map[p.x + dx[j]][p.y + dy[j]] == '1') return false;
                if (j == 7) p.dir = (p.dir + 1) % 2;
            }
        }

        return true;
    }
}