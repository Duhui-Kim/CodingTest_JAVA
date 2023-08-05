import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] input = new char[N][N];
		int[][] friend = new int[N][N];
		
		for(int i=0; i<N; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i == j) continue;
					
					if(input[i][j] == 'Y' || (input[i][k] == 'Y' && input[k][j] == 'Y')) {
						friend[i][j] = 1;
					}
				}
			}
		}
		
		int answer = 0;
		for(int j=0; j<N; j++) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				sum += friend[i][j];
			}
			answer = answer < sum ? sum : answer;
		}
		System.out.println(answer);
	}
}