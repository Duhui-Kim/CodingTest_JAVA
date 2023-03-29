import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // testCase 입력받기
        int testCase = sc.nextInt();

        // 1부터 진행
        for (int tc = 1; tc <= testCase; tc++) {
            // N명의 사람과 M개의 간선 입력받기
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 부모 배열 생성
            int[] parent = new int[N+1];

            // 부모 배열에 자기자신 넣기
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            // 간선의 개수 세어줄 cnt
            int cnt = 0;
            // 간선을 입력받으면서 진행한다.
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                // 두 그룹이 다른 부모를 가지면 false 반환하고 이어주기, 같은 그룹이면 true 반환
                if(!makeGroup(parent, a, b)) continue;

                // 여기까지 왔으면 간선 하나 추가된 것
                cnt++;
            }

            sb.append(String.format("#%d %d\n", tc, N-cnt));
        }
        System.out.println(sb);
    }

    private static boolean makeGroup(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        if(a == b) return false;

        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    private static int find(int[] parent, int a) {
        if(parent[a] == a) return a;

        return find(parent, parent[a]);
    }
}