
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int max = 10000;
		
		boolean[] Prime = new boolean[max+1];
		
		Prime[0] = Prime[1] = true;
		for(int i=2; i<=Math.sqrt(max); i++) {
			if(Prime[i] == true) {
				continue;
			}
			
			for(int j=i*2; j<max+1; j+=i) {
				Prime[j] = true;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int min_num = Integer.parseInt(br.readLine());
		int max_num = Integer.parseInt(br.readLine());
		
		int primeSum = 0;
		int primeMin = 0;
		
		for(int i=min_num; i<=max_num; i++) {
			if(Prime[i]) {
				continue;
			} else primeSum += i;
		}
		for(int i=min_num; i<=max_num; i++) {
			if(Prime[i] == false) {
				primeMin = i;
				break;
			}
		}
		if(primeMin == 0) {
			System.out.println(-1);
		} else {
			System.out.println(primeSum);
			System.out.println(primeMin);
		}
	}
}
