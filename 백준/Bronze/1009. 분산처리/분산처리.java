import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int answer = find(a, b);
			
			if(answer == 0) answer = 10;
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static int find(int a, int b) {
		if(b == 1) return a % 10;
		
		int num = find(a, b/2);

		if(b%2 == 0) {
			return (num * num) % 10;
		} else {
			return (a * num * num) % 10;
		}
	}
}