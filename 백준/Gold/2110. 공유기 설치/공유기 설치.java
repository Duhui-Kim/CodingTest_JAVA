import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 집의 개수 N과 공유기의 개수 C
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        // 이분탐색을 위해 정렬하기
        Arrays.sort(house);

        int left = 0;
        int right = house[N-1];
        int max = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(house, N, mid) >= C) {
                max = Math.max(max, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(max);
    }

    private static int check(int[] house, int N, int length) {
        int cnt = 1;
        int pre = house[0];
        for (int i = 1; i < N; i++) {

            if (house[i] - pre >= length) {
                cnt++;
                pre = house[i];
            }
        }
        return cnt;
    }
}