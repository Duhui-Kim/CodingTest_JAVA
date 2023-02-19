import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		
		Stack<Integer> stack = new Stack<>();
		
		int num = 0;
		
		for(int i=0; i<test; i++) {
			num = sc.nextInt();
			if(num != 0) {
				stack.push(num);
			} else {
				stack.pop();
			}
		}
		num = 0;
		while(!stack.isEmpty()) {
			num += stack.pop();
		}
		System.out.println(num);
	}
}
