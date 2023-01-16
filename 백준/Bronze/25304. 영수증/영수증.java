import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int total = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		int totalSum = 0;
		
		for (int i=0; i<count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int price = Integer.parseInt(st.nextToken());
			int product = Integer.parseInt(st.nextToken());
			totalSum += price * product;			
		}
		
		if (total == totalSum) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
	}	
}