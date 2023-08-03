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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
				
		int answer = 0;
		
		if(Integer.bitCount(N) <= K) {
			System.out.println(0);
			return;
		}
		
		for(int i=0; i<=24; i++) {
			if((N & 1 << i) > 0) {
				answer += 1 << i;
				N += 1 << i;
			}
			if(Integer.bitCount(N) <= K) {
				System.out.println(answer);
				return;
			}
		}
	}
}