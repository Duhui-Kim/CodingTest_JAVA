import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		int[] num_list = new int[testCase];
		
		for (int i=0; i<testCase; i++) {
			num_list[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(num_list);
		
		for (int e : num_list) {
			sb.append(e + "\n");
		}
		System.out.println(sb);		
	}	
}