import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		int N = Integer.parseInt(input.split(" ")[0]);
		int K = Integer.parseInt(input.split(" ")[1]);
		
		int[] arr = new int[N];
		boolean[] check = new boolean[N];
		
		for(int i=1; i<=N; i++) {
			arr[i-1] = i;
		}
		
		bw.write("<");
		int idx = 0;
		int cnt = -1;
		int removeCnt = 1;
		while(N > removeCnt) {
			if(!check[idx % N]) {
				cnt++;
			}
			
			if(!check[idx%N] && cnt%K == K-1) {
				check[idx % N] = true;
				bw.write(arr[idx % N] + ", ");
				removeCnt++;
			}
			
			idx++;
		}
		for(int i=0; i<N; i++) {
			if(!check[i]) {
				bw.write(arr[i] + ">");
			}
		}
		
		bw.close();
	}
}
