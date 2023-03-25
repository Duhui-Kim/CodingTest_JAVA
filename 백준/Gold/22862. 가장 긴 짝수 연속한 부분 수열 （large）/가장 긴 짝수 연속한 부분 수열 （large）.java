import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수열의 길이 N과 삭제가능한 횟수 K 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        // 수열 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 홀수 개수를 세어줄 cnt
        int cnt = 0;

        // size를 측정할 size
        int size = 0;

        // 정답을 저장할 answer
        int answer = 0;

        int p = -1;
        int q = -1;

        while (q < N && p < N) {
            // K개의 홀수를 안넘을때까지 오른쪽 포인터 이동하며 size증가
            while(q < N-1 && cnt + arr[q+1]%2 <= K) {
                q++;
                size++;
                cnt += arr[q]%2;
            }
            // K개의 홀수보다 홀수가 많아졌으면 왼쪽 포인터 이동하며 홀수 감소시키기
            while (p < N-1 && cnt > K) {
                p++;
                size--;
                cnt -= arr[p]%2;
            }

            // answer에 전체 size에서 홀수 뺀 사이즈를 저장
            if(cnt <= K) answer = Math.max(answer, size-cnt);

            // 다음을 확인하기 위해 오른쪽 포인터를 이동시킴
            q++;
            size++;
            cnt += arr[q]%2;
        }
        // 결과 출력
        System.out.println(answer);
    }
}