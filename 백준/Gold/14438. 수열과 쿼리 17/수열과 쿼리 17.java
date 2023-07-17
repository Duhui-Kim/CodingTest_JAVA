import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N+1];
        int[] segment = new int[4 * (N+1)];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        insert(segment, input, 1, 1, N);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                update(segment, a, b, 1, 1, N);
            } else {
                sb.append(find(segment, a, b, 1, 1, N)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int find(int[] segment, int left, int right, int idx, int st, int ed) {
        if (ed < left || st > right)
            return Integer.MAX_VALUE;

        if (left <= st && ed <= right)
            return segment[idx];

        int mid = (st + ed) / 2;

        return Math.min(find(segment, left, right, idx * 2, st, mid), find(segment, left, right, idx * 2 + 1, mid+1, ed));
    }

    private static int update(int[] segment, int uIdx, int value, int idx, int st, int ed) {
        if (uIdx < st || uIdx > ed)
            return segment[idx];

        if (st == ed)
            return segment[idx] = value;

        int mid = (st + ed) / 2;

        return segment[idx] = Math.min(update(segment, uIdx, value, idx * 2, st, mid), update(segment, uIdx, value, idx * 2 + 1, mid+1, ed));
    }

    private static int insert(int[] segment, int[] input, int idx, int st, int ed) {
        if (st == ed)
            return segment[idx] = input[st];

        int mid = (st + ed) / 2;

        return segment[idx] = Math.min(insert(segment, input, idx * 2, st, mid), insert(segment, input, idx * 2 + 1, mid+1, ed));
    }
}