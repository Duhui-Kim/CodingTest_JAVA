import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		
		long max = -5000;
		long tmp = -5000;
		
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		for(int i=0; i<N; i++) {
			long num = Long.parseLong(st.nextToken());
			
			tmp = tmp + num < num ? num : tmp + num;
			max = max < tmp ? tmp : max;
		}
		System.out.println(max);
	}
}