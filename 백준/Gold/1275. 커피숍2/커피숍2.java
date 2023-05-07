import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int ROOT = 1;

        long[] arr = new long[N+1];
        long[] segment = new long[N * 4 + 4];

        // 인풋 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        // 1. 세그먼트 트리 만들기
        makeTree(segment, arr, ROOT, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // x가 더 큰 경우 두 수의 위치 바꿔주기
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            // 2. 구간합 구하기
            sb.append(findSum(segment, 1, N, x, y, ROOT)).append("\n");

            // 3. 숫자 바꾸기
            arr[a] = b;
            changeTree(segment, arr, 1, N, a, ROOT);
        }
        System.out.println(sb);
    }
    // 1. 세그먼트 트리 만들기
    private static long makeTree(long[] segment, long[] arr, int idx, int st, int ed) {
        if (st == ed) return segment[idx] = arr[st];

        int mid = (st + ed) / 2;
        return segment[idx]
                = makeTree(segment, arr, idx * 2, st, mid)
                + makeTree(segment, arr, idx * 2 + 1, mid+1, ed);
    }

    // 2. 세그먼트 트리 합계 구하기
    private static long findSum(long[] segment, int st, int ed, int x, int y, int idx) {
        // 범위 밖이면 더해주지 않음
        if (x > ed || y < st) return 0;
        // 범위 안이면 더해줌
        if (x <= st && ed <= y) return segment[idx];

        int mid = (st + ed) / 2;
        return findSum(segment, st, mid, x, y, idx*2) + findSum(segment, mid+1, ed, x, y, idx*2+1);
    }

    // 3. 세그먼트 트리 바꾸기
    private static long changeTree(long[] segment, long[] arr, int st, int ed, int target, int idx) {
        if (target < st || target > ed) return segment[idx];
        if (st == ed) return segment[idx] = arr[st];

        int mid = (st + ed) / 2;
        return segment[idx]
                = changeTree(segment, arr, st, mid, target, idx*2)
                + changeTree(segment, arr, mid+1, ed, target, idx*2+1);
    }
}