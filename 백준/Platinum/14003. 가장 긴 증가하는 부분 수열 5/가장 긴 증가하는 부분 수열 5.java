import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 입력 저장용 배열
    private static int[] arr;
    private static int[] tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 입력 배열과 해당 배열의 idx를 저장할 배열을 선언한다.
        arr = new int[N];
        int[] idxArray = new int[N];

        // 배열을 입력받는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 가장 긴 길이의 수열을 저장할 배열 tmp를 선언하고 0번째 idx에 arr의 0번째 idx를 넣고 시작한다.
        int idx = 0;
        tmp = new int[N];
        Arrays.fill(tmp, Integer.MIN_VALUE);
        tmp[0] = arr[0];

        // tmp의 idx 값보다 arr[i]가 크면 tmp의 맨 뒤에 arr의 값을 넣는다.
        for (int i = 0; i < arr.length; i++) {
            if(tmp[idx] < arr[i]) {
                tmp[++idx] = arr[i];
                idxArray[i] = idx;
            // 그렇지 않을 경우 arr의 값이 들어갈 수 있는 위치를 찾아 넣는다.
            } else {
                idxArray[i] = binarySearch(0, idx, arr[i]);
                if(tmp[idxArray[i]] < arr[i]) {
                    idxArray[i] = Integer.MAX_VALUE;
                } else {
                    tmp[idxArray[i]] = arr[i];
                }
            }
        }
        // 출력을 위한 StringBuilder 선언
        StringBuilder sb = new StringBuilder();

        // idx + 1의 값이 가장 긴 길이
        sb.append(idx+1).append("\n");

        // stack을 이용하는데, 맨 뒤에서부터 앞으로 오면서 idxArray의 값과 idx가 같은 값을 보면
        // 그 값을 stack에 넣고 idx를 1 줄여준다.
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = N-1; i >= 0; i--) {
            if(idxArray[i] == idx) {
                stack.offerFirst(arr[i]);
                idx--;
            }
        }
        // 최종적으로 stack에 들어있는 수열이 가장 긴 길이의 수열
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst()).append(" ");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // BinarySearch method (idx 반환)
    private static int binarySearch(int st, int end, int n) {

        int mid;
        while (st < end) {
            mid = (st + end) / 2;
            if(tmp[mid] == n) {
                end = mid;
                break;
            } else if (tmp[mid] > n) {
                end = mid;
            } else {
                st = mid + 1;
            }
        }

        return end;
    }
}