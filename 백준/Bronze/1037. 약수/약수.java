import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		while(N-- > 0) {
			int nxt = Integer.parseInt(st.nextToken());
			
			max = max < nxt ? nxt : max;
			min = min > nxt ? nxt : min;
		}
		System.out.println(max * min);
	}
}