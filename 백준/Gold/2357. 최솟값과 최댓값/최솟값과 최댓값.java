import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] input = new int[N+1];
		int[] minSeg = new int[4 * (N+1)];
		int[] maxSeg = new int[4 * (N+1)];
		
		for(int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		insert(maxSeg, input, 1, 1, N, true);
		insert(minSeg, input, 1, 1, N, false);
		
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int max = findMax(maxSeg, 1, 1, N, a, b);
			int min = findMin(minSeg, 1, 1, N, a, b);
			
			sb.append(min + " " + max + "\n");
		}
		System.out.println(sb);
	}

	private static int findMin(int[] segment, int idx, int st, int ed, int i, int r) {
		if(ed < i || st > r) 
			return Integer.MAX_VALUE;
		
		if(i <= st && ed <= r) {
			return segment[idx];
		}
		
		int mid = (st + ed) / 2;
		
		return Math.min(findMin(segment, idx * 2, st, mid, i, r), findMin(segment, idx * 2 + 1, mid+1, ed, i, r));
	}
	
	private static int findMax(int[] segment, int idx, int st, int ed, int i, int r) {
		if(ed < i || st > r) 
			return Integer.MIN_VALUE;
		
		if(i <= st && ed <= r) {
			return segment[idx];
		}
		
		int mid = (st + ed) / 2;
		
		return Math.max(findMax(segment, idx * 2, st, mid, i, r), findMax(segment, idx * 2 + 1, mid+1, ed, i, r));
	}

	private static int insert(int[] segment, int[] input, int idx, int st, int ed, boolean check) {
		if(st == ed) {
			return segment[idx] = input[st];
		}
		
		int mid = (st + ed) / 2;
		
		if(check) {
			segment[idx] = Math.max(insert(segment, input, idx * 2, st, mid, check), insert(segment, input, idx * 2 + 1, mid+1, ed, check));
		} else {
			segment[idx] = Math.min(insert(segment, input, idx * 2, st, mid, check), insert(segment, input, idx * 2 + 1, mid+1, ed, check));
		}
		
		return segment[idx];
	}
}