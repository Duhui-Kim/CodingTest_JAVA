import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 세그먼트 트리 만들기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        final int ROOT = 1;

        // 세그먼트 트리 배열과 input 담을 배열 선언
        long[] tree = new long[N * 4 + 4];
        long[] arr = new long[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        makeTree(tree, arr, ROOT, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // a가 1일 경우 값 변경
            if(a == 1) {
                arr[b] = c;
                change(tree, arr, ROOT, b, 1, N);
            }
            // a가 2일 경우 구간합 구하기
            else {
                sb.append(find(tree, ROOT, b, (int)c, 1, N)).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 세그먼트 트리 초기화
    private static long makeTree(long[] tree, long[] arr, int idx, int start, int end) {
        // [리프노드] start == end일 경우 arr 값을 tree idx에 넣는다.
        if(start == end) {
            return tree[idx] = arr[start];
        }
        int mid = (start + end) / 2;

        // 왼쪽 자식과 오른쪽 자식의 합이 부모노드
        return tree[idx] = makeTree(tree, arr, idx * 2, start, mid)
                + makeTree(tree, arr, idx * 2 + 1, mid+1, end);
    }

    // 값 변경하기
    private static long change(long[] tree, long[] arr, int idx, int num, int st, int ed) {
        // 변경하려는 값이 범위 밖이면 바뀌지 않으므로 기존 값 반환
        if(num < st || num > ed) {
            return tree[idx];
        }
        // 리프노드에 도달하면 arr 값 반환
        if (st == ed) {
            return tree[idx] = arr[st];
        }
        int mid = (st + ed) / 2;

        // 부모노드는 양쪽 자녀노드의 합
        return tree[idx] = change(tree, arr, idx * 2, num, st, mid)
                + change(tree, arr, idx * 2 + 1, num, mid+1, ed);
    }

    // 구간합 구하기
    private static long find(long[] tree, int idx, int from, int to, int st, int ed) {
        // 범위 밖이면 안 더해줌
        if (to < st || from > ed) return 0;
        // 범위 안일 경우 더해줌
        if (from <= st && ed <= to) {
            return tree[idx];
        }
        int mid = (st + ed) / 2;

        // 최종 리턴 값은 범위 밖의 값을 뺀 나머지의 합
        return find(tree, idx*2, from, to, st, mid) + find(tree, idx*2+1, from, to, mid+1, ed);
    }

}
