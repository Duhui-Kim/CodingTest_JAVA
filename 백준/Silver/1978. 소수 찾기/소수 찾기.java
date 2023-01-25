
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int max = 1001;
		boolean[] isPrime = new boolean[max];

		isPrime[0] = isPrime[1] = true;
		
		for(int i=2;i<=Math.sqrt(max-1);i++) {
			if(isPrime[i] == true) {
				continue;
			}

			for(int j= i*2; j < max; j = j + i) {
				isPrime[j] = true;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] numlist = new int[num];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int primeCnt = 0;
		for(int i=0; i<num; i++) {
			if(isPrime[Integer.parseInt(st.nextToken())]) {
				continue;
			} else primeCnt++;
		}
		
		System.out.println(primeCnt);
	}
}
