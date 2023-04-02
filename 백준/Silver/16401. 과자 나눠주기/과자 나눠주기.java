import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
            max = max < list.get(i) ? list.get(i) : max;
        }

        Collections.sort(list);

        int left = 0;
        int right = max;
        int mid = 0;

        while (left < right) {
            mid = (left + right + 1) / 2;

            int cnt = 0;
            for (int i = N-1; i >= 0; i--) {
                int tmp = list.get(i) / mid;
                if (tmp == 0) break;
                cnt += tmp;
            }
            if (cnt >= M) {
                left = mid;
            } else {
                right = mid-1;
            }
        }
        System.out.println(left);
    }
}