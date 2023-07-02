import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[41];
		
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i=3; i<41; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int[] vip = new int[M+1];
		vip[0] = 0;
		
		int num = 1;
		for(int i=1; i<=M; i++) {
			vip[i] = Integer.parseInt(br.readLine());
			num *= dp[vip[i] - vip[i-1] - 1];
		}
		num *= dp[N - vip[M]];
		
		System.out.println(num);
	}
}