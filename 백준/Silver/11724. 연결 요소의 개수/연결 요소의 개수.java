import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 노드의 개수 N과 간선의 개수 M 입력받기
        int N = sc.nextInt();
        int M = sc.nextInt();

        // 인접리스트와 체크 배열 선언
        ArrayList<Integer>[] list = new ArrayList[N+1];
        boolean[] check = new boolean[N+1];

        // 간선 입력받기
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (list[a] == null) list[a] = new ArrayList<>();
            if (list[b] == null) list[b] = new ArrayList<>();

            // 간선 양쪽으로 저장하기
            list[a].add(b);
            list[b].add(a);
        }

        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            // 이미 체크되었으면 지나감
            if (check[i]) continue;

            cnt++;
            queue.offer(i);
            check[i] = true;

            // queue에서 꺼내서 진행+
            while (!queue.isEmpty()) {
                int x = queue.poll();

                if (list[x] == null) continue;

                // 리스트에 있는 숫자를 꺼낸다.
                for(int a : list[x]) {
                    if (check[a]) continue;
                    check[a] = true;
                    queue.offer(a);
                }
            }
        }
        System.out.println(cnt);
    }
}