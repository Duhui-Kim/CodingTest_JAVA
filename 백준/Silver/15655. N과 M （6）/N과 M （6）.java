import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N과 M을 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N크기의 arr와 check 배열을 만든다.
        int[] arr = new int[N];
        boolean[] check = new boolean[N];

        // M개의 수를 선택할 것이므로 M 크기의 배열을 만든다
        int[] ans = new int[M];

        // 배열에 값을 입력받는다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 사전 순으로 출력해야하므로 배열을 정렬한다.
        Arrays.sort(arr);

        // 경우의 수 탐색
        backTracking(arr, ans, check, 0);

        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int[] ans, boolean[] check, int k) {

        // 기저조건 : k가 ans의 length와 같아지면 종료
        // sb에 ans에 담긴 값을 저장한다.
        if(k == ans.length) {
            for (int i = 0; i < ans.length; i++) {
                sb.append(ans[i] + " ");
            }
            sb.append("\n");
            return;
        }

        // 사용되지 않은 인덱스를 추려서 ans에 넣어준다.
        for (int i = 0; i < arr.length; i++) {
            if(k == 0) {
                if (!check[i]) {
                    check[i] = true;
                    ans[k] = arr[i];
                    backTracking(arr, ans, check, k + 1);
                    check[i] = false;
                }
            } else {
                if (!check[i] && ans[k-1] <= arr[i]) {
                    check[i] = true;
                    ans[k] = arr[i];
                    backTracking(arr, ans, check, k + 1);
                    check[i] = false;
                }
            }
        }

    }
}