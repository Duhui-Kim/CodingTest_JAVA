import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int C = Integer.parseInt(br.readLine());
		int m = C/60;
		int s = C%60;

		if (B + s >= 60) {
			if (A+m+1 >= 24) {
				System.out.printf("%d %d", A+m+1-24, (B+s)%60);
			} else {
				System.out.printf("%d %d", A+m+1, (B+s)%60);		
			}
		} else {
			if (A+m >= 24) {
				System.out.printf("%d %d", A+m-24, B+s);
			} else {
				System.out.printf("%d %d", A+m, B+s);		
			}
		}
	}
}