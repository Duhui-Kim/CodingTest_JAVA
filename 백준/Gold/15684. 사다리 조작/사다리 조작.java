import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int min = Integer.MAX_VALUE;
    private static int C;
    private static int R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사다리의 행의 개수 R, 열의 개수 C, 처음 사다리의 개수 H
        C = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 입력이 1부터 N || K까지 주어지므로 1씩 범위를 늘려줬다.
        int[][] ladder = new int[R+1][C+1];

        // 사다리 입력
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = b+1;
            ladder[a][b+1] = b;
        }

        // 백트래킹 진행
        backTracking(ladder, 0, 0, 0);

        // min이 초기값과 같으면 불가능이므로 -1 출력
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
            
        // min이 어떤 값을 갖고 있다면 출력
        } else {
            System.out.println(min);
        }
    }
    
    private static void backTracking(int[][] ladder, int x, int y, int k) {
        // min보다 k가 크거나 같으면 필요없으므로 return
        if(min <= k) return;
        
        // k가 4 이상이면 -1이므로 return
        if(k >= 4) return;

        // 사다리 성공여부 체크용 boolean
        boolean check = true;
        
        // 각 열마다 체크
        for (int i = 1; i <= C; i++) {
            // 시작점을 i열로 잡고, 행의 시작점은 1이다.
            int start = i;
            int move = 1;
            
            // 행이 맨 아래까지 가면 종료
            while (move <= R) {
                // ladder값이 0이면 한 칸 아래로 내려감
                if(ladder[move][start] == 0) {
                    move++;
                    
                // ladder에 어떤 값이 있으면 해당 열로 이동함
                } else {
                    start = ladder[move][start];
                    move++;
                }
            }
            // 시작점과 끝점이 다르면 실패했으므로 종료
            if(start != i) {
                check = false;
                break;
            }
        }

        // 모든 열이 성공했으면 min에 k를 넣어줌 (위에서 min보다 크거나 같은 값은 걸렀으므로 최솟값)
        if (check == true) {
            min = k;
            return;
        }

        // 중복되지 않게 배열을 순회
        for (int i = 1; i <= R; i++) {
            if(i < x) continue;
            for (int j = 1; j < C; j++) {
                if(i == x && j < y) continue;
                
                // ladder의 값과 +1 값이 0이면 사다리를 놓아준다.
                if(ladder[i][j] == 0 && ladder[i][j+1] == 0) {
                    ladder[i][j] = j+1;
                    ladder[i][j+1] = j;
                    backTracking(ladder, i, j, k+1);
                    ladder[i][j] = 0;
                    ladder[i][j+1] = 0;
                }
            }
        }
    }
}