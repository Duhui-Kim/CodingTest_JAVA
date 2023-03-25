import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력될 수 N을 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // 입력을 받을 arr와 정답을 저장할 arr를 선언
        int[] input = new int[N];
        arr = new int[N];

        // 배열 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        arr[0] = input[0];
        int idx = 0;
        for (int i = 1; i < N; i++) {
            // arr의 값보다 input이 더 크면 뒤에 붙이기
            if(arr[idx] < input[i]) {
                arr[++idx] = input[i];
            }
            // 그렇지 않으면 이분탐색을 통해 해당 위치에 넣기
            else {
                arr[binarySearch(input[i], idx)] = input[i];
            }
        }
        // idx까지는 값이 채워져있는 것이므로 idx+1 출력
        System.out.println(idx+1);
    }
    private static int binarySearch(int num, int N) {
        int st = 0;
        int ed = N;
        int mid;

        // binarySearch를 진행하는데,
        // 배열에 있는 해당 수보다 바로 한 단계 큰 수를 대체해야하므로
        // ed = mid로 두었고, ed를 반환함
        while (st < ed) {
            mid = (st + ed)/2;
            if(arr[mid] == num) {
                ed = mid;
                break;
            } else if (arr[mid] < num) {
                st = mid + 1;
            } else {
                ed = mid;
            }
        }
        return ed;
    }
}