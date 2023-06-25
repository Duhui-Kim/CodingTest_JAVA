import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer>[] queueList = new PriorityQueue[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			queueList[i] = new PriorityQueue<>();
			for(int j=0; j<M; j++) {
				queueList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		PriorityQueue<int[]> stats = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);;
		int max = 0;
		
		for(int i=0; i<N; i++) {
			max = max < queueList[i].peek() ? queueList[i].peek() : max;
			stats.add(new int[] {queueList[i].poll(), i});
		}
		
		int answer = max - stats.peek()[0];
		
		while(true) {
			int idx = stats.poll()[1];
			
			if(queueList[idx].isEmpty()) break;
			
			int num = queueList[idx].poll();
			
			max = num > max ? num : max;
			stats.add(new int[] {num, idx});
			
			if(answer > max - stats.peek()[0]) {
				answer = max - stats.peek()[0];
			}
		}
		System.out.println(answer);		
	}
}