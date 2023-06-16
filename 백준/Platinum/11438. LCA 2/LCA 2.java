import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] nodes = new ArrayList[N+1];
		for(int i=1; i<=N; i++)
			nodes[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		int[][] sparse = new int[N+1][20];
		int[] depth = new int[N+1];
		
		Queue<Integer> queue = new LinkedList<>();

		queue.add(1);
		depth[1] = 1;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int nxt : nodes[cur]) {
				if(depth[nxt] != 0) continue;
				
				sparse[nxt][0] = cur;
				depth[nxt] = depth[cur] + 1;
				queue.offer(nxt);
			}
		}
		
		for(int j=1; j<20; j++) {
			for(int i=1; i<=N; i++) {
				sparse[i][j] = sparse[sparse[i][j-1]][j-1];
			}
		}
				
		int M = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
						
			sb.append(LCA(sparse, depth, n1, n2)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int LCA(int[][] sparse, int[] depth, int n1, int n2) {		
		if(depth[n1] < depth[n2]) {
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		
		// 높이 맞추기
		for(int j=19; j>=0; j--) {
			if(depth[n1] - depth[n2] >= 1 << j)
				n1 = sparse[n1][j];
		}
		
		if(n1 == n2) return n1;
		
		for(int j=19; j>=0; j--) {
			if(sparse[n1][j] == sparse[n2][j]) continue;
			
			n1 = sparse[n1][j];
			n2 = sparse[n2][j];
		}
		
		return sparse[n1][0];
	}
}