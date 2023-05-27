import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[][] answer = new long[2][10];
		
		for(int i=0; i<10; i++) {
			answer[0][i] = 10 - i;
		}
		
		int cnt = 0;
		long num = 0;
		while(N-- > 0) {
			
			for(int i=0; i<10; i++) {
				num += answer[cnt][i];
			}
			
			for(int i=0; i<10; i++) {
				answer[(cnt+1) % 2][i] = num % 10007;
				num -= answer[cnt][i];
			}
			
			num = 0;
			cnt = (cnt+1) % 2;
		}	
		
		System.out.println(answer[(cnt+1)%2][0]%10007);
	}
}
