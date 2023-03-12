import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 배열을 두개 만든다.
        // 1. 숫자 저장용 배열
        // 2. 길이 저장용 배열
        int A = Integer.parseInt(br.readLine());
        int[] arr = new int[A];
        int[] len = new int[A];

        // 숫자를 입력받으며 제일 길어질 수 있는 위치값을 저장한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            len[i] = 1;
            for (int j = 0; j <= i; j++) {
                if(arr[i] > arr[j]) {
                    len[i] = Math.max(len[i], len[j]+1);
                }
            }
        }
        // 길이 저장용 배열에서 가장 큰 크기를 반환한다.
        int max = 0;
        for (int i = 0; i < A; i++) {
            max = Math.max(max, len[i]);
        }
        System.out.println(max);
    }
}
