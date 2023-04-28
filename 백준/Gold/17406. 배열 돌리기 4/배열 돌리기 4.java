import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    1. 회전시키는 연산 만들기
    2. 회전연산 순서 정하기 위한 nextPermutation 구현하기
     */
    private static class Ro {
        int r, c, s;
        public Ro(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    private static int[][] map;
    private static int[][] tmpMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        tmpMap = new int[N+1][M+1];

        // 맵 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전연산 입력받기
        Ro[] rotates = new Ro[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotates[i] = new Ro(r, c, s);
        }

        // 조합
        int[] permutation = new int[K];
        for (int i = 0; i < K; i++) permutation[i] = i;

        // 최솟값 저장용
        int answer = Integer.MAX_VALUE;

        // 회전연산의 순서를 바꿔가며 배열의 최솟값 구하기
        do {
            // map 복사
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    tmpMap[i][j] = map[i][j];
                }
            }

            // 회전연산 수행
            for (int i = 0; i < K; i++) {
                rotate(rotates[permutation[i]]);
            }

            // 최솟값 구하기
            int min = cntMap(N, M);

            answer = Math.min(min, answer);

        } while (nextPermutation(permutation, K));

        System.out.println(answer);
    }

    // 각 행 별 최솟값 찾기
    private static int cntMap(int N, int M) {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += tmpMap[i][j];
            }
            min = Math.min(sum, min);
        }
        return min;
    }

    // 조합
    private static boolean nextPermutation(int[] permutation, int k) {
        int idx = k-2;

        while (idx >= 0 && permutation[idx] > permutation[idx+1]) idx--;

        if (idx < 0) return false;

        int tidx = k-1;

        while (permutation[idx] > permutation[tidx]) tidx--;

        swap(permutation, idx, tidx);

        int i = idx+1;
        int j = k-1;

        while (i < j) {
            swap(permutation, i, j);
            i++; j--;
        }

        return true;
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 회전 연산
    private static void rotate(Ro ro) {
        if (ro.s == 0) return;

        // 시작점 빼놓기
        int tmp = tmpMap[ro.r-ro.s][ro.c-ro.s];

        // 왼쪽 모서리 값 이동
        for (int i = ro.r-ro.s; i < ro.r+ro.s; i++) {
            tmpMap[i][ro.c-ro.s] = tmpMap[i+1][ro.c-ro.s];
        }

        // 아래 모서리 값 이동
        for (int i = ro.c-ro.s; i < ro.c+ro.s; i++) {
            tmpMap[ro.r+ro.s][i] = tmpMap[ro.r+ro.s][i+1];
        }

        // 오른쪽 모서리 값 이동
        for (int i = ro.r+ro.s; i > ro.r-ro.s; i--) {
            tmpMap[i][ro.c+ro.s] = tmpMap[i-1][ro.c+ro.s];
        }

        // 위쪽 모서리 값 이동
        for (int i = ro.c+ro.s; i > ro.c-ro.s; i--) {
            tmpMap[ro.r-ro.s][i] = tmpMap[ro.r-ro.s][i-1];
        }

        // 시작점을 끝점에 넣기
        tmpMap[ro.r-ro.s][ro.c-ro.s+1] = tmp;

        // 다음 회전
        rotate(new Ro(ro.r, ro.c, ro.s - 1));
    }
}