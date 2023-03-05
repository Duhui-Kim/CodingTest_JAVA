import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] color = {0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 전체 종이 2차 배열 생성
        int[][] map = new int[N][N];

        // map 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 재귀 호출
        cntColor(map, 0, 0, N, N);

        // 색 출력
        System.out.println(color[0]);
        System.out.println(color[1]);
    }

    private static void cntColor(int[][] map, int s1, int s2, int e1, int e2) {

        // s1부터 e1, s2부터 e2까지 반복문을 실행
        for (int i = s1; i < e1; i++) {
            for (int j = s2; j < e2; j++) {
                
                // s1, s2값과 다른 값이 있으면 네 개로 나눠서 재귀호출
                if(map[s1][s2] != map[i][j]) {
                    int m1 = (s1+e1)/2;
                    int m2 = (s2+e2)/2;

                    cntColor(map, s1, s2, m1, m2);
                    cntColor(map, m1, s2, e1, m2);
                    cntColor(map, s1, m2, m1, e2);
                    cntColor(map, m1, m2, e1, e2);

                    return;
                }
            }
        }
        // 위에서 통과했을 시에는 color cnt++
        color[map[s1][s2]]++;
    }
}