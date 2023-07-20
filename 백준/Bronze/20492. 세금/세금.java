import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		double N = Double.parseDouble(br.readLine());
		
		System.out.println(Math.round(N * 0.78) + " " + Math.round(N * 0.8 + (N * 0.2) * 0.78));
	}
}