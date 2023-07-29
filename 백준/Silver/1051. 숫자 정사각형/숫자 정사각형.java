import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[N][M];

		for(int i=0; i<N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int max = Math.min(N, M);
		
		int answer = 1;
		
		loop:
		for(int k=max; k>0; k--) {
			for(int i=0; i<=N-k; i++) {
				for(int j=0; j<=M-k; j++) {
					if(arr[i][j] == arr[i][j+k-1] &&
							arr[i][j+k-1] == arr[i+k-1][j+k-1] &&
							arr[i][j] == arr[i+k-1][j]) {
						answer = k * k;
						break loop;
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}