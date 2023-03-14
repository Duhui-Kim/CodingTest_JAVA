import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 인풋에 tmp에 들어가는 인덱스와 값을 저장해야하므로, 2차 배열로 생성
        // tmp는 긴 수열 넣을 배열
        int[][] input = new int[N][2];
        int[] tmp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i][0] = Integer.parseInt(st.nextToken());
        }

        // 처음 값은 tmp에 넣고 시작한다.
        tmp[0] = input[0][0];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            // tmp의 값이 input보다 작으면 수열은 증가해야하므로, idx + 1에 input을 넣고 증가시킴.
            // 이 때 좌표값도 저장
            if(tmp[idx] < input[i][0]) {
                tmp[++idx] = input[i][0];
                input[i][1] = idx;
            }
            // tmp의 값이 input보다 작은 경우 들어갈 수 있는 idx를 찾아서 넣어준다.
            else {
                int ii = findIdx(tmp, 0, idx, input[i][0]);
                tmp[ii] = input[i][0];
                input[i][1] = ii;
            }
        }
        // 그럼 이제 input에 value와 idx가 저장되어 있고, idx는 tmp의 마지막 원소가 들어있는 값이다.
        Stack<Integer> stack = new Stack<>();

        // 뒤에서부터 해당 idx가 처음 나오는 값이 최종적으로 제출해야하는 값이다.
        int tIdx = idx;
        for (int i = N-1; i >= 0; i--) {
            if(input[i][1] == tIdx) {
                stack.push(input[i][0]);
                tIdx--;
            }
        }
        // idx + 1 값 = 개수
        StringBuilder sb = new StringBuilder();
        sb.append(idx + 1).append("\n");

        // stack에 거꾸로 넣었으므로 stack에서 뽑아내는 순서가 올바른 순서이다.
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);

    }
    private static int findIdx(int[] arr, int start, int end, int n) {
        int mid = 0;

        while (start < end) {
            mid = (start + end) / 2;
            if(arr[mid] < n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}