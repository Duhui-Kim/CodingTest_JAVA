import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
	
		int cnt=0;
		for(int t=0; t<n; t++) {
			String str = br.readLine();		
			Stack<Character> stack = new Stack<>();
			
			for(int i=0; i<str.length(); i++) {
				switch(str.charAt(i)) {
				case 'A':
					if(!stack.isEmpty()) {
						if(stack.peek() != 'A') stack.push('A');
						else stack.pop();
					} else {
						stack.push('A');
					}
					break;
				case 'B' : 
					if(!stack.isEmpty()) {
						if(stack.peek() != 'B') stack.push('B');
						else stack.pop();
					} else {
						stack.push('B');
					}
					break;
				}
			}
			if(stack.isEmpty()) cnt++;		
		}
		System.out.println(cnt);
	}
}
