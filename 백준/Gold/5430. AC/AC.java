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
			String input = br.readLine();
			
			int n = Integer.parseInt(br.readLine());
			ArrayDeque<Integer> nums = new ArrayDeque<>();
			String num = br.readLine();
						
			for(int i=1; i<num.length()-1; i++) {
				String a = "";
				while(num.charAt(i) >= '0' && i < num.length()-1) {
					a += num.charAt(i++);
				}
				if(a != "") nums.offer(Integer.parseInt(a));
			}
			
			boolean flag = true;
			boolean error = true;
			loop:
			for(int i=0; i<input.length(); i++) {
				switch(input.charAt(i)) {
				case 'R':
					flag = !flag;
					break;
				case 'D':
					if(nums.isEmpty()) {
						sb.append("error\n");
						error = false;
						break loop;
					}
					if(!flag) nums.pollLast();
					else nums.pollFirst();
					break;
				}
			}
			
			if(error) {
				int size = nums.size();
				sb.append("[");
				for(int i=0; i<size; i++) {
					if(!flag) {
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
