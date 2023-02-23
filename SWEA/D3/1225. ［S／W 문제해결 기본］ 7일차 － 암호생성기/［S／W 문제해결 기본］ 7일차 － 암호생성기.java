import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();

        for (int test = 0; test < 10; test++) {
            int testCase = sc.nextInt();
            int min = 0;

            // 숫자를 입력받으면서 15로 나눈 몫의 최솟값을 찾는다.
            for (int i = 0; i < 8; i++) {
                queue.offer(sc.nextInt());
                min = min > queue.peek()/15? queue.peek()/15 : min;
            }

            // 몫에 15를 곱한 값을 min으로 잡는다.
            min *= 15;


            // cnt와 num을 임의로 설정하고, 종료조건을 num이 min보다 작아질 때로 설정한다.
            int cnt = 0;
            int num = min+1;
            while (num > min) {
                // queue에서 꺼낸 값에서 cnt를 증가시켜 1, 2, 3, 4, 5를 차례로 빼며 queue에 넣는다.
                num = queue.poll() - (cnt%5 + 1);

                // num이 min보다 작을 경우 num을 min으로 설정한다
                if(num <= min) num = min;

                queue.offer(num);
                cnt++;
            }
            
            // 결과를 출력한다.
            // 이 때 queue에서 꺼낸 값에 min을 빼면 최종 결과가 나온다.
            System.out.print("#" + testCase);
            for (int i = 0; i < 8; i++) {
                System.out.print(" " + (queue.poll()-min));
            }
            System.out.println();
            queue.clear();
        }
    }
}
