import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine());

            // x좌표와 y좌표를 저장할 map 배열
            long[][] map = new long[2][N];

            // x 좌표 입력
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for (int i = 0; i < N; i++) {
                map[0][i] = Long.parseLong(st.nextToken());
            }

            // y 좌표 입력
            st = new StringTokenizer(sc.nextLine());
            for (int i = 0; i < N; i++) {
                map[1][i] = Long.parseLong(st.nextToken());
            }

            // 세율 입력받기
            double E = Double.parseDouble(sc.nextLine());

            // 거리 순으로 최저값부터 뽑아내는 queue
            PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] > o2[2]) return 1;
                else return -1;
            });

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    long[] arr = new long[3];
                    // 어디서 어디로 향하는지와 거리 담기
                    arr[0] = i;
                    arr[1] = j;
                    arr[2] = (map[0][i] - map[0][j]) * (map[0][i] - map[0][j]) + (map[1][i] - map[1][j]) * (map[1][i] - map[1][j]);
                    queue.offer(arr);
                }
            }
            int[] parent = new int[N];

            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long answer = 0;

            int cnt = 0;
            while(!queue.isEmpty()) {
                long[] arr = queue.poll();
                int a = (int) arr[0];
                int b = (int) arr[1];
                long c = arr[2];

                if(!diffGroup(parent, a, b)) continue;

                answer += c;
                cnt++;
                if (cnt == N-1) break;
            }
            sb.append(String.format("#" + tc + " " + Math.round(answer * E)) + "\n");
        }
        System.out.println(sb);
    }

    private static boolean diffGroup(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if (a == b) return false;

        if (parent[a] < parent[b]) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    private static int find(int[] parent, int b) {
        if (parent[b] == b) return b;

        return parent[b] = find(parent, parent[b]);
    }
}