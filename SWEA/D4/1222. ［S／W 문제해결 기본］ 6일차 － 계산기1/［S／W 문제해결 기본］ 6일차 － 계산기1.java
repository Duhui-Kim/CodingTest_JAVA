import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 10번의 테스트 케이스 진행
		for(int t=1; t<=10; t++) {
			// 문자열의 크기인 size를 입력받는다
			int size = sc.nextInt();
			
			// number를 저장할 numStack과
			// 덧셈을 저장할 addStack을 선언한다.
			Stack<Integer> numStack = new Stack<>();
			Stack<Character> addStack = new Stack<>();
			
			// str의 처음부터 끝까지 순회하며 숫자는 numStack에 기호는 addStack에 추가한다.
			String str = sc.next();
			for(int i=0; i<size; i++) {
				if(str.charAt(i) == '+') {
					addStack.push('+');
				} else {
					numStack.push(Integer.parseInt(str.charAt(i) + ""));
				}
			}
			// 초기값을 numStack의 마지막 값으로 하고
			// 순서대로 꺼내며 덧셈을 진행한다.
			int answer = numStack.pop();
			while(!addStack.isEmpty()) {
				
				if(addStack.pop() == '+') {
					answer += numStack.pop();
				}
			}
			System.out.printf("#%d %d\n", t, answer);
		}
	}
}
