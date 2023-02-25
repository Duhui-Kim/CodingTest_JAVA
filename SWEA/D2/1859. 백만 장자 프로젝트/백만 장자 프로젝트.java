import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            int N = Integer.parseInt(br.readLine());

            // 입력을 저장할 arr 선언
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());

            // arr에 먼저 숫자를 입력받는다.
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // cnt값을 0으로 설정하고 예측 + 결과값 측정하기
            long cnt = 0;

            // max 값은 arr의 맨 마지막으로 설정하고,
            // 앞에 있는 값이 현재값보다 클 경우 앞의 값으로 진행
            // 앞의 값이 현재 값보다 작을 경우 현재 값으로 덮어씌움
            int max = arr[N-1];
            for (int i = N-2; i >= 0; i--) {
                if(arr[i] > max) {
                    max = arr[i];
                } else {
                    cnt += max - arr[i];
                }
            }
            // 최댓값 - 현재값을 더 해줌
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
