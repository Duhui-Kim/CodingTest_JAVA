import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		// 입력큐와 다리큐 만듦
		Queue<Integer> input = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();
		
		// 입력큐에 넣어주기
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			input.offer(Integer.parseInt(st.nextToken()));			
		}
		
		// 큐에 다리 길이만큼 0을 넣어둠.
		int i = 0;
		while(i < w) {
			bridge.offer(0);
			i++;
		}
		
		// 다리 위의 무게를 측정하는 weight 변수 선언
		int weight = 0;
		int cnt = 0;
		
		// weight 변수 => queue에서 poll 되는 수를 빼고, queue에 offer되는 수를 더한 값이 L보다 작은지 확인
		
		// ( L >= weight + 다음 넣을 수 )이면 input의 수를 빼서 bridge에 넣어주고 cnt++
		// 그렇지 않으면 0을 넣어주고 cnt++;
		while(!input.isEmpty()) {
			weight -= bridge.poll();
			
			if(L >= weight + input.peek()) {
				weight += input.peek();
				bridge.offer(input.poll());
				
			} else {
				bridge.offer(0);
			}
			cnt++;
		}
		
		// 마지막 수를 bridge에 넣어주면 종료되고, 마지막 수가 나오는 시간은 w만큼 추가로 이동한 후이므로 cnt+w가 답 
		System.out.println(cnt+w);
	}
}
