import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		// 숫자 n을 입력받고 초기값을 정해준다.		
		if(n == 1) {
			System.out.println(0);
			return;
		} else if (n == 2) {
			System.out.println(1);
			return;
		}
		
		// 초기값
		int[] ans = new int[n+1];
		ans[0] = 0;
		ans[1] = 0;
		ans[2] = 1;
		
		// 작은 숫자부터 차례로 쌓아나가면서 최적의 값을 배열에 저장한다.
		for(int i=3; i<=n; i++) {
			ans[i] = ans[i-1] + 1;
			
			if(i%2 == 0) {
				if(ans[i] > ans[i/2] + 1) {
					ans[i] = ans[i/2] + 1;
				}
			}
			
			if(i%3 == 0) {
				if(ans[i] > ans[i/3] + 1) {
					ans[i] = ans[i/3] + 1;
				}
			}
		}
		
		// n번째 값을 출력한다.
		System.out.println(ans[n]);
		
	}
}
