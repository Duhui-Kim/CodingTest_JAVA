import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static boolean swap = true;
	private static BigInteger n_1 = new BigInteger("0");
	private static BigInteger n_2 = new BigInteger("1");
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		fibonacci(N);
		System.out.println(sb);
	}
	
	private static void fibonacci(int n) {
		
		if(n==0) {
			sb.append("0");
			return;
		}
		else if(n==1) {
			sb.append("1");
			return;
		}
		// n_1 : 0, n_2 : 1	swap:true
		// n_1 : 1, n_2 : 1 swap:false
		// n_1 : 1, n_2 : 2 swap:true
		// n_1 : 3, n_2 : 2 swap:false
		while(n>=2) {
			if(swap) {
				n_1 = n_2.add(n_1);
				swap = false;
			} else {
				n_2 = n_1.add(n_2);
				swap = true;
			}
			n--;
		}
		// 마지막 수만 더해줌
		if(swap) {
			sb.append(n_2);
		} else {
			sb.append(n_1);
		}
	}
}
