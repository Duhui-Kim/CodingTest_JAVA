import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		int N = input.length;
		
		HashSet<String> set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			String str = "";
			for(int j=i; j<N; j++) {
				str += input[j];
				set.add(str);
			}
		}
		System.out.println(set.size());
	}
}