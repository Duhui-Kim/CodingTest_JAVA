import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        // arr[k]에 1~N까지의 수를 차례로 넣고 다음 idx로 넘어간다.
        for (int i = 1; i <= N; i++) {
            arr[k] = i;
            backTracking(arr, k+1);
        }
    }
}
