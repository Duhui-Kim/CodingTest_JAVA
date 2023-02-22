import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 테스트케이스마다 같은 Deque를 써주려고 선언한다.
        ArrayDeque<Character> queue = new ArrayDeque<>();

        for(int t=1; t<=10; t++){
            int strSize = sc.nextInt();
            String strInput = sc.next();
            // true / false를 체크할 flag를 선언한다.
            boolean flag = true;

            loop:
            for(int i=0; i<strSize; i++){
                switch (strInput.charAt(i)) {
                    // 열린 괄호의 경우에는 그냥 넣는다.
                    case '(': case '[': case '<': case '{':
                        queue.offer(strInput.charAt(i));
                        break;
                    // 닫힌 괄호의 경우 마지막으로 들어간 값과 비교하여 쌍이 맞을 경우
                    // 마지막으로 들어간 괄호를 제거한다.
                    case ')':
                        if(queue.peekLast() == '(') queue.pollLast();
                        else {
                            flag = false;
                            break loop;
                        }
                        break;
                    case ']':
                        if(queue.peekLast() == '[') queue.pollLast();
                        else {
                            flag = false;
                            break loop;
                        }
                        break;
                    case '}':
                        if(queue.peekLast() == '{') queue.pollLast();
                        else {
                            flag = false;
                            break loop;
                        }
                        break;
                    case '>':
                        if(queue.peekLast() == '<') queue.pollLast();
                        else {
                            flag = false;
                            break loop;
                        }
                        break;
                }
            }
            // flag에 따라 값을 출력한다.
            if(flag) System.out.printf("#%d %d\n", t, 1);
            else System.out.printf("#%d %d\n", t, 0);

            // 다음 과정을 위해 Deque를 비운다.
            queue.clear();
        }
    }
}
