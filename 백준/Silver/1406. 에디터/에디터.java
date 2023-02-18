import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> Lstack = new Stack<>();	
		Stack<Character> Rstack = new Stack<>();	
		
		String str = br.readLine();
		
		for(char c : str.toCharArray()) {
			Lstack.add(c);
		}
		
		int test = Integer.parseInt(br.readLine());
		
		for(int t=0; t<test; t++) {
			str = br.readLine();
			switch(str.charAt(0)) {
			
			case 'P':
				Lstack.add(str.charAt(2));
				break;
			case 'L':
				if(!Lstack.isEmpty()) {
					Rstack.add(Lstack.pop());					
				}
				break;
			case 'D':
				if(!Rstack.isEmpty()) {
					Lstack.add(Rstack.pop());
				}
				break;
			case 'B':
				if(!Lstack.isEmpty()) {
					Lstack.pop();
				}
				break;
			}
		}
		while(!Lstack.isEmpty()) Rstack.add(Lstack.pop());
		while(!Rstack.isEmpty()) bw.write(Rstack.pop() + "");
		bw.close();
	}
}
