import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			long a = Integer.parseInt(st.nextToken());
			long b = Integer.parseInt(st.nextToken());
			
			long answer = 1;
			LinkedList<Long> list = new LinkedList<>();
			
			for(long j=2; j<=a; j++) {
				list.add(j);
			}
			
			for(long j=a; j>=1; j--) {
				answer *= b--;
				
				for(long k : list) {
					if(answer % k == 0) {
						answer /= k;
						list.remove(k);
						break;
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}