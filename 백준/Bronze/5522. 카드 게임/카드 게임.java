import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int cnt = 5;
		
		while(cnt-- > 0) {
			String input = sc.nextLine();
			
			if(input == null) break;
			
			sum += Integer.parseInt(input);
		}
		
		System.out.println(sum);
	}
}