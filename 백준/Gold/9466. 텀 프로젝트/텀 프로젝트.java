import javax.naming.PartialResultException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] answer = new int[M];

        // N과 M, answer 배열로 backTracking 시작
        backTracking(answer, N, 0, sb);
        
        // 결과 출력
        System.out.println(sb);
    }

    // backTracking method
    private static void backTracking(int[] answer, int n, int m, StringBuilder sb) {
        
        // m이 answer 크기와 같아지면 종료
        if(m == answer.length) {
            // answer의 값들을 sb에 저장
            for (int i = 0; i < answer.length; i++) {
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
            return;
        }

        // m이 answer보다 작을 경우 진행
        for (int i = 1; i <= n; i++) {
            // 아무것도 안들어있으면 모든 i를 넣을 수 있음
            if(m == 0) {
                answer[m] = i;
                backTracking(answer, n, m+1, sb);
                
            // 무언가가 들어있을 때 앞의 숫자보다 크거나 같은 수만 넣을 수 있음
            } else {
                if (answer[m-1] <= i) {
                    answer[m] = i;
                    backTracking(answer, n, m+1, sb);
                }
            }
        }
    }
}
