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

        int N = Integer.parseInt(br.readLine());

        int[][] input = new int[4][N];

        // 네 배열의 숫자를 입력받는다
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            input[0][i] = Integer.parseInt(st.nextToken());
            input[1][i] = Integer.parseInt(st.nextToken());
            input[2][i] = Integer.parseInt(st.nextToken());
            input[3][i] = Integer.parseInt(st.nextToken());
        }

        int[] arr1 = new int[N*N];
        int[] arr2 = new int[N*N];

        // 두 배열의 합을 모두 구해서 list에 담는다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr1[i*N+j] = input[0][i] + input[1][j];
                arr2[i*N+j] = input[2][i] + input[3][j];
            }
        }
        // 리스트를 정렬한다.
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int p1 = 0;
        int p2 = N * N - 1;

        // 0이 되는 숫자를 세어줄 cnt
        long cnt = 0;

        while (p1 < N * N && p2 >= 0) {
            int num = arr1[p1] + arr2[p2];

            if (num == 0) {
                long a = 1;
                long b = 1;
                p1++;
                p2--;

                while (p1 < N * N && arr1[p1] == arr1[p1-1]) {
                    p1++;
                    a++;
                }
                while (p2 >= 0 && arr2[p2] == arr2[p2+1]) {
                    p2--;
                    b++;
                }
                cnt += a * b;

            } else if (num > 0) {
                p2--;
            } else {
                p1++;
            }
        }
        System.out.println(cnt);
    }
}