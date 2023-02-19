import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		
		int x = 1;
		int sum = 0;
		boolean isRight = true;
		
		int smallCnt = 0;
		int bigCnt = 0;
		loop:
		for(int i=0; i<str.length(); i++) {
			switch(str.charAt(i)) {
			case '(':
				smallCnt++;
				stack.push('(');
				x *= 2;
				break;
			case '[':
				bigCnt++;
				stack.push('[');
				x *= 3;
				break;
			case ')':
				smallCnt--;
				if(stack.isEmpty()) {
					isRight = false;
					break loop;
				}
				if(stack.peek() == '(') sum += x;
				else if(stack.peek() == '[') isRight = false;
				stack.push(')');
				x /= 2;
				break;
			case ']':
				bigCnt--;
				if(stack.isEmpty()) {
					isRight = false;
					break loop;
				}
				if(stack.peek() == '[') sum += x;
				else if(stack.peek() == '(') isRight = false;
				stack.push(']');
				x /= 3;
				break;
			}
		}
		if(isRight && smallCnt == 0 && bigCnt == 0) System.out.println(sum);
		else System.out.println(0);
	}
}
