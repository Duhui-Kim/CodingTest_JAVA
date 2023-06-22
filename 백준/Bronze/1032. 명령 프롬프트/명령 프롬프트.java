import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());		
		char[] base = br.readLine().toCharArray();
		
		int length = base.length; 
		
		for(int i=1; i<N; i++) {
			char[] next = br.readLine().toCharArray();
			
			for(int j=0; j<length; j++) {
				if(base[j] == '?') continue;
				
				if(base[j] != next[j])
					base[j] = '?';
			}
		}
		
		System.out.println(String.valueOf(base));
	}
}