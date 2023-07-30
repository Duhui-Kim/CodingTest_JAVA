import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static int answer = 0;
	public static int N;
	public static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(M < 5) {
			System.out.println(0);
			return;
		}
		
		int[] input = new int[N];
		
		for(int i=0; i<N; i++) {
			char[] arr = br.readLine().toCharArray();
			
			int k = 0;
			
			for(int j=0; j<arr.length; j++) {
				k = k | 1 << (arr[j] - 'a');
			}
			input[i] = k;
		}
		
		backTracking(0, 0, 0, input);
		
		System.out.println(answer);
	}

	private static void backTracking(int word, int number, int k, int[] input) {
		if(k > M) {
			return;
		}
		if(word == 26) {
			if(k != M) return;
			
			int cnt = 0;
			for(int i=0; i<input.length; i++) {
				if((input[i] & number) == input[i]) {
					cnt++;
				}
			}
			answer = Math.max(cnt, answer);
			return;
		}
		backTracking(word+1, number | 1 << word, k+1, input);
		backTracking(word+1, number, k, input);
	}
}