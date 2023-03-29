import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int[] parent = new int[N+1];

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            sb.append(String.format("#%d ", tc));
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(sc.nextLine());
                int action = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if (action == 0) {
                    union(parent, a, b);
                } else {
                    sb.append(sameGroup(parent, a, b));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int sameGroup(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);

        // 같은 그룹이면 1, 다른 그룹이면 0
        if (a == b) return 1;
        else return 0;
    }

    private static void union(int[] parent, int a, int b) {
        // a와 b의 부모를 받아와서 하나의 부모로 만들어주기
        a = find(parent, a);
        b = find(parent, b);
        
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
    private static int find(int[] parent, int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent, parent[a]);
    }
    
}