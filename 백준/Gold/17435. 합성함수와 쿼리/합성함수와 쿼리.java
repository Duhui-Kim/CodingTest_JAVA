import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] input = new int[N+1];	
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] sparse = new int[N+1][20];
		
		for(int i=1; i<=N; i++) {
			sparse[i][0] = input[i];
		}
		
		for(int j=1; j<20; j++) {
			for(int i=1; i<=N; i++) {
				sparse[i][j] = sparse[sparse[i][j-1]][j-1];				
			}
		}
		
		int M = Integer.parseInt(br.readLine());	
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			for(int j=19; j>=0; j--) {
				if(n >= 1 << j) {
					x = sparse[x][j];
					n -= 1 << j;
				}
			}
			sb.append(x).append("\n");
		}
		System.out.println(sb);
	}
}