import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 배열의 크기 N을 입력받는다.
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열을 생성하고 숫자를 넣어준다.
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 인풋으로 주어질 개수를 입력받는다.
        int M = Integer.parseInt(br.readLine());

        // 입력받으면서 수의 여부를 계산한다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(findNum(arr, Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    private static int findNum(int[] arr, int num) {
        int st = 0;
        int ed = arr.length-1;
        int mid;

        // st가 ed보다 커지면 종료
        while (st <= ed) {
            // mid를 st와 ed의 중간 지점을 잡는다.
            mid = (st + ed) / 2;
            
            // arr[mid]가 찾는 숫자이면 1을 리턴하고 종료
            if(arr[mid] == num) {
                return 1;
            }
            // arr[mid]가 num보다 작으면 st를 mid+1까지 올림
            else if(arr[mid] < num) {
                st = mid + 1;
            }
            // arr[mid]가 num보다 크면 ed를 mid-1까지 내림
            else {
                ed = mid - 1;
            }
        }
        return 0;
    }
}