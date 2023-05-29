import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Pair {
		int num, dist;
		
		public Pair(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] edges = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {		
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges[a].add(b);
			edges[b].add(a);
		}
		
		int min = Integer.MAX_VALUE;
		int answer = 0;
		
		for(int i=1; i<=N; i++) {
			boolean[] check = new boolean[N+1];
			check[i] = true;
			int cnt = 0;
			Queue<Pair> queue = new LinkedList<>();
			queue.offer(new Pair(i, 0));
			
			while(!queue.isEmpty()) {
				Pair pair = queue.poll();
				
				for(int a : edges[pair.num]) {
					if(check[a]) continue;
					check[a] = true;
					cnt += pair.dist+1;
					queue.offer(new Pair(a, pair.dist+1));
				}
			}
			
			if(min > cnt) {
				answer = i;
				min = cnt;
			}
		}
		System.out.println(answer);
	}
}