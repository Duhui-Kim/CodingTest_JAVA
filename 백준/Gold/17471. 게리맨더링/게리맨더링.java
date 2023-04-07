import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /*
    1. 선거구 정하기 (백트래킹)
    2. 다 정해지면 각 선거구별 BFS 진행
    3. 모든 선거구가 체크될 경우 값 비교
    4. 체크 안된 선거구가 있을 경우 지나감
     */

    static class Pair {
        int a;
        int b;
        int point;
        private Pair(int a, int b, int point) {
            this.a = a;
            this.b = b;
            this.point = point;
        }
    }
    private static int N;
    private static int[] point;
    private static ArrayList<Pair>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        point = new int[N+1];

        // 각 지역의 포인트
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }

        // 간선 입력받을 인접리스트
        list = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 간선 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int b = Integer.parseInt(st.nextToken());

                if (i == b) continue;

                // i에서 b로 가면 point[b]만큼을 얻을 수 있음
                list[i].add(new Pair(i, b, point[b]));
            }
        }
        // answer 초기화
        int answer = Integer.MAX_VALUE/2;

        int[] arr = new int[N+1];

        answer = backTracking(arr, 1);

        if (answer == Integer.MAX_VALUE/2) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int backTracking(int[] arr, int k) {
        if (k == N + 1) {
            boolean[] check = new boolean[N+1];

            for (int i = 1; i <= N; i++) {
                if (arr[i] == 1) {
                    BFS(check, arr, i);
                    break;
                }
            }

            for (int i = 1; i <= N; i++) {
                if (arr[i] == 2) {
                    BFS(check, arr, i);
                    break;
                }
            }

            for (int i = 1; i <= N; i++) {
                if (!check[i]) {
                    return Integer.MAX_VALUE/2;
                }
            }

            int a = 0;
            int b = 0;
            for (int i = 1; i <= N; i++) {
                if (arr[i] == 1) {
                    a += point[i];
                } else {
                    b += point[i];
                }
            }

            return Math.abs(a - b);
        }

        arr[k] = 1;
        int c = backTracking(arr, k+1);
        arr[k] = 2;
        int d = backTracking(arr, k+1);

        return Math.min(c, d);
    }

    private static void BFS(boolean[] check, int[] arr, int a) {
        int choice = arr[a];
        check[a] = true;

        Queue<Pair> queue = new LinkedList<>();

        for(Pair p : list[a]) {
            queue.offer(p);
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            if (arr[pair.b] != choice) continue;
            if (check[pair.b]) continue;

            check[pair.b] = true;

            for(Pair p : list[pair.b]) {
                queue.offer(p);
            }
        }
    }

}