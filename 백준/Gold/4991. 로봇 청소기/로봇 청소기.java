import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
    1. 시작점과 각 더러운 지점에 넘버링을 한다.
    2. 한 점에서 다른 모든 점까지의 거리를 BFS를 이용해서 구하고 배열에 저장한다.
    3. 도달하지 못하는 점이 하나라도 있으면 -1 출력
    4. 아닐 경우 백트래킹을 이용해서 최소 이동거리를 구한다.
     */

    private static class Pair {
        int x, y, t;
        private Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int W, H, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        loop:
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // map의 넓이와 높이
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            if (W == 0 && H == 0) break;

            int[][] map = new int[W][H];

            // 맵을 입력받는다.
            // 넘버링을 위한 cnt
            cnt = 2;
            for (int i = 0; i < W; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < H; j++) {
                    // 시작점
                    if (input[j] == 'o') {
                        map[i][j] = 1;
                    }
                    // 더러운 칸 넘버링
                    else if (input[j] == '*') {
                        map[i][j] = cnt++;
                    }
                    // 벽
                    else if (input[j] == 'x') {
                        map[i][j] = -1;
                    }
                }
            }

            // 거리 저장할 배열
            int[][] distance = new int[cnt][cnt];

            // 반복문을 진행하며 각 점에서 도착점까지의 거리를 저장
            for (int i = 1; i < cnt; i++) {
                distance[i] = BFS(map, i);

                // 도달하지 못하는 지점이 있으면 0번 idx에 -1이 있음
                if (distance[i][0] == -1) {
                    sb.append(-1).append("\n");
                    continue loop;
                }
            }

            // cnt가 3인 경우 1-2번 연결이 끝
            if (cnt == 3) {
                sb.append(distance[1][2]).append("\n");
                continue loop;
            }

            // 3보다 큰 경우 순열 구하기
            int[] permutation = new int[cnt-2];
            for (int i = 0; i < cnt-2; i++) {
                permutation[i] = i+2;
            }

            int minTime = Integer.MAX_VALUE;

            do {
                // 시작점과 첫번째 도착점 사이의 거리 저장
                int time = distance[1][permutation[0]];
                // 순열로 구해진 각 점까지의 거리 저장
                for (int i = 1; i < cnt-2; i++) {
                    time += distance[permutation[i-1]][permutation[i]];
                }
                // 최소시간과 비교
                minTime = Math.min(time, minTime);

            } while (nextPermutaion(permutation, cnt-2));

            // 최소시간 저장
            sb.append(minTime).append("\n");
        }
        // 정답 출력
        System.out.println(sb);
    }

    private static boolean nextPermutaion(int[] permutation, int end) {

        // 맨 끝 - 1에서 시작
        int idx = end - 2;

        // 앞의 수가 더 클 경우 idx를 계속 줄여나감
        while (idx >= 0 && permutation[idx] >= permutation[idx+1]) idx--;

        // 맨 앞까지 내림차순 정렬되어있으면 종료
        if (idx < 0) return false;

        // 아닌 경우 새로운 포인터를 맨 오른쪽에 잡음
        int tidx = end - 1;

        // 기준점의 값보다 타겟점의 값이 커질 때가지 타겟점 idx 내림
        while (permutation[idx] >= permutation[tidx]) tidx--;

        // 두 값을 바꿔줌
        swap(permutation, idx, tidx);

        // idx 뒤쪽의 값을 오름차순으로 재정렬
        int i = idx + 1;
        int j = end - 1;

        while (i < j) {
            swap(permutation, i, j);
            i++; j--;
        }
        // true 반환
        return true;
    }

    private static void swap(int[] permutation, int i, int j) {
        int tmp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = tmp;
    }

    private static int[] BFS(int[][] map, int num) {
        // BFS용 queue와 방문체크 배열
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] check = new boolean[W][H];

        // 시작점 만나면 시작
        loop:
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (map[i][j] == num) {
                    queue.offer(new Pair(i, j, 0));
                    check[i][j] = true;
                    break loop;
                }
            }
        }

        // 거리 저장용
        int[] dist = new int[cnt];

        // BFS 진행
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = pair.x + dx[i];
                int ny = pair.y + dy[i];

                // 범위 밖이거나 벽이거나 이미 방문했다면 지나감
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                if (map[nx][ny] == -1) continue;
                if (check[nx][ny]) continue;

                // 방문체크
                check[nx][ny] = true;

                // 더러운 지점을 만나면 해당 지점 도달시간 저장
                if (map[nx][ny] != 0) dist[map[nx][ny]] = pair.t + 1;

                // queue에 넣어주기
                queue.offer(new Pair(nx, ny, pair.t+1));
            }
        }

        // 자기자신을 제외하고 0인 값이 있으면 도달하지 못한 것이므로 -1 넣어줌
        for (int i = 1; i < cnt; i++) {
            if (i == num) continue;

            if (dist[i] == 0) dist[0] = -1;
        }

        // 배열 반환
        return dist;
    }
}