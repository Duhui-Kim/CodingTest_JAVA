import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int min = Integer.MAX_VALUE;
    private static int[][] arr2;
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        check = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }

        arr2 = new int[N][N];
        int sumOfNum = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr2[i][j] = Integer.parseInt(st.nextToken());
                sumOfNum += arr2[i][j];
            }
        }

        int[] A = new int[arr.length/2];

        selectHalf(arr, A, sumOfNum, 0, arr.length/2);

        System.out.println(min);
    }

    private static void selectHalf(int[] arr, int[] A, int sumOfNum, int k, int N) {
        if(k == N) {
            int[] tmp = arr.clone();
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length/2; j++) {
                    if(tmp[i] == A[j]) {
                        tmp[i] = -1;
                    }
                }
            }
            int answer = Math.abs(selectTwo(tmp, arr2) - selectTwo(A, arr2));
            min = min > answer? answer: min;
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(k==0) {
                A[k] = i;
                selectHalf(arr, A, sumOfNum,k+1, N);
            }
            else if (A[k-1] < i) {
                A[k] = i;
                selectHalf(arr, A, sumOfNum,k+1, N);
            }
        }

    }

    // 주어진 배열에서 2개를 골라 값을 더하는 method
    private static int selectTwo(int[] arr, int[][] arr2) {
        int answer = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] != -1 && arr[j] != -1) {
                    answer += arr2[arr[i]][arr[j]] + arr2[arr[j]][arr[i]];
                }
            }
        }
        return answer;
    }

}
