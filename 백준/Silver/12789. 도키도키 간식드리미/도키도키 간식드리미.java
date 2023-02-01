import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		// LIFO인 stack과 FIFO인 queue를 선언
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> queue = new LinkedList<>();
		
		int cnt = 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력값을 순서대로 queue에 저장
		while(st.hasMoreTokens()) {
			queue.add(Integer.parseInt(st.nextToken()));
		}
		
		// queue에 값이 없을 때까지 반복 진행
		while(!queue.isEmpty()) {
			
			// queue의 peek값과 cnt를 비교해서 같을 경우 queue값을 빼주고 cnt를 증가시킴
			if(queue.peek() == cnt) {
				queue.poll();
				cnt++;
			// queue의 peek값과 cnt가 다를 경우 stack의 peek 값을 확인
			} else if(!stack.isEmpty() && stack.peek() == cnt) {
				stack.pop();
				cnt++;
				
			// 둘 다 cnt값과 다를 경우 stack에 queue 값을 넣어줌
			} else {
				stack.push(queue.poll());
			}
		}
		
		// stack에 남은 원소들이 있으면 cnt값과 비교
		while(!stack.isEmpty()) {
			if(stack.peek() == cnt) {
				stack.pop();
				cnt++;
			// 실패하면 Sad를 return
			} else {
				System.out.println("Sad");
				return;
			}
		}
		// 성공하면 Nice를 반환
		System.out.println("Nice");
	}
}

