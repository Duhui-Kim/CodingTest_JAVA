import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		System.out.println(fibonacci(n));
		
	}
	public static int fibonacci(int n) {
		if(n==0) return 0;
		else if (n==1) return 1;
		
		return fibonacci(n-1) + fibonacci(n-2);
	}
}
