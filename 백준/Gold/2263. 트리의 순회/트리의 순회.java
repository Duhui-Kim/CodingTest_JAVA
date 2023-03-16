import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] inOrder;
    private static int[] postOrder;
    private static int idx = 1;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 숫자를 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // 인오더를 입력받을 배열과 포스트오더를 입력받을 배열 선언
        inOrder = new int[N+1];
        postOrder = new int[N+1];

        // 인오더와 포스트오더 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        // 프리오더 출력을 위한 method
        makePreOrder(1, N, 0);

        // 결과 출력
        System.out.println(sb);
    }

    // 여기서 left와 right를 나눠서 출력한다.
    private static void makePreOrder(int st, int end, int k) {
        if(st > end) {
            return;
        }

        // postOrder의 맨 마지막 값은 root의 value이므로 StringBuilder에 저장 후 시작
        sb.append(postOrder[end]).append(" ");

        // postOrder의 end값과 일치하는 inOrder의 값을 찾는다.
        // 이 값을 기준으로 왼쪽과 오른쪽 자식이 나뉜다.
        int mid = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if(postOrder[end] == inOrder[i]) {
                mid = i;
            }
        }
        mid += k;

        // 왼쪽은 end를 mid - 1까지로 조정해서 재귀
        makePreOrder(st, mid-1, k);

        // 오른쪽은 index 값을 mid부터 end-1까지로 조정해서 재귀
        makePreOrder(mid, end-1, k-1);
    }
}