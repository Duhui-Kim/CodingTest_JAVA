import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dice = new int[6];
		
		long minOne = Integer.MAX_VALUE;
		long minTwo = Integer.MAX_VALUE;
		long minThree = Integer.MAX_VALUE;

		for(int i=0; i<6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			minOne = Math.min(minOne, dice[i]);
		}
		
		if(N == 1) {
			int sum = 0;
			int max = 0;
			
			for(int i=0; i<6; i++) {
				sum += dice[i];
				max = Math.max(max, dice[i]);
			}
			System.out.println(sum - max);
			return;
		}
		
		int[] di = {0, 1, 5, 4, 2, 2, 2, 2, 3, 3, 3, 3};
		int[] dj = {1, 5, 4, 0, 0, 1, 4, 5, 0, 1, 4, 5};
		
		for(int i=0; i<12; i++) {
			minTwo = Math.min(minTwo, dice[di[i]] + dice[dj[i]]);
			
			if(i < 4) {
				minThree = Math.min(minThree, dice[di[i]] + dice[dj[i]] + dice[2]);
				minThree = Math.min(minThree, dice[di[i]] + dice[dj[i]] + dice[3]);
			}
		}
		
		long answer = 0;
		// 한 면 : dice = 2일 때 0, 3일 때 2*4 + 1, 4일 때 6*4 + 2*2, 5일 때 12*4 + 3*3
		answer += minOne * ((N-1) * (N-2) * 4 + (N-2) * (N-2));
		// 두 면 : dice = 2일 때 1 * 4 + 0 * 4, 3일 때 2 * 4 + 1 * 4, 4일 때 3 * 4 + 2 * 4
		answer += minTwo * ((N-1) * 4 + (N-2) * 4);
		// 세 면 : 항상 4
		answer += minThree * 4;
		
		System.out.println(answer);
	}
}