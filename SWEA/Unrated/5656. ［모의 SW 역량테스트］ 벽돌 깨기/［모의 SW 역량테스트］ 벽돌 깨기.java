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
            min = Integer.MAX_VALUE;
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();

            // 맵 static으로 입력받기
            // 입력 받을 때 숫자를 다 세줌
            // 0만 있는 배열이 있으면 거기는 N이 떨어질 필요가 없음!! check 배열 생성

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

        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            newMap[i] = Arrays.copyOf(map[i], W);
        }

        // 내려오다가 벽돌 만나면 폭발
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
        for(int j=0; j<W; j++){
            int idx = H-1;
            for(int i=H-1; i>=0; i--){
                if(newMap[i][j] != 0) newMap[idx--][j] = newMap[i][j];
            }
            while(idx>=0) newMap[idx--][j] = 0;
        }


        for(int i=0; i<W; i++){
            blockTest(newMap, i, K+1);
        }


    }

    // map 복제해서 새로운거 만듦
    // 위에서부터 아래로 내려오며 숫자가 1이면 0으로 만들고 cnt 증가
    // 1이 아니면 폭발 진행 -> 폭발 진행 중 1보다 큰 수를 만나면 Queue에 좌표랑 값 넣어두고 0으로 만들고 cnt ++
    // 1을 만나면 0으로 바꾸고 cnt 증가
    // 0을 만나면 안건드림

    // 정렬은 열마다 idx를 0으로 잡고 위로 올라가며 숫자를 만나면 idx에 넣고 idx ++

    // map 정렬 후 N에 도달할 때까지 +1 후 다시 호출
    // N에 도달하면 최대값 - cnt 뺸 값을 리턴함. 이게 min보다 작으면 min을 교체
}
