import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(findEnd(arr, num) - findStart(arr, num)).append(" ");
        }
        System.out.println(sb);

    }

    private static int findStart(int[] arr, int num) {
        int st = 0;
        int ed = arr.length-1;
        int mid;

        // st가 ed보다 크거나 같기 전까지 진행
        while (st < ed) {
            mid = (st + ed) / 2;

            // num보다 arr가 크거나 같으면 ed에 mid를 넣음. (가장 왼쪽 값을 찾기 위해)
            if(arr[mid] >= num) {
                ed = mid;
            } else {
                st = mid+1;
            }
        }
        // 종료되는 시점은 ed가 찾는 값의 가장 왼쪽에 있을 때 끝남
        return ed;
    }
    private static int findEnd(int[] arr, int num) {
        int st = 0;
        int ed = arr.length-1;
        int mid = 0;

        // st가 ed보다 크거나 같아지면 종료
        while (st < ed) {
            mid = (st + ed) / 2;

            // 가장 오른쪽 값을 찾기 위해
            // arr[mid]가 num보다 작거나 같으면 st를 계속 올려줌
            if(arr[mid] > num) {
                ed = mid;
            } else {
                st = mid+1;
            }
        }
        if(arr[ed] == num) return ed + 1;
        else return ed;
    }
}