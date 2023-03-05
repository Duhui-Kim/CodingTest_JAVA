import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();

        // N을 입력받고 별 찍을 준비
        char[][] arr = new char[N][2*N-1];

        // 제일 큰 삼각형 별 미리 찍어놓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                if(j+i >= N-1 && j-i <= N-1) arr[i][j] = '*';
                else arr[i][j] = ' ';
            }
        }

        // 공백 만들러 가기
        makeStar(arr, 0, 0, N);

        // 별 출력 준비
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        // 출력!
        System.out.println(sb);
    }

    private static void makeStar(char[][] arr, int x, int y, int N) {

        // N이 3일 경우는 가운데만 점 찍기
        if(N == 3) {
            arr[x+1][y+2] = ' ';
            return;
        }

        // 3보다 클 경우는 공백 채우기
        for (int i = x + N/2, k = 0; i < x + N; i++, k++) {
            for (int j = y + N/2 + k; j < y + 3 * N/2 - 1- k; j++) {
                arr[i][j] = ' ';
            }
        }

        // 3개의 범위로 나눠서 또 찍기
        makeStar(arr, x, y + N/2, N/2);
        makeStar(arr, x + N/2, y, N/2);
        makeStar(arr, x + N/2, y + N, N/2);

    }
}