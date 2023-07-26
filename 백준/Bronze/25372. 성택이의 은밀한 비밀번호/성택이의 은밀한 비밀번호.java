import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i=0; i<N; i++) {
			int next = sc.next().length();
			if(next < 6 || next > 9) {
				System.out.println("no");
			} else {
				System.out.println("yes");
			}
		}
	}
}