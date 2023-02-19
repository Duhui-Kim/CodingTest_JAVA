import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		LinkedList<Integer> deque = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			deque.offer(i);
		}
		
		int test = Integer.parseInt(st.nextToken());
		
		int moveCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<test; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = deque.indexOf(num);

			if(idx >= deque.size()-idx) {
				while(deque.peekFirst() != num) {
					deque.offerFirst(deque.pollLast());
					moveCnt++;
				}
				deque.pollFirst();
				
			} else {
				while(deque.peekFirst() != num) {
					deque.offerLast(deque.pollFirst());
					moveCnt++;
				}
				deque.pollFirst();
			}
		}
		System.out.println(moveCnt);
	}
}
