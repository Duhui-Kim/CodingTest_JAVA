import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int t=1; t<=10; t++){
            // 문제의 입력에서 주어진 값들을 입력받는다.
            int size = sc.nextInt();
            String input = sc.next();

            // Stack을 선언하고 값들을 모두 저장한다.
            // 계산하기 편하게 하기 위해 역순으로 넣어줬다.
            Stack<Character> stack = new Stack<>();
            for(int i=size-1; i>=0; i--){
                stack.push(input.charAt(i));
            }
            // answer와 tmp를 선언한다.
            int answer = 0;
            int tmp = 0;

            /*
             * stack에서 값을 꺼내서 비교한다.
             * 1. 값이 +이면 answer에 tmp 값을 더해준다.
             * 2. 값이 *이면 tmp에 stack의 다음 값을 곱해준다.
             * 3. 숫자이면 tmp에 그 숫자를 대입한다.
             */
            while (!stack.isEmpty()){
                char nextVal = stack.pop();

                if(nextVal == '+') answer += tmp;
                else if (nextVal == '*') tmp *= Integer.parseInt(stack.pop() + "");
                else tmp = Integer.parseInt(nextVal + "");
            }

            // 종료 후에 마지막으로 tmp에 입력되어있는 값을 answer에 더해준다.
            answer += tmp;
            System.out.printf("#%d %d\n", t, answer);
        }
    }
}
