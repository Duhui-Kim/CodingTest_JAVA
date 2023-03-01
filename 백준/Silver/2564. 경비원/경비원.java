import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 가로 - 세로 길이
        int H = sc.nextInt();
        int V = sc.nextInt();
        
        // 상점의 수
        int N = sc.nextInt();
        
        // 상점의 좌표 모아놓기
        int[][] store = new int[N][2];
        for (int i = 0; i < N; i++) {
            store[i][0] = sc.nextInt();
            store[i][1] = sc.nextInt();
        }
        
        // 경비원의 좌표
        int x = sc.nextInt();
        int y = sc.nextInt();

        // 최단거리 찾기
        int distance = 0;

        // 1이 북, 2가 남, 3이 서, 4가 동
        for (int i = 0; i < N; i++) {
            // 같을 때
            if (store[i][0] == x) {
                distance += Math.abs(store[i][1] - y);

            // 남북 (반대방향에 있을 때)
            } else if ((store[i][0] == 2 && x == 1) || (store[i][0] == 1 && x == 2)) {
                distance += Math.min(store[i][1] + y, 2 * H - store[i][1] - y) + V;

            // 동서 (반대방향에 있을 때)
            } else if ((store[i][0] == 3 && x == 4) || (store[i][0] == 4 && x == 3)) {
                distance += Math.min(store[i][1] + y, 2 * V - store[i][1] - y) + H;

            // 남동
            } else if (store[i][0] == 2 && x == 4) {
                distance += H - store[i][1] + V - y;

            // 동남
            } else if (store[i][0] == 4 && x == 2) {
                distance += V - store[i][1] + H - y;

            // 북동
            } else if (store[i][0] == 1 && x == 4) {
                distance += H - store[i][1] + y;

            // 동북
            } else if (store[i][0] == 4 && x == 1) {
                distance += store[i][1] + H - y;

            // 서남
            } else if (store[i][0] == 3 && x == 2) {
                distance += V - store[i][1] + y;
                
            // 남서
            } else if (store[i][0] == 2 && x == 3) {
                distance += store[i][1] + V - y;
                
            // 서북 + 북서
            } else if ((store[i][0] == 3 && x == 1) || (store[i][0] == 1 && x == 3)) {
                distance += store[i][1] + y;
            }
        }
        System.out.println(distance);
    }
}