import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stack = new Stack<>();

        String str = br.readLine();
        char[] input = str.toCharArray();

        String tmp = "";
        StringBuilder answer = new StringBuilder();

        boolean tag = false;
        for (char c : input) {
            // space를 만났을 경우 sb에 저장된 문자를 뒤집어서 answer에 저장
            if (!tag && c == ' ') {
                while (!stack.isEmpty()) answer.append(stack.pop());
                answer.append(c);

            /*
            여는 괄호를 만났을 경우 sb에 담겨있던 것들을 뒤집어서 answer에 저장하고 tag를 true로 바꾼다.
            그리고 괄호도 answer에 넣는다.
             */

            } else if (c == '<') {
                while (!stack.isEmpty()) answer.append(stack.pop());
                tag = true;
                answer.append(c);

            /*
            닫는 괄호를 만나면 tag를 다시 false로 만들고 괄호를 answer에 저장한다.
             */
            } else if (c == '>') {
                tag = false;
                answer.append(c);

                // tag가 false인 상태는 괄호 안에 있는 것이 아니므로 sb에 저장한다.
            } else if (!tag) {
                stack.push(c);

                // tag가 true인 경우에는 answer에 바로 저장한다.
            } else {
                answer.append(c);
            }
        }
        // stack에 남아있는 문자가 있으면 뒤집어서 answer에 저장하고 출력
        while(!stack.isEmpty()) answer.append(stack.pop());

        System.out.println(answer);
    }
}
