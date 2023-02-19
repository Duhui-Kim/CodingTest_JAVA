import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
			
		int bar = 0;
		int cnt = 0;
		String str = br.readLine();		
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			switch(str.charAt(i)) {
			case '(':
				if(stack.isEmpty()) {
					stack.push(str.charAt(i));
				} else if(stack.peek() == '(') {
					stack.push(str.charAt(i));
					bar++;
				} else {
					stack.push(str.charAt(i));
				}
				break;
			case ')':
				if(stack.peek() == '(') {
					stack.push(str.charAt(i));
					cnt += bar;
				} else if (stack.peek() == ')') {
					stack.push(str.charAt(i));
					bar--;
					cnt++;
				}
				break;
			}
		}	
		System.out.println(cnt);
	}
}
