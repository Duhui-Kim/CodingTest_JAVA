import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        Arrays.sort(arr);

        // 두 점을 정해놓고 시작
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < N-3; i++) {
            for (int j = N-1; j >= i+3; j--) {

                // 왼쪽과 오른쪽 포인터 설정
                int left = i + 1;
                int right = j - 1;
                int sum = arr[i] + arr[j];

                // 두 포인터를 이동시켜가며 차이의 최솟값 구하기
                while (left < right) {
                    if (left < right) {
                        answer = Math.min(answer, Math.abs(sum - arr[left] - arr[right]));
                    }

                    if (arr[left] + arr[right] == sum) {
                        System.out.println(0);
                        return;
                    } else if (arr[left] + arr[right] < sum) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        // 정답 출력
        System.out.println(answer);
    }
}