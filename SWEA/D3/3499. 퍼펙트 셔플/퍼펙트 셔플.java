import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1번 큐, 2번 큐를 만든다.
        // (어차피 다 출력하고 나면 queue가 비어있기 때문에 처음에 만들고 사용)
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();

        int testCase = sc.nextInt();
        for(int t=1; t<=testCase; t++){
            // 입력 총 개수
            int strCnt = sc.nextInt();

            // 중간값 (홀수일 경우 올림 / 짝수일 경우에는 절반)
            int middle = (int)Math.ceil((double) strCnt/2);

            // 1번 큐에 (총 개수 / 2)를 올림한 값을 넣고 2번 큐에 나머지 값을 넣는다.
            for(int i=0; i<middle; i++){
                q1.offer(sc.next());
            }
            for(int i=middle; i<strCnt; i++){
                q2.offer(sc.next());
            }

            // 1번 -> 2번 순으로 출력한다. (2번이 빌 때까지)
            String answer = "";
            while(!q2.isEmpty()){
                answer += q1.poll() + " ";
                answer += q2.poll() + " ";
            }
            // 1번에 남아있으면 1번을 출력한다.
            if(!q1.isEmpty()) answer += q1.poll();

            System.out.printf("#%d %s\n", t, answer);
        }
    }
}
