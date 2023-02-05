import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		
		if(s.equals("F")) {
			System.out.println("0.0");
			return;
		} else if(s.contains("+")){
			switch (s.charAt(0)) {
			case 'A':
				System.out.println("4.3");
				break;
			case 'B':
				System.out.println("3.3");
				break;
			case 'C':
				System.out.println("2.3");
				break;
			case 'D':
				System.out.println("1.3");
				break;
			}
		} else if(s.contains("0")) {
			switch (s.charAt(0)) {
			case 'A':
				System.out.println("4.0");
				break;
			case 'B':
				System.out.println("3.0");
				break;
			case 'C':
				System.out.println("2.0");
				break;
			case 'D':
				System.out.println("1.0");
				break;
			}
		} else if(s.contains("-")) {
			switch (s.charAt(0)) {
			case 'A':
				System.out.println("3.7");
				break;
			case 'B':
				System.out.println("2.7");
				break;
			case 'C':
				System.out.println("1.7");
				break;
			case 'D':
				System.out.println("0.7");
				break;
			}
		}

	}
}
