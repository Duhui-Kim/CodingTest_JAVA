import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		Stack<Character> stack = new Stack<>();
		
		String parenthesis = "";
		loop:
		for(int i=0; i<n; i++) {
			parenthesis = br.readLine();
			for(int j=0; j<parenthesis.length(); j++) {
				if(parenthesis.charAt(j) == '(') {
					stack.add('(');
				} else {
					if(stack.isEmpty()) {
						bw.write("NO\n");
						continue loop;
					} else {
						stack.pop();
					}
				}
			}
			if(stack.isEmpty() == true) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
			stack.clear();
		}
		bw.close();
	}
}
