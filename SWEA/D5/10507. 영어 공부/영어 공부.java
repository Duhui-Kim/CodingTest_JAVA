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
			int P = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int left = 0;
			int right = 0;
			
			int blank = P;
			
			if(N == 1) {
				sb.append(String.format("#%d %d\n", tc, 1 + P));
				continue;
			}

			int answer = P + 1;
			
			while(right < N-1) {
				if(arr[right+1] - arr[right] - 1 <= blank) {
					blank -= (arr[right+1] - arr[right] - 1);
					right++;
					
				} else {
					blank -= (arr[right+1] - arr[right] - 1);
					blank += (arr[left+1] - arr[left] - 1);
					right++;
					left++;
				}
				answer = Math.max(answer, arr[right] - arr[left] + 1 + blank);
			}
			sb.append(String.format("#%d %d\n", tc, answer));
		}
		System.out.print(sb);
	}
}