import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] sugar = new int[N+1];
		Arrays.fill(sugar, -1);
		
		sugar[3] = 1;
		if(N>=5) {
			sugar[5] = 1;			
		}
		
		for(int i=3; i<=N; i++) {
			
			int a = sugar[i-3];
			int b = -1;
			if(i-5 >= 0) {
				b = sugar[i-5];				
			}
			
			if(a>0 && b>0) {
				sugar[i] = Math.min(a, b) + 1;
			} else if(a > 0) {
				sugar[i] = a + 1;
			} else if(b > 0) {
				sugar[i] = b + 1;
			}
		}
		System.out.println(sugar[N]);
	}
}
