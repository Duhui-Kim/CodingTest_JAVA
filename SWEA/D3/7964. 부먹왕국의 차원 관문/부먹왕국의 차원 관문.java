import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {

            int N = sc.nextInt();
            int D = sc.nextInt();
            
            // map의 0번째 idx와 N+1번째 idx는 1을 넣어둔다.
            int[] map = new int[N + 2];
            map[0] = 1;
            map[N + 1] = 1;

            // 1부터 N까지 map을 입력받는다.
            for (int i = 1; i <= N; i++) {
                map[i] = sc.nextInt();
            }
            
            // cnt를 0으로 초기화하고, 1을 만나면 가장 멀리 있는 D부터 탐색하여
            // 1을 만나면 i를 D로 바꾸고 다시 탐색 진행
            // 1을 만나지 못하면 cnt를 늘리고 i+D를 1로 설정한 뒤 다시 탐색
            // i+D 값이 끝에 도달하면 종료
            int cnt = 0;
            loop:
            for (int i = 0; i <= N + 1; i++) {
                if (map[i] == 1) {

                    for (int j = D; j > 0; j--) {
                        if (i + j == N + 1) break loop;
                        if (i + j < N + 1 && map[i + j] != 0) {
                            i += j - 1;
                            continue loop;
                        }
                    }
                    cnt++;
                    if (i + D <= N) map[i + D] = 1;
                    else {
                        cnt++;
                        break loop;
                    }
                    i += D - 1;
                }
            }
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
