import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[3];

        // 입력받으면서 바로 진행한다.
        for (int i = 0; i < N; i++) {
            // R, G, B를 입력받는다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            // arr에 있는 값 = 이전에 저장된 값이므로
            // R에는 G, B 중 작은 값을 원래값에 더해주고,
            // G에는 R, B 중 작은 값을 원래값에 더한다.
            // B에도 마찬가지로 더한다.
            R += Math.min(arr[1], arr[2]);
            G += Math.min(arr[0], arr[2]);
            B += Math.min(arr[0], arr[1]);

            // arr에 R, G, B 값을 업데이트한다.
            arr[0] = R;
            arr[1] = G;
            arr[2] = B;
        }
        // 결과 중 최솟값을 반환한다.
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if(arr[i] < answer) {
                answer = arr[i];
            }
        }
        System.out.println(answer);
    }
}
