import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N+1];
        int[][] map = new int[1001][1001];

        for (int t = 1; t <= N; t++) {

            int x = sc.nextInt();
            int y = sc.nextInt();
            int width = sc.nextInt();
            int height = sc.nextInt();

            for (int i = x; i < x+width; i++) {
                for (int j = y; j < y+height; j++) {
                    // 대체되는 부분의 넓이는 빼준다.
                    arr[map[i][j]]--;
                    // map[i][j]를 지금의 값으로 바꾸고
                    map[i][j] = t;
                    // 새로운 넓이를 늘려준다.
                    arr[map[i][j]]++;
                }
            }
        }
        // 1번 idx부터 N번 idx까지 출력한다.
        for (int i = 1; i <= N; i++) {
            System.out.println(arr[i]);
        }
    }
}
