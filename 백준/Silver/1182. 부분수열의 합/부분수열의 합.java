import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N과 S를 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        // N 크기의 arr 배열을 만든다.
        int[] arr = new int[N];

        // arr에 값을 입력받는다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1부터 N 크기의 값을 저장하기 위한 answer 배열을 만들고 backtracking 진행
        for (int i = 0; i < N; i++) {
            int[] answer = new int[i+1];
            backTracking(arr, answer, answer.length, i, 0, N, S);
        }
        
        // count 반환
        System.out.println(count);
    }

    private static void backTracking(int[] arr, int[] answer, int m, int x, int k, int n, int s) {
        // k가 m에 도달하면 값을 다 더해서 S와 같으면 count 반환
        if(k == m) {
            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum += answer[i];
            }
            if(sum == s) count++;
            return;
        }

        // k==0일 때는 아무 값이나 넣어주고,
        // k가 0이 아닐 때는 이전에 넣어준 다음 원소부터 넣을 수 있다.(중복방지)
        for (int i = 0; i < n; i++) {
            if(k==0) {
                answer[k] = arr[i];
                backTracking(arr, answer, m, i, k+1, n, s);
            } else {
                if (i > x) {
                    answer[k] = arr[i];
                    backTracking(arr, answer, m, i, k+1, n, s);
                }
            }
        }
    }
}