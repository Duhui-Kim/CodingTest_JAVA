
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		int num = 0;
		boolean[] Prime = new boolean[N+1];
		loop:
		for(int i=2; i<=N; i++) {
			for(int j=i; j<=N; j+=i) {
				if(Prime[j] == false) {
					Prime[j] = true;
					cnt++;
				}
				if(cnt == K) {
					num = j;
					break loop;
				}
			}
		}
		System.out.println(num);
		
	}
}