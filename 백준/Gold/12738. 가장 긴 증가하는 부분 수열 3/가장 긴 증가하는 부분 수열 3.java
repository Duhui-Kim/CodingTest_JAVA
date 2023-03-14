import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // input은 입력값 저장할 배열, tmp는 긴 수열 넣을 배열
        int[] input = new int[N];
        int[] tmp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        tmp[0] = input[0];
        int idx = 1;

        // 반복문을 진행하며, idx-1 보다 큰 값이 있으면 idx 자리에 값을 추가하고 idx를 증가시킨다.
        // idx보다 값이 작거나 같으면 binarySearch를 통해 들어갈 수 있는 자리를 찾아서 넣는다.
        for (int i = 0; i < N; i++) {
            if(tmp[idx-1] < input[i]) {
                tmp[idx++] = input[i];
            } else {
                int nIdx = findIdx(tmp, 0, idx, input[i]);
                tmp[nIdx] = input[i];
            }
        }
        System.out.println(idx);

    }
    private static int findIdx(int[] arr, int p, int q, int n) {
        int mid;
        while (p < q) {
            mid = (p + q) / 2;
            if(arr[mid] < n) {
                p = mid + 1;
            } else {
                q = mid;
            }
        }
        return q;
    }
}