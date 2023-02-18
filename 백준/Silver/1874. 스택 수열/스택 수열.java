import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> stack = new Stack<Integer>();

		//개수
		int num = Integer.parseInt(br.readLine());
		
		//1~num까지 들어갈 배열
		int[] list = new int[num];
		
		for (int i=0; i<num; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		br.close();
		
		//수열 index
		int index = 0;
		
		for (int i=1; i<=num; i++) {
			stack.push(i);
			sb.append("+\n");
			while (!stack.isEmpty()) {
				//stack의 탑과 리스트가 같은 경우
				if (stack.peek() == list[index]) {
					stack.pop();
					sb.append("-\n");
					index++;
				} else {
					break;
				}
			}
		}
		
		if (!stack.isEmpty()) {
			System.out.println("NO");
		} else {
			System.out.println(sb);
		}
		
	}
}