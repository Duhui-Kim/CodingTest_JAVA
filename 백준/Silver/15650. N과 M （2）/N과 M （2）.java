import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input을 String 형태로 입력받고 N과 M을 숫자로 저장한다.
        String input = br.readLine();
        N = Integer.parseInt(input.split(" ")[0]);
        M = Integer.parseInt(input.split(" ")[1]);

        // 숫자를 저장할 arr를 만들고 backTracking을 시작한다.
        int[] arr = new int[M];

        backTracking(arr, 0);
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int k) {
        // k가 M과 같아지면 배열에서 값을 꺼내서 sb에 입력한다.
        if(k == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        // k가 0일 때는 모든 수를 저장하고,
        // k가 1 이상일 때는 이전 수와 비교해서 큰 값만 넣을 수 있다.
        for (int i = 1; i <= N; i++) {
            if(k != 0 && arr[k-1] < i) {
                arr[k] = i;
                backTracking(arr, k+1);
            }
            if(k == 0) {
                arr[k] = i;
                backTracking(arr, k+1);
            }
        }
    }
}
