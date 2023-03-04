import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 계란의 개수 입력받음
        int N = Integer.parseInt(br.readLine());

        // 계란 2차 배열 생성
        int[][] eggs = new int[N][2];

        // 0번에 계란의 내구도, 1번에 계란의 무게 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        // 백트래킹 시작
        backTracking(eggs, 0, N);

        // max값 출력
        System.out.println(max);
    }

    private static void backTracking(int[][] eggs, int k, int n) {
        if(k == n) {
            // 깨진 계란 세서 비교하기
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if(eggs[i][0] <= 0) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        boolean impossible = true;

        for (int i = 0; i < n; i++) {
            // 손에 든 계란이 깨진 계란이면 break
            if (eggs[k][0] <= 0) break;
            
            // 손에 든 계란으로 못치므로 지나감
            if (i == k) continue;
            
            // 깨진 계란은 못치므로 지나감
            if (eggs[i][0] <= 0) continue;

            impossible = false;
            // 계란 깨주고 다음 계란으로
            eggs[i][0] -= eggs[k][1];
            eggs[k][0] -= eggs[i][1];
            backTracking(eggs, k+1, n);
            
            // 계란 다시 살려냄
            eggs[i][0] += eggs[k][1];
            eggs[k][0] += eggs[i][1];
        }
        // 깨지 못하는 경우에만 다음 계란으로 그냥 넘어가기
        if(impossible) backTracking(eggs, k+1, n);
    }
}