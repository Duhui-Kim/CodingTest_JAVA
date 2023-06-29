import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long dream1 = 0;
		long dream2 = 0;
		
		long pow_2 = 1;
		for (int i=0; i<W-1; i++) {
			pow_2 *= 2;
		}
		long pow_3 = 1;
		for (int i=0; i<W-1; i++) {
			pow_3 *= 3;
		}
		long col_pow_3 = 1;
		for (int i=0; i<H-1; i++) {
			col_pow_3 *= 3;
		}
		long col_pow_5 = 1;
		for (int i=0; i<H-1; i++) {
			col_pow_5 *= 5;
		}
		
		for(int i=0; i<H; i++) {
			char[] input = br.readLine().toCharArray();
			
			long tmp1 = 0;
			long tmp2 = 0;
			
			for(int j=0; j<W; j++) {
				
				if(input[j] == 'o') {
					tmp1 = tmp1 * 2 + 1;
					tmp2 = tmp2 * 3 + 1;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}
			}
			dream1 = dream1 * 3 + tmp1;
			dream2 = dream2 * 5 + tmp2;
		}
		
		long[][][] real = new long[N][M-W+1][2];
		
		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			
			long tmp1 = 0;
			long tmp2 = 0;
			
			for(int j=0; j<W; j++) {
				if(input[j] == 'o') {
					tmp1 = tmp1 * 2 + 1;
					tmp2 = tmp2 * 3 + 1;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}				
			}
			
			real[i][0][0] = tmp1;
			real[i][0][1] = tmp2;
			
			for(int j=W, k=0; j<M; j++, k++) {
				if(input[k] == 'o') {
					tmp1 = tmp1 - pow_2;
					tmp2 = tmp2 - pow_3;
				}
				
				if(input[j] == 'o') {
					tmp1 = tmp1 * 2 + 1;
					tmp2 = tmp2 * 3 + 1;
				} else {
					tmp1 = tmp1 * 2;
					tmp2 = tmp2 * 3;
				}
				real[i][k+1][0] = tmp1;
				real[i][k+1][1] = tmp2;
			}
		}
		
		int answer = 0;			
		for(int j=0; j<M-W+1; j++) {
			
			long tmp1 = 0;
			long tmp2 = 0;
			
			for(int i=0; i<H; i++) {
				tmp1 = tmp1 * 3 + real[i][j][0];
				tmp2 = tmp2 * 5 + real[i][j][1];
			}
							
			if(tmp1 == dream1 && tmp2 == dream2) {
				answer++;
			}
			
			for(int i=H, k=0; i<N; i++, k++) {
				tmp1 = tmp1 - (col_pow_3 * real[k][j][0]);
				tmp2 = tmp2 - (col_pow_5 * real[k][j][1]);
				
				tmp1 = 3 * tmp1 + real[i][j][0];
				tmp2 = 5 * tmp2 + real[i][j][1];
				
				if(tmp1 == dream1 && tmp2 == dream2) {
					answer++;
				}
			}
		}
			
		System.out.println(answer);
	}
}