import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i=0; i<test; i++) {
			String input = br.readLine();
			if(input.equals("pop")) {
				if(queue.isEmpty()) sb.append("-1\n");
				else sb.append(queue.pollLast() + "\n");
			
			} else if(input.equals("size")) sb.append(queue.size() + "\n");
			
			else if(input.equals("empty")) {
				if(queue.isEmpty()) sb.append("1\n");
				else sb.append("0\n");
				
			} else if(input.equals("front")) {
				if(queue.isEmpty()) sb.append("-1\n");
				else sb.append(queue.peekLast() + "\n");
			
			} else if(input.equals("back")) {
				if(queue.isEmpty()) sb.append("-1\n");
				else sb.append(queue.peekFirst() + "\n");
			
			} else if(input.split(" ")[0].equals("push")) queue.push(Integer.parseInt(input.split(" ")[1]));;
		}
		System.out.println(sb);
	}
}
