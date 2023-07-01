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
			int L = Integer.parseInt(br.readLine());
			int N = Integer.parseInt(br.readLine());
			
			int[] start = new int[N];
			int[] end = new int[N];
			int[] gap = new int[N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				start[i] = a;
				end[i] = b;
				if(i > 0) {
					gap[i] = start[i] - end[i-1] + gap[i-1];
				}
			}
			
			int max = 0;
			
			for(int i=0; i<N; i++) {
				int left = start[i];
				int right = left + L;
				
				int idx = binarySearch(start, 0, N-1, right);
				
				int sum = L - (gap[idx] - gap[i]);
				if(right > end[idx]) sum = sum - (right - end[idx]);
				
				max = max < sum ? sum : max;
			}
			sb.append(String.format("#%d %d\n", tc, max));
		}
		System.out.print(sb);
	}
	private static int binarySearch(int[] arr, int i, int j, int k) {
		int st = i;
		int ed = j;
		
		while(st < ed) {
			int mid = (st + ed + 1) / 2;
			
			if(arr[mid] == k) {
				return mid;
			} else if (arr[mid] < k) {
				st = mid;
			} else {
				ed = mid - 1;
			}
		}
		return st;
	}
}