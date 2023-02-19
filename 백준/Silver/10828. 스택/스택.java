import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i=0; i<test; i++) {
			String str = br.readLine();
			if(str.split(" ")[0].equals("push")) stack.push(Integer.parseInt(str.split(" ")[1]));
			else if(str.split(" ")[0].equals("top")) {
				if(stack.isEmpty()) sb.append("-1\n");
				else sb.append(stack.peek() + "\n");
			}
			else if(str.split(" ")[0].equals("size")) sb.append(stack.size() + "\n");
			else if(str.split(" ")[0].equals("empty")) {
				if(stack.isEmpty()) sb.append("1\n");
				else sb.append("0\n");
			}
			else if(str.split(" ")[0].equals("pop")) {
				if(stack.isEmpty()) {
					sb.append("-1\n");
				} else {
					sb.append(stack.pop() + "\n");
				}
			}
		}
		System.out.println(sb);
	}
}
