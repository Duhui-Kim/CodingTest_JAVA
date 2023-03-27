import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    private static int minPath;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // testCase 입력 받기
        int testCase = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= testCase; tc++) {
            // 고객의 수 N 입력받기
            int N = Integer.parseInt(sc.nextLine());

            StringTokenizer st = new StringTokenizer(sc.nextLine());

            // 시작점과 끝 좌표를 입력받고
            int[] start = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] end = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            // 고객 좌표 저장용 배열
            int[][] customer = new int[2][N];

            // 고객들 좌표를 모두 입력받음
            for (int i = 0; i < N; i++) {
                customer[0][i] = Integer.parseInt(st.nextToken());
                customer[1][i] = Integer.parseInt(st.nextToken());
            }
            // 최적경로 초기화
            minPath = Integer.MAX_VALUE;

            // 조합 담을 배열
            int[] perm = new int[N];
            for (int i = 0; i < N; i++) {
                perm[i] = i;
            }

            do {
                // 시작점과 끝점 저장해두고, 나머지 경로별 거리 저장하기
                int path = Math.abs(start[0] - customer[0][perm[0]]) + Math.abs(start[1] - customer[1][perm[0]]);
                path += Math.abs(end[0] - customer[0][perm[N-1]]) + Math.abs(end[1] - customer[1][perm[N-1]]);

                // 경로 저장하다가 min보다 커지면 해당 조합은 더 안봐도 되므로 끝냄
                for (int i = 0; i < N-1; i++) {
                    path += Math.abs(customer[0][perm[i]] - customer[0][perm[i+1]]) + Math.abs(customer[1][perm[i]] - customer[1][perm[i+1]]);
                    if (path >= minPath) break;
                }

                // 최솟값 저장하기
                minPath = minPath > path ? path : minPath;

            } while (nextPermutation(perm, N));

            // 테스트넘버와 최솟값 저장
            sb.append(String.format("#%d %d\n", tc, minPath));
        }
        // 출력
        System.out.println(sb);
    }
    // 조합 생성하기
    private static boolean nextPermutation(int[] perm, int N) {
        int idx =  N-2;

        while (idx >= 0 && perm[idx] >= perm[idx+1]) idx--;

        if (idx < 0) return false;

        int tIdx = N-1;

        while (perm[idx] >= perm[tIdx]) tIdx--;

        swap(perm, idx, tIdx);

        int i = idx+1;
        int j = N-1;

        while (i < j) {
            swap(perm, i, j);
            i++;
            j--;
        }

        return true;
    }

    // swap method
    private static void swap(int[] perm, int idx, int tIdx) {
        int tmp = perm[idx];
        perm[idx] = perm[tIdx];
        perm[tIdx] = tmp;
    }
}
