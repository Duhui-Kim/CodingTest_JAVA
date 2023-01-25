import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		int N = Integer.parseInt(br.readLine());
	
		boolean[] Prime = new boolean[10000001];
		
		Prime[0] = true;
		Prime[1] = true;
		
		for(int i=2; i<Math.sqrt(10000001); i++) {
			if(Prime[i] == true) {
				continue;
			}
			for(int j=i*2; j<10000001; j+=i) {
				Prime[j] = true;
			}
		}
		
		for(int i=0; i<=N; i++) {
			if(Prime[i] == false) {
				while(N%i == 0) {
					N = N/i;
					bw.write(i + "\n");
				}
			}
			if(N == 1) {
				break;
			}
		}
		bw.flush();
		bw.close();
		
	}
}