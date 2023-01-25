
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		boolean[] Prime = new boolean[M+1];
		
		Prime[0] = true;
		Prime[1] = true;
		
		for(int i=2; i<Math.sqrt(M+1); i++) {
			if(Prime[i] == true) {
				continue;
			}
			for(int j=i*2; j<M+1; j+=i) {
				Prime[j] = true;
			}
		}
		
		for(int i=N; i<=M; i++) {
			if(Prime[i] == false) {
				bw.write(i + "\n");
			}
		}
		bw.flush();
		bw.close();
		
	}
}