import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        br.close();

        // K가 N보다 작거나 같을 경우는 이렇게 반환
        if(K <= N) {
            System.out.println(N - K);
            return;
        }
        boolean[] check = new boolean[100001];
        int[] map = new int[100001];
        Queue<Integer> queue = new LinkedList<>();

        int[] dx = {-1, 1};

        queue.offer(N);
        check[N] = true;

        loop:
        while (!queue.isEmpty()){
            int x = queue.poll();

            int nx = x + x;
            if(nx >= 0 && nx < 100001 && !check[nx]) {
                check[nx] = true;
                map[nx] = map[x];
                queue.offer(nx);
                if(nx == K) break loop;
            }

            for(int i=0; i<2; i++){
                nx = x + dx[i];
                if(nx >= 0 && nx < 100001 && !check[nx]) {
                    check[nx] = true;
                    map[nx] = map[x] + 1;
                    queue.offer(nx);
                    if(nx == K) break loop;
                }
            }
        }
        System.out.println(map[K]);
    }
}
