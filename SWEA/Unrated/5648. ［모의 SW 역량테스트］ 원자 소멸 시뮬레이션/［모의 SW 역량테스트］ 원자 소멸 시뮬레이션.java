import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static class Pair {
        int x, y, k, dir;
        boolean exp;

        private Pair(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
            this.exp = false;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        // 0 : y증가, 1 : y감소, 2 : x감소, 3 : x증가
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=testCase; tc++) {
            int N = sc.nextInt();

            int size = 2200;

            int[][] map = new int[size+1][size+1];
            Pair[] atoms = new Pair[N+1];

            for(int i=1; i<=N; i++) {
                int x = sc.nextInt() + size/2;
                int y = sc.nextInt() + size/2;
                int dir = sc.nextInt();
                int k = sc.nextInt();
                atoms[i] = new Pair(x, y, dir, k);

                map[y][x] = i;
            }

            int answer = 0;
            int cnt = N;

            Queue<Integer> queue = new LinkedList<>();

            // 하나 남으면 폭발 불가능
            while(cnt > 1) {
                for(int i=1; i<=N; i++) {
                    // 이미 터진 원소들은 지나감
                    if(atoms[i].exp) continue;

                    // 출발한 곳 없애주기
                    if(map[atoms[i].y][atoms[i].x] == i) map[atoms[i].y][atoms[i].x] = 0;

                    // 이동시키기
                    atoms[i].y += dy[atoms[i].dir];
                    atoms[i].x += dx[atoms[i].dir];

                    int ny = atoms[i].y;
                    int nx = atoms[i].x;

                    // 밖으로 나가면 터질 수 없으므로 없애줌
                    if(nx < 0 || ny < 0 || nx > size || ny > size) {
                        atoms[i].exp = true;
                        cnt--;
                        continue;
                    }

                    // 없을 경우 진행
                    if(map[ny][nx] == 0) {
                        map[ny][nx] = i;
                    } else if (atoms[map[ny][nx]].exp) {
                        map[ny][nx] = i;
                    }
                    // 아직 안움직인 애와 충돌
                    else if (map[ny][nx] > i) {
                        // 방향 반대인 경우에만 충돌 가능
                        int dir = 2;
                        if(atoms[map[ny][nx]].dir == 0) {
                            dir = 1;
                        } else if (atoms[map[ny][nx]].dir == 1) {
                            dir = 0;
                        } else if (atoms[map[ny][nx]].dir == 2) {
                            dir = 3;
                        }

                        // 방향 반대일 경우 충돌하여 없애줌
                        if(dir == atoms[i].dir) {
                            answer += atoms[map[ny][nx]].k;
                            atoms[map[ny][nx]].exp = true;
                            map[ny][nx] = 0;

                            answer += atoms[i].k;
                            atoms[i].exp = true;

                            cnt -= 2;
                        }
                        // 반대가 아닌 경우 충돌 안 일어남
                        else {
                            map[ny][nx] = i;
                        }

                    // 먼저 움직인 애와 충돌은 좌표가 같다면
                    // 무조건 일어남. 하지만 뒤에 다른 애가 또 충돌할 수 있으므로 기다림
                    } else if(map[ny][nx] < i) {
                        atoms[map[ny][nx]].exp = true;
                        answer += atoms[map[ny][nx]].k;

                        cnt--;

                        map[ny][nx] = i;
                        queue.offer(i);
                    }
                }

                while(!queue.isEmpty()) {
                    int num = queue.poll();
                    if(atoms[num].exp) continue;

                    map[atoms[num].y][atoms[num].x] = 0;
                    answer += atoms[num].k;
                    atoms[num].exp = true;
                    cnt--;
                }

            }
            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }
}