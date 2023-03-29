import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵의 크기 N과 스위치의 개수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 스위치를 담아둘 리스트
        List<Switch> list = new ArrayList<>();

        // 리스트에 스위치 담기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.add(new Switch(x, y, a, b));
        }

        // map 생성 (불 켜진 방 체크)
        boolean[][] map = new boolean[N+1][N+1];

        // 중복이동 방지용 배열
        boolean[][] check = new boolean[N+1][N+1];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // queue를 선언하고 1,1 넣어주기
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 1});

        // 불 켠 방의 수 (1, 1) 포함이므로 1부터 시작
        int light = 1;

        // 시작점 true로 바꾸고 시작하기
        check[1][1] = true;
        map[1][1] = true;

        // BFS 진행
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];

            // 해당 좌표 불 켜주기
            int chk = 1;

            // chk가 0이면 일치하는 좌표 없으므로 종료
            while (chk != 0) {
                if (list.size() == 0) break;
                chk = 0;
                for (Switch s : list) {
                    if (s.x == x && s.y == y) {
                        if (!map[s.a][s.b]) light++;
                        map[s.a][s.b] = true;

                        // 스위치를 켜고나서 주변을 탐방한다.
                        // 만약 체크가 되어있다면 해당 방으로 도달할 수 있는 것이므로 해당 방의 좌표를 queue에 넣는다.
                        for (int i = 0; i < 4; i++) {
                            int cx = s.a + dx[i];
                            int cy = s.b + dy[i];

                            if (cx < 1 || cy < 1 || cx > N || cy > N) continue;
                            if (check[cx][cy]) {
                                queue.offer(new int[]{cx, cy});
                                break;
                            }
                        }
                        list.remove(s);
                        chk = 1;
                        break;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 밖이거나 불이 켜져있지 않거나 이미 지나간 곳이면 불가능
                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if (!map[nx][ny]) continue;
                if (check[nx][ny]) continue;

                // 방문체크를 하고 불을 켜준다.
                check[nx][ny] = true;

                // queue에 넣어주기
                queue.offer(new int[]{nx, ny});
            }

        }
        System.out.println(light);
    }

    // 스위치 객체
    static class Switch {
        int x;
        int y;
        int a;
        int b;
        private Switch(int x, int y, int a, int b) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.b = b;
        }
    }
}