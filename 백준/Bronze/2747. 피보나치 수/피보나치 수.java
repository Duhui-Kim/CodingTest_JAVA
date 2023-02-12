import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int n = 0;
		int m = 1;
		int tmp;
		for(int i=0; i<N; i++) {
			tmp = n;
			n = n+m;
			m = tmp;
		}
		System.out.println(n);
	}
}
