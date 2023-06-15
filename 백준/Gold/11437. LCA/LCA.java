import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] nodes = new ArrayList[N+1];
		int[] depth = new int[N+1];
		
		for(int i=1; i<=N; i++) 
			nodes[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		boolean[] check = new boolean[N+1];
		
		int[][] parent = new int[N+1][17];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		check[1] = true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			for(int nxt : nodes[num]) {
				if(check[nxt]) continue;
				
				check[nxt] = true;
				queue.offer(nxt);
				parent[nxt][0] = num;
				depth[nxt] = depth[num] + 1;
			}
		}

		for(int j=1; j<=16; j++) {
			for(int i=1; i<=N; i++) {
				parent[i][j] = parent[parent[i][j-1]][j-1];
			}
		}
		
		
		int M = Integer.parseInt(br.readLine());		
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			int common = node1;
			
			if(node1 != node2) {
				common = LCA(node1, node2, depth, parent, N);
			}
			
			sb.append(common).append("\n");
		}
		System.out.println(sb);
	}

	private static int LCA(int node1, int node2, int[] depth, int[][] parent, int N) {

		if(depth[node1] < depth[node2]) {
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		
		for(int i=16; i>=0; i--) {
			if(depth[node1] - depth[node2] >= 1 << i) {
				node1 = parent[node1][i];
			}
		}
		
		if(node1 == node2) return node1;
		
		for(int i=16; i>=0; i--) {
			if(parent[node1][i] == parent[node2][i]) continue;
			
			node1 = parent[node1][i];
			node2 = parent[node2][i];
		}
		
		return parent[node1][0];
	}
}