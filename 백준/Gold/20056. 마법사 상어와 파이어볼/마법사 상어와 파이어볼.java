import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class Fireball {
        int r, c, m, s, d;
        private Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }

    private static Queue<Fireball> queue = new LinkedList<>();
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0 ,-1, -1, -1};
    private static int N;
    private static ArrayList<Fireball>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // map의 크기 N과 파이어볼의 수 M, 이동횟수 K
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // fireball 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            queue.offer(new Fireball(r-1, c-1, m, s, d));
        }

        map = new ArrayList[N][N];

        for (int i = 0; i < K; i++) {
            // map을 초기화해준다.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] = new ArrayList<>();
                }
            }

            // queue에 담겨있는 파이어볼 이동
            while (!queue.isEmpty()) {
                Fireball fb = queue.poll();

                // 음수가 되지 않도록 N을 충분히 더해준 뒤 나머지를 구함
                fb.r = ((fb.r + dx[fb.d] * fb.s) + N * fb.s) % N;
                fb.c = ((fb.c + dy[fb.d] * fb.s) + N * fb.s) % N;

                // map에 저장해줌
                map[fb.r][fb.c].add(fb);
            }

            // 배열을 순회
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    // 1개만 있다면 queue에 넣어줌
                    if (map[j][k].size() == 1) {
                        queue.offer(map[j][k].get(0));
                    }
                    // 2개 이상 있다면 합쳐줌
                    else if (map[j][k].size() > 1) {
                        merge(map[j][k], j, k);
                    }
                }
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) cnt += queue.poll().m;

        System.out.println(cnt);
    }

    // 파이어볼 합치고 나누기
    private static void merge(ArrayList<Fireball> fireballs, int r, int c) {
        // 질량과 속력 저장용
        int m = 0;
        int s = 0;

        // 방향의 홀수 짝수 여부 판단
        boolean same = true;
        int pre = fireballs.get(0).d % 2;

        // 파이어볼 더해주기
        for(Fireball f : fireballs) {
            m += f.m;
            s += f.s;

            // 모두 짝수 또는 홀수가 아니라면 false
            if (pre != f.d % 2) same = false;
        }
        // 질량이 0이면 리턴
        if (m/5 == 0) return;

        // 홀수 방향 더할지 짝수 방향 더할지 정하기
        int start = 0;
        if (!same) start = 1;

        // 홀수 또는 짝수방향으로 나뉘기
        for (int i = start; i < 8; i+=2) {
            queue.offer(new Fireball(r, c, m / 5, s / fireballs.size(), i));
        }
    }
}