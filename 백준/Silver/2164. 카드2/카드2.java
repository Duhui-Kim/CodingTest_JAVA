import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) {
			queue.offerFirst(i);
		}
		
		int num = 0;
		while(!queue.isEmpty()) {
			num = queue.pollLast();
			if(queue.isEmpty()) break;
			num = queue.pollLast();
			if(queue.isEmpty()) break;
			queue.offerFirst(num);
		}
		System.out.println(num);
	}
}
