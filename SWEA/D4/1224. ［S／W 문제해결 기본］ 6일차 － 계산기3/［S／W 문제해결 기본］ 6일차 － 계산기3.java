import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//      테케 10 진행
        for(int t=1; t<=10; t++) {

            int size = sc.nextInt();
            String input = sc.next();

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            Stack<Integer> tmp = new Stack<>();

            // 입력된 문자를 int 형태로 저장하기 위해 '0'을 빼주면
            // (는 -8, *는 -6, +는 -5
            int bracket = -8;
            int multiSign = -6;
            int addSign = -5;

            for (int i = 0; i < size; i++) {
                // 일단 숫자는 모두 queue에 저장
                if (input.charAt(i) >= 48) {
                    queue.add(input.charAt(i) - '0');
                    continue;
                }
                // 연산자일 경우 진행
                // 비어있다면 tmp에 연산자 넣어주기
                if (tmp.isEmpty()) {
                    tmp.push(input.charAt(i) - '0');
                    continue;
                }
                switch (input.charAt(i)) {
                    // 입력된 연산자가 *이고 tmp에도 *가 있다면 queue에 넣는다. 그렇지 않으면 그냥 tmp에 넣는다.
                    case '*':
                        if (tmp.peek() == multiSign) queue.add(input.charAt(i) - '0');
                        else tmp.push(input.charAt(i) - '0');
                        break;
                    // 입력된 연산자가 +이고 tmp에도 +나 *가 있다면 queue로 옮기고 입력 연산자는 tmp에 넣는다.
                    case '+':
                        if (tmp.peek() == addSign || tmp.peek() == multiSign) {
                            queue.add(tmp.pop());
                            tmp.push(input.charAt(i) - '0');
                        } else {
                            tmp.push(input.charAt(i) - '0');
                        }
                        break;
                    // 입력된 연산자가 (이면 그냥 tmp에 넣는다.
                    case '(':
                        tmp.push(input.charAt(i) - '0');
                        break;
                    // 입력된 연산자가 )이면 (를 만날 때까지 tmp의 연산자를 queue로 옮긴다.
                    case ')':
                        while (tmp.peek() != bracket) {
                            queue.add(tmp.pop());
                        }
                        tmp.pop();
                }
            }

            // 현재 queue는 후위연산으로 정리 완료
            int cal = 0;

            // 후위표현식 계산하기
            // queue가 빌 때까지 반복한다.
            while (!queue.isEmpty()) {

                // 숫자는 answer에 넣는다.
                if (queue.peek() >= 0) {
                    tmp.push(queue.pop());

                // 연산자가 나오면 answer에서 두 개 꺼내서 계산 후 answer에 넣는다
                } else {
                    if (queue.peek() == multiSign) {
                        queue.poll();
                        cal = tmp.pop() * tmp.pop();
                        tmp.push(cal);
                    } else {
                        queue.poll();
                        cal = tmp.pop() + tmp.pop();
                        tmp.push(cal);
                    }
                }
            }
            // 마지막에 tmp에 넣은 값이 최종값이므로 cal을 반환한다.
            System.out.printf("#%d %d\n", t, cal);
        }
    }
}