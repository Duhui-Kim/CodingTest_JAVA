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
		int m = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		if(m + T > M) {
			System.out.println(-1);
			return;
		}
		
		int exer = 0;
		int time = 0;
		int cur = m;
		
		while(exer < N) {		
			time++;
			
			if(cur + T <= M) {
				cur += T;
				exer++;
			} else {
				cur -= R;
				if(cur < m) cur = m;
			}
		}
		System.out.println(time);
	}
}