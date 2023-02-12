import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int i=0; i<testCase; i++) {
			N = Integer.parseInt(br.readLine());
			bw.write(factorial(N) + "\n");
		}
		
		bw.close();
	}
	
	// 팩토리얼 진행
	private static int factorial(int n) {
		if(n == 0 || n == 1) return 1;
		
		int k = n * factorial(n-1);
		
		// N이 1000까지이므로 오버플로우를 방지하기 위해 k에 10000 이하의 값만 저장하기
		while(true) {
			if(k%10 != 0) {
				k = k%10000;
				break;
			} else {
				k = k/10;
			}
		}
		
		// 마지막 값에서는 1의 자리를 제외한 모든 값 제거
		if(n == N) {
			while(true) {
				if(k%10 !=0) {
					k=k%10;
					return k;
				} else {
					k/=10;
				}
			}
		} else {
			return k;
		}
	}
}
