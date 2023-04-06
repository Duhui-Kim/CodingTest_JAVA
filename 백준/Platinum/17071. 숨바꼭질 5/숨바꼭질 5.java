import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Pair {
        int x;
        int num;
        private Pair(int x, int num) {
            this.x = x;
            this.num = num;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수빈이와 동생의 위치
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 동생과 수빈이 위치가 같으면 0 출력
        if (N == K) {
            System.out.println(0);
            return;
        }
        // 맵 그냥 크게 만들어놓기
        boolean[][] map = new boolean[2][500001];

        // queue에 초기값 넣어두기
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(N, 0));
        map[0][N] = true;

        // 몇 번 이동했는지
        int cnt = 0;

        // delta
        int[] dx = {-1, 1, N};

        // BFS 진행
        while (!queue.isEmpty()) {
            // 한 사이클씩 진행
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair pair = queue.poll();
                dx[2] = pair.x;

                // -1, +1, +x 만큼씩 이동
                for (int j = 0; j < 3; j++) {
                    int nx = pair.x + dx[j];

                    // 범위 밖이면 지나가고, 이미 체크되었으면 지나감
                    if (nx < 0 || nx > 500000) continue;
                    if (map[(pair.num+1)%2][nx]) continue;

                    // 홀수번째 이동이면 홀수 map에 체크, 짝수번째 이동이면 짝수 map에 체크
                    map[(pair.num+1)%2][nx] = true;
                    queue.offer(new Pair(nx, (pair.num+1)%2));
                }
            }
            // 동생 가속도 넣어주기
            cnt++;
            K += cnt;

            // K가 범위 밖이면 지나감
            if (K > 500000) {
                System.out.println(-1);
                return;
            }
            // 홀수번째 또는 짝수번째 이동인데 이미 홀수번째 또는 짝수번째에 체크가 되어있다면 
            // cnt 출력 후 종료
            if (map[cnt%2][K]) {
                System.out.println(cnt);
                return;
            }
        }
        // 여기까지 못찾았다면 -1 출력
        System.out.println(-1);
    }
}