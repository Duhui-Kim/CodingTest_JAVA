import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 수 입력받기
        int N = Integer.parseInt(br.readLine());

        // 용액 담을 배열
        ArrayList<Integer> list = new ArrayList<>();

        // 용액 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        // 최솟값 저장용
        long zero = Long.MAX_VALUE;

        // 정답 용액 담을 배열
        long[] answer = new long[3];

        // 한 점을 고정시켜놓고 투포인터로 최솟값 찾기
        loop:
        for (int k = 0; k < list.size()-2; k++) {

            // 왼쪽 포인터를 k+1로 잡고 오른쪽 포인터를 list.size-1로 잡음
            int left = k+1;
            int right = list.size()-1;

            // 세 수를 더한 값이 0보다 크면 right를 줄여주고, 작으면 left를 늘려주며 값 갱신
            while (left < right) {
                long num = Long.valueOf(list.get(k)) + Long.valueOf(list.get(left)) + Long.valueOf(list.get(right));
                if (zero > Math.abs(num)) {
                    zero = Math.abs(num);
                    answer[0] = list.get(k);
                    answer[1] = list.get(left);
                    answer[2] = list.get(right);
                    if (zero == 0) break loop;
                }
                if (num < 0) {
                    left++;
                } else if (num > 0) {
                    right--;
                }
            }
        }
        if (list.size() == 3) {
            answer[0] = list.get(0);
            answer[1] = list.get(1);
            answer[2] = list.get(2);
        }

        for (int k = 0; k < 3; k++) {
            System.out.print(answer[k] + " ");
        }
    }

}