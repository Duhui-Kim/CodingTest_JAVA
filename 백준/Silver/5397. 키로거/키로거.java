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
		
		Stack<Character> Lstack = new Stack<>();
		Stack<Character> Rstack = new Stack<>();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			String str = br.readLine();
			for(int i=0; i<str.length(); i++) {
				switch(str.charAt(i)){
				case '<':
					if(!Lstack.isEmpty()) Rstack.push(Lstack.pop()); 
					break;
				case '>':
					if(!Rstack.isEmpty()) Lstack.push(Rstack.pop()); 
					break;
				case '-':
					if(!Lstack.isEmpty()) Lstack.pop();
					break;
				default:
					Lstack.push(str.charAt(i));
				}
			}
			while(!Lstack.isEmpty()) Rstack.push(Lstack.pop());
			while(!Rstack.isEmpty()) bw.write(Rstack.pop()+"");
			bw.write("\n");
		}
		bw.close();
		
	}
}
