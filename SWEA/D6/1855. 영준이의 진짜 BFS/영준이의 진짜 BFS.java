import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {	
	private static int[][] parent;
	private static int[] depth;
	private static long move;
	private static Node[] nodes;
	
	private static class Node {
		List<Integer> children;
		
		public Node() {
			this.children = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			parent = new int[N+1][21];
			depth = new int[N+1];
			nodes = new Node[N+1];
			
			depth[1] = 1;
			
			move = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++)
				nodes[i] = new Node();
			
			for(int i=2; i<=N; i++) {
				int p = Integer.parseInt(st.nextToken());
				
				parent[i][0] = p;
				depth[i] = depth[p] + 1;
				
				nodes[p].children.add(i);
			}
			
			for(int j=1; j<21; j++) {
				for(int i=1; i<=N; i++) {
					parent[i][j] = parent[parent[i][j-1]][j-1];
				}
			}
			
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(1);
			
			int prev = 1;
			int nxt = 1;
			
			while(!queue.isEmpty()) {
				prev = nxt;
				nxt = queue.poll();
				
				for(int i : nodes[nxt].children)
					queue.offer(i);
				
				move(prev, nxt);
			}
			
			sb.append(String.format("#%d %d\n", tc, move));
		}
		System.out.println(sb);
	}

	private static void move(int prev, int nxt) {
		if(depth[prev] > depth[nxt]) {
			int tmp = prev;
			prev = nxt;
			nxt = tmp;
		}
		
		for(int i=20; i>=0; i--) {
			int jump = 1 << i;
			
			if(depth[nxt] - depth[prev] >= jump) {
				nxt = parent[nxt][i];
				move += jump;
			}
		}
		
		if(nxt == prev) return;
	
		for(int i=20; i>=0; i--) {
			if(parent[nxt][i] == parent[prev][i]) continue;
			
			nxt = parent[nxt][i];
			prev = parent[prev][i];
			move += 2 * (1 << i);
		}
		
		move += 2;
	}
}