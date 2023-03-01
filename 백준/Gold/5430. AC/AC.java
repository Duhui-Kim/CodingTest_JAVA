package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		for(int t=0; t<testCase; t++) {
			// 문자열을 입력받는다.
			String input = br.readLine();
			
			int n = Integer.parseInt(br.readLine());
			
			// 명령을 수행하기 위해 Deque를 선언하여 값을 넣어준다.
			ArrayDeque<Integer> nums = new ArrayDeque<>();
			String num = br.readLine();
			
			/*
			 *  String을 입력받고 괄호를 제외하고 더해주는데,
			 *  숫자와 반점만 존재하므로 숫자를 만나면 다음 숫자까지 계속해서 더해주고,
			 *  반점을 만나면 종료하고 더해준 string값을 Integer로 변환해서 Deque에 넣어준다.
			 */
			for(int i=1; i<num.length()-1; i++) {
				String a = "";
				while(num.charAt(i) >= '0' && i < num.length()-1) {
					a += num.charAt(i++);
				}
				if(a != "") nums.offer(Integer.parseInt(a));
			}
			
			/*
			 *  R이 나오는 것에 따라 Deque에서 값을 어떻게 뽑는지 결정되기 때문에
			 *  reverse 되었는지 아닌지 체크하기 위해 rCnt를 선언했다.
			 *  그리고 error가 발생할 경우도 체크하기 위해 boolean을 선언했다.
			 */
			int rCnt = 0;
			boolean error = true;
			loop:
			for(int i=0; i<input.length(); i++) {
				switch(input.charAt(i)) {
				
				// R이 주어지면 rCnt에 1을 더해주고 2로 나눈 나머지로 바꾼다.
				// R이 홀수번 주어지면 1, 0이거나 짝수번 주어지면 0
				case 'R':
					rCnt = (rCnt+1)%2;
					break;
					
				// D가 입력되면 nums가 비어있을 경우에는 error를 출력한다.
				case 'D':
					if(nums.isEmpty()) {
						sb.append("error\n");
						error = false;
						break loop;
					}
				// 비어있지 않을 경우에 reverse 상태이면 맨 뒤의 값을 빼주고,
				// reverse 상태가 아니면 맨 앞의 값을 뺴준다.
					if(rCnt == 1) nums.pollLast();
					else nums.pollFirst();
					break;
				}
			}
			
			/*
			 *  마지막 출력 시에도 앞 뒤에 괄호를 입력해주고
			 *  rCnt 여부에 따라 순서대로 또는 역순으로 입력해준다.
			 */
			if(error) {
				int size = nums.size();
				sb.append("[");
				for(int i=0; i<size; i++) {
					if(rCnt == 1) {
						if(i<size-1) sb.append(nums.pollLast() + ",");
						else sb.append(nums.pollLast());
					} else {
						if(i<size-1) sb.append(nums.pollFirst() + ",");
						else sb.append(nums.pollFirst());
					}
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
	}
}
