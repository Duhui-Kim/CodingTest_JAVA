import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = 0;
		while(true) {
			Stack<Character> stack = new Stack<>();
			boolean isRight = true;

			String str = br.readLine();		
			
			if(str.equals(".")) break;			
			str = addStr(str);
			
			loop:
			for(int i=0; i<str.length(); i++) {
				switch(str.charAt(i)) {
				case '(' : case '[':
					stack.push(str.charAt(i));
					break;
				case ')' : 
					if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
					else {
						isRight = false;
						break loop;
					}
					break;
				case ']':
					if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
					else {
						isRight = false;
						break loop;
					}
					break;
				}
			}
			if(stack.isEmpty() && isRight) sb.append("yes\n");
			else sb.append("no\n");
		}
		System.out.println(sb);
	}
	
	private static String addStr(String string) throws IOException {
		if(string.charAt(string.length()-1) == '.') return string;
		else string += br.readLine();
		return addStr(string);
	}
}
