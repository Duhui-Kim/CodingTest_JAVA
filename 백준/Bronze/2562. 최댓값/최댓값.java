import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int cnt = 0;
		for(int i=1; i<=9; i++) {
			int k = Integer.parseInt(br.readLine());
			if(k>max) {
				max = k;
				cnt = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		System.out.println(sb.append(max + "\n" + cnt));
	}
}