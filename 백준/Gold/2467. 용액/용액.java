import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int p = 0;
        int q = N-1;

        int min = arr[p] + arr[q];
        int[] answer = new int[2];
        answer[0] = arr[p];
        answer[1] = arr[q];

        while (p < q) {
            // min의 절댓값보다 arr[p] + arr[q]이 작을 경우 0에 가까우므로 정답 저장하기 
            if(Math.abs(min) > Math.abs(arr[p] + arr[q])) {
                min = arr[p] + arr[q];
                answer[0] = arr[p];
                answer[1] = arr[q];
            }
            // 두 수의 합이 0보다 크면 q를 내려줌
            if(arr[p] + arr[q] > 0) {
                q--;
            } 
            // 두 수의 합이 0보다 작으면 p를 올려줌
            else if (arr[p] + arr[q] < 0) {
                p++;
            } 
            // 두 수의 합이 0이면 종료
            else {
                break;
            }
        }
        // 결과 출력
        System.out.println(answer[0] + " " + answer[1]);
    }
}