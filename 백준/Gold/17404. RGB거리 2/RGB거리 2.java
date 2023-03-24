import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[3][N];

        // 색깔별 집 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
            arr[2][i] = Integer.parseInt(st.nextToken());
        }


        // 첫 번째 집이 각각 R, G, B일 때
        int[][] dpR = new int[3][N];
        int[][] dpG = new int[3][N];
        int[][] dpB = new int[3][N];

        // 다른 집은 시작점으로 선택 못하게 만들기
        dpR[0][0] = arr[0][0];
        dpR[1][0] = 2000;
        dpR[2][0] = 2000;

        // 다른 집은 시작점으로 선택 못하게 만들기
        dpG[0][0] = 2000;
        dpG[1][0] = arr[1][0];
        dpG[2][0] = 2000;

        // 다른 집은 시작점으로 선택 못하게 만들기
        dpB[0][0] = 2000;
        dpB[1][0] = 2000;
        dpB[2][0] = arr[2][0];


        // 1부터 N까지 진행하며 자신과 다른 색깔 집 중 최솟값을 가져와서 더해줌
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dpR[j][i] = Math.min(dpR[(j+1)%3][i-1], dpR[(j+2)%3][i-1]) + arr[j][i];
                dpG[j][i] = Math.min(dpG[(j+1)%3][i-1], dpG[(j+2)%3][i-1]) + arr[j][i];
                dpB[j][i] = Math.min(dpB[(j+1)%3][i-1], dpB[(j+2)%3][i-1]) + arr[j][i];
            }
        }
        // R일 경우 마지막 집이 R인 경우를 제외하고 min 구하기
        int minNum = Math.min(dpR[1][N-1], dpR[2][N-1]);

        // G인 경우도 마지막 집이 G인 경우를 제외하고 min 구하기
        minNum = Math.min(minNum, Math.min(dpG[0][N-1], dpG[2][N-1]));

        // B인 경우도 마지막 집이 B인 경우를 제외하고 min 구하기
        minNum = Math.min(minNum, Math.min(dpB[0][N-1], dpB[1][N-1]));

        System.out.println(minNum);
    }
}