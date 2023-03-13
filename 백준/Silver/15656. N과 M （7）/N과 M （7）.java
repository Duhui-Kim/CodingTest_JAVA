import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] tmp;
    private static final StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 입력 배열
        arr = new int[N];

        // 정답 출력용 배열
        int[] answer = new int[M];

        // 배열에 숫자 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tmp = arr.clone();

        // 사전 순으로 증가하는 순서로 출력해야하므로 정렬 후 진행
        mergeSort(0, arr.length-1);

        backTracking(answer, M, 0);

        System.out.println(sb);
    }

    private static void backTracking(int[] answer, int m, int k) {
        // k == m이면 answer에 모든 원소가 차있으므로 출력한다.
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // answer에 차례로 넣어준다
        for (int j : arr) {
            answer[k] = j;
            backTracking(answer, m, k + 1);
        }
    }

    // 머지소트 구현
    private static void mergeSort(int start, int end) {
        if(start >= end) return;

        int mid = (start + end) / 2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);

        int p = start;
        int q = mid+1;
        int idx = start;

        while (p <= mid && q <= end) {
            if(tmp[p] > tmp[q]) {
                arr[idx++] = tmp[q++];
            } else {
                arr[idx++] = tmp[p++];
            }
        }

        while (p <= mid) {
            arr[idx++] = tmp[p++];
        }

        tmp = arr.clone();
    }
}