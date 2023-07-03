import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(N == 2) {
			System.out.println(0);
			return;
		}
		
		int[] parent = new int[N+1];
		Arrays.fill(parent, -1);
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
				
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			queue.offer(new int[] {a, b, c});
		}
		
		int cnt = 0;
		int cost = 0;
		while(cnt < N-2) {
			int[] cur = queue.poll();
			
			if(!diffParent(cur[0], cur[1], parent)) continue;
			
			cost += cur[2];
			cnt++;
		}
		System.out.println(cost);
	}

	private static boolean diffParent(int a, int b, int[] parent) {
		a = find(a, parent);
		b = find(b, parent);
		
		if(a == b) return false;
		
		if(parent[a] == parent[b]) parent[a]--;
		if(parent[a] < parent[b]) parent[b] = a;
		else parent[a] = b;
		
		return true;
	}

	private static int find(int a, int[] parent) {
		if(parent[a] < 0) return a;
				
		return parent[a] = find(parent[a], parent);
	}
}