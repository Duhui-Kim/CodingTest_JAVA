import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			long M = Long.parseLong(st.nextToken());
			
			long[] candy = new long[N];
			
			st = new StringTokenizer(br.readLine());
			long max = 0;
			for(int i=0; i<N; i++) {
				candy[i] = Long.parseLong(st.nextToken());
				max = Math.max(max, candy[i]);
			}
			
			long left = 1l;
			long right = max;
			
			long answer = 0;
			
			while(left <= right) {
				long mid = (left + right) / 2;
				long sum = 0l;
				
				for(int i=0; i<N; i++) {
					sum += (candy[i] / mid);
				}
				if(sum >= M) {
					answer = mid;
					left = mid+1;
				} else {
					right = mid-1;
				}
			}
			sb.append(String.format("#%d %d\n", tc, answer));
		}
		System.out.print(sb);
	}
}