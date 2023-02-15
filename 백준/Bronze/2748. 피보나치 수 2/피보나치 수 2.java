import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자 n을 입력받고 배열을 만듦
		int n = sc.nextInt();
		long[] fibo = new long[n+1];
		if(n==0) {
			System.out.println(0);
			return;
		}
		if(n==1) {
			System.out.println(1);
			return;
		};
		
		// 0과 1의 값을 1로 초기화해둠
		fibo[0] = 0;
		fibo[1] = 1;
		
		// 반복문 진행
		for(int i=2; i<=n; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		System.out.println(fibo[n]);
	}
}
