import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int[][] arr;
    private static int D;
    private static int W;
    private static int K;
    private static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 보호필름 층 수 D, 보호필름 가로크기 W, 합격기준 K
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[D][W];

            answer = Integer.MAX_VALUE;

            // 보호필름 입력받기
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] color = new int[D];

            backTracking(color, 0, 0);
            sb.append(String.format("#%d %d\n", tc, answer));
        }
        System.out.println(sb);
    }

    private static void backTracking(int[] color, int end, int cnt) {
        if(answer <= cnt) return;

        if(end == D) {
            if(check(color)) {
                answer = Math.min(cnt, answer);
            }
            return;
        }

        // 2일 경우 색칠 X, 0일 경우 A 색칠, 1일 경우 B 색칠
        color[end] = 2;
        backTracking(color, end+1, cnt);

        color[end] = 0;
        backTracking(color, end+1, cnt+1);

        color[end] = 1;
        backTracking(color, end+1, cnt+1);
    }

    private static boolean check(int[] color) {
        for (int i = 0; i < W; i++) {
            // 통과 여부 확인을 위한 flag
            // 같은 숫자 연속여부 확인을 위한 cnt와 이전 값과 비교하기 위해 pre 선언
            boolean isTrue = false;
            int cnt = 0;
            int pre = 0;
            for (int j = 0; j < D; j++) {
                // color[j]가 2이면 색칠 안한 것이므로 arr의 값 사용
                if(color[j] == 2) {
                    // j가 0일 때는 시작 값을 pre에 저장하고 시작
                    if(j == 0) {
                        pre = arr[j][i];
                        cnt++;
                    }
                    // 0이 아닐 때는 pre와 비교해서 같을 경우 cnt 증가
                    // 다를 경우 pre를 해당 값으로 바꾸고 cnt를 1으로 바꾸기
                    else {
                        if(pre == arr[j][i]) {
                            cnt++;
                        } else {
                            pre = arr[j][i];
                            cnt = 1;
                        }
                    }
                }
                // color[j]가 2가 아니면 0 또는 1로 색칠된 것이므로 color 사용
                else {
                    if(j == 0) {
                        pre = color[j];
                        cnt++;
                    } else {
                        if(pre == color[j]) {
                            cnt++;
                        } else {
                            pre = color[j];
                            cnt = 1;
                        }
                    }
                }
                // cnt가 K보다 커지면 isTrue를 true로 바꾸고 다음 열 검사
                if(cnt >= K) {
                    isTrue = true;
                    break;
                }
            }
            // 이번 열에서 통과하지 못했다면 false 반환
            if (!isTrue) return false;
        }
        // 모두 통과했다면 true 반환
        return true;
    }

}