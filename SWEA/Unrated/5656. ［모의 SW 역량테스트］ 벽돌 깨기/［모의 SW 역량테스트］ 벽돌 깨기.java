import java.util.*;

public class Solution {
    private static int N;
    private static int W;
    private static int H;
    private static int min;
    private static Queue<Integer> queue = new LinkedList<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        for(int t=1; t<=testCase; t++) {
            // 결과 저장할 min 선언
            min = Integer.MAX_VALUE;
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            // 맵 static으로 입력받기
            int[][] map = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 1 ~ N까지 모든 col을 순회하며 테스트 진행
            for (int i = 0; i < W; i++) {
                blockTest(map, i, 0);
            }
            System.out.printf("#%d %d\n", t, min);
        }
    }

    private static void blockTest(int[][] map, int col, int K) {
        // K가 N과 같으면 개수 세고 종료
        if (K == N) {
            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] != 0) cnt++;
                }
            }
            if (min > cnt) {
                min = cnt;
            }
            return;
        }

        // 새로운 입력된 map을 복제하여 새로운 newMap을 만들어준다.
        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            newMap[i] = Arrays.copyOf(map[i], W);
        }

        // 내려오다가 벽돌 만나면 폭발
        // 1일 경우는 그냥 0으로 만들고, 1보다 클 경우 queue에 넣음
        for (int i = 0; i < H; i++) {
            if (newMap[i][col] == 1) {
                newMap[i][col] = 0;
                break;
            } else if (newMap[i][col] > 1) {
                queue.offer(i);
                queue.offer(col);
                break;
            }
        }

        // 숫자만큼 사방으로 뻗어나가며 벽돌을 깬다.
        // 이 때도 1보다 큰 수는 queue에 담는다.
        while (!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            int num = newMap[x][y];
            newMap[x][y] = 0;
            for(int i=0; i<4; i++){
                for(int j=1; j<num; j++) {
                    int nx = x + dx[i] * j;
                    int ny = y + dy[i] * j;
                    if (nx >= 0 && ny >= 0 && nx < H && ny < W) {
                        if (newMap[nx][ny] == 1) newMap[nx][ny] = 0;
                        else if (newMap[nx][ny] > 1) {
                            queue.offer(nx);
                            queue.offer(ny);
                        }
                    }
                }
            }
        }

        // 정렬 method
        // 열들을 전부 아래로 정렬한다.
        for(int j=0; j<W; j++){
            int idx = H-1;
            for(int i=H-1; i>=0; i--){
                if(newMap[i][j] != 0) newMap[idx--][j] = newMap[i][j];
            }
            while(idx>=0) newMap[idx--][j] = 0;
        }

        // 다음 폭탄을 떨어트리러 간다.
        for(int i=0; i<W; i++){
            blockTest(newMap, i, K+1);
        }
    }
}
