import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		PriorityQueue<Long> queue = new PriorityQueue<>(Collections.reverseOrder());
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				queue.offer(Long.parseLong(st.nextToken()));
			}
		}
		
		for(int i=0; i<N-1; i++) {
			queue.poll();
		}
		bw.write(queue.poll()+"");
		bw.flush();
		bw.close();
	}
}