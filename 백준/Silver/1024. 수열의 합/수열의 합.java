import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		
		int N = Integer.parseInt(input.split(" ")[0]);
		int L = Integer.parseInt(input.split(" ")[1]);
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			int sum = L * (L-1) / 2;
			if(L > 100 || sum > N) {
				System.out.println(-1);
				return;
			} else if (sum == N || (N - sum) % L == 0) {
				int gap = (N - sum) / L;
				
				for(int i=gap; i<L+gap; i++) {
					sb.append(i).append(" ");
				}
				break;
			}
			L++;
		}
		System.out.println(sb);
	}
}