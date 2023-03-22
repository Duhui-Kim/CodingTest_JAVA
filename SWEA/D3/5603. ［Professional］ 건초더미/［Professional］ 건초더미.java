import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            int sumNum = 0;
            int num = 0;
            for (int i = 0; i < N; i++) {
                num = Integer.parseInt(br.readLine());
                arr[i] = num;
                sumNum += num;
            }

            sumNum = sumNum / N;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += Math.abs(sumNum - arr[i]);
            }

            sb.append(String.format("#%d %d\n", tc, cnt/2));
        }
        System.out.println(sb);
    }
}