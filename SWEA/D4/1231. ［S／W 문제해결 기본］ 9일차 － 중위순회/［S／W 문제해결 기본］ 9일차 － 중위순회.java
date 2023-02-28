import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {

            int N = Integer.parseInt(sc.nextLine());

            // arr 배열의 각 칸에
            // [자기숫자, Value, Left, Right]를 넣는다. 자기숫자 = index여서 사실 필요없긴하다.
            String[][] arr = new String[N + 1][4];

            // 본인 숫자와 idx를 맞추기 위해 1부터 N까지 idx에 맞게 값을 넣어줬다.
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                int idx = 0;
                while (st.hasMoreTokens()) {
                    arr[i][idx++] = st.nextToken();
                }
            }
            sb.append("#" + t + " ");
            // 중위순회 시작(1번 idx부터)
            traverse(arr, 1, N, sb);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void traverse(String[][] arr, int i, int N, StringBuilder sb) {
        // i가 N 이하일 때만 진행
        if(i <= N) {
            // Left나 Right가 없으면 진행하지 않고,
            // L - V - R 순으로 순회 진행
            if(arr[i][2] != null) traverse(arr, Integer.parseInt(arr[i][2]), N, sb);
            sb.append(arr[i][1] + "");
            if(arr[i][3] != null) traverse(arr, Integer.parseInt(arr[i][3]), N, sb);
        }
    }
}
