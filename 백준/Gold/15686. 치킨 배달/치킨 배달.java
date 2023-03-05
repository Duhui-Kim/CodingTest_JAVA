import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int distance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도의 크기 N과 폐업시키지 않을 치킨집 수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N*N map 생성
        int[][] map = new int[N][N];

        // chicken집의 수 13개보다 작거나 같고,
        // home의 수는 최대 2*N개로 주어짐
        int[][] chicken = new int[13][2];
        int[][] home = new int[2*N][2];

        int chickenCnt = 0;
        int homeCnt = 0;

        // map을 입력받으며 chicken과 home 좌표 입력하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;

                if(num == 1) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    home[homeCnt++] = arr;
                }
                else if (num == 2) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    chicken[chickenCnt++] = arr;
                }
            }
        }
        boolean[] check = new boolean[chickenCnt];

        backTracking(check, home, chicken, homeCnt, chickenCnt, 0, 0, M);

        System.out.println(distance);
    }

    private static void backTracking(boolean[] check, int[][] home, int[][] chicken, int homeCnt, int chickenCnt, int k, int l, int m) {
        if(k == m) {
            int sumDistance = 0;

            // 각 집에 대해서 반복문 실행
            for (int i = 0; i < homeCnt; i++) {
                // num을 큰 수로 선언한다.
                int num = Integer.MAX_VALUE;
                // 살아남은 치킨집과의 거리 중 작은 값을 num에 담는다.
                for (int j = 0; j < chickenCnt; j++) {
                    if(check[j]) {
                        num = Math.min(num, Math.abs(home[i][0] - chicken[j][0]) +  Math.abs(home[i][1] - chicken[j][1]));
                    }
                }
                // 가장 짧은 거리를 더해준다.
                sumDistance += num;
            }

            // 최솟값을 저장한다.
            distance = Math.min(sumDistance, distance);

            return;
        }

        for (int i = 0; i < chickenCnt; i++) {
            if(i < l) continue;
            
            if(!check[i]) {
                check[i] = true;
                backTracking(check, home, chicken, homeCnt, chickenCnt, k+1, i, m);
                check[i] = false;
            }
        }
    }
}