import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			int key = Integer.parseInt(br.readLine());
			if(key == 0) {
				if(queue.isEmpty()) {
					bw.write("0\n");
				} else {
					bw.write(queue.poll()+"\n");
				}
			} else {
				queue.offer(key);
			}
			
		}
		bw.flush();
		bw.close();
		
	}
}
