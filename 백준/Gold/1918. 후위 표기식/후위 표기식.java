import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 결과를 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 연산자를 저장할 stack
        ArrayDeque<Character> stack = new ArrayDeque<>();

        // Input을 char 배열로 변환한다.
        char[] input = br.readLine().toCharArray();

        for (int i = 0; i < input.length; i++) {
            if(input[i] - 'A' >= 0) {
                sb.append(input[i]);
            } else {
                // stack이 비어있을 경우 연산자 넣어주기
                if(stack.isEmpty()) {
                    stack.offerLast(input[i]);

                // stack이 비어있지 않은 경우 계산하기
                } else {
                    // input이 +나 -면 괄호가 아닌 모든 수를 sb에 더해주기
                    if(input[i] == '+' || input[i] == '-') {
                        while(!stack.isEmpty() && stack.peekLast() != '(') {
                            sb.append(stack.pollLast());
                        }
                        // 그다음 연산자를 stack에 넣어주기
                        stack.offerLast(input[i]);

                    // 곱하기나 나누기면, stack에 +나 -가 들어있거나 (가 들어있으면 stack에 넣어주고
                    // *나 /가 들어있으면 stack에 있는 것을 sb에 더해주고 자기는 stack에 넣는다.
                    } else if (input[i] == '*' || input[i] == '/') {
                        if(stack.peekLast() == '+' || stack.peekLast() == '-' || stack.peekLast() == '(') {
                            stack.offerLast(input[i]);
                        } else {
                            sb.append(stack.pollLast());
                            stack.offerLast(input[i]);
                        }

                    // 여는 괄호면 stack에 넣어준다.
                    } else if (input[i] == '(') {
                        stack.offer(input[i]);

                    // 남은 것은 닫는 괄호인데, 여는 괄호가 나올 때까지 stack에 있는 연산자를 빼준다.
                    // 괄호도 빼준다.
                    } else {
                        while (stack.peekLast() != '(') {
                            sb.append(stack.pollLast());
                        }
                        stack.pollLast();
                    }
                }
            }
        }
        // stack에 남아있는 연산자를 sb에 더해주면 종료
        while (!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        System.out.println(sb);
    }
}