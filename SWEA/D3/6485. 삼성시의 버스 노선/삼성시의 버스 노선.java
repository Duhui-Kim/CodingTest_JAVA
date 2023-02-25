import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            // bus의 최솟값 최댓값을 저장할 배열을 만들고,
            // 최솟값과 최댓값을 입력받는다.
            int N = sc.nextInt();
            int[][] bus = new int[N][2];
            
            for (int i = 0; i < N; i++) {
                bus[i][0] = sc.nextInt();
                bus[i][1] = sc.nextInt();
            }

            // 사람을 입력받으며 최솟값, 최댓값 사이일 경우 cnt++;
            int P = sc.nextInt();
            int[] answer = new int[P];
            for (int i = 0; i < P; i++) {
                int busStop = sc.nextInt();
                for (int j = 0; j < N; j++) {
                    if(bus[j][0] <= busStop && busStop <= bus[j][1]) {
                        answer[i]++;
                    }
                }
            }
            
            // 결과를 출력한다.
            System.out.printf("#%d ", t);
            for (int i = 0; i < P; i++) {
                System.out.print(answer[i] + " ");
            }
            System.out.println();
        }
    }
}
