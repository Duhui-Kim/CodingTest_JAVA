import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		
		int n = Integer.parseInt(br.readLine());
		/*
		 * 숫자를 입력받고 64로 나눈 나머지를 deque에 저장, 몫을 또 64로 나눈 나머지를 저장
		 * 첫번째와 마지막 값을 차례대로 불러와 같으면 true 반환
		 * 다르면 false를 반환하고 63으로 나눠서 진행(2까지 진행 중 palindrome이 있으면 1 출력 후 deque clear)
		 * 두 번째 진행
		 */
		for(int i=0; i<n; i++) {
			Deque<Integer> deque = new ArrayDeque<>();
			int next = Integer.parseInt(br.readLine());
			boolean isPal = false;
			for(int j=64; j>=2; j--) {
				int find = next;
				while(find>0) {
					deque.add(find%j);
					find /= j;
				}
				while(deque.size() > 1) {
					if(deque.peekFirst()==deque.peekLast()) {
						deque.pollFirst();
						deque.pollLast();
						isPal = true;
					} else {
						isPal = false;
						deque.clear();
						break;
					}
				}
				if(isPal) {
					bw.write(1 + "\n");
					break;
				}
			}
			if(!isPal) {
				bw.write(0+"\n");
			}
		}
		bw.close();
	}
}
