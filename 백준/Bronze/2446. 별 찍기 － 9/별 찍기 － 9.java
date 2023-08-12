import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<i; j++) {
				sb.append(" ");
			}
			for(int j=0; j<N*2-1-2*i; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		for(int i=N-2; i>=0; i--) {
			for(int j=0; j<i; j++) {
				sb.append(" ");
			}
			for(int j=0; j<N*2-1-2*i; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}