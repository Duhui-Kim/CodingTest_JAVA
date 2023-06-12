import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static class Node {
		int parent, left, right;
		
		public Node() {}
	}
	
	private static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			nodes = new Node[V+1];
			
			for(int i=1; i<=V; i++) 
				nodes[i] = new Node();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				
				if(nodes[parent].left == 0)
					nodes[parent].left = child;
				else nodes[parent].right = child;
				
				nodes[child].parent = parent;
			}
			
			int ancestor = findAncestor(n1, n2, 1);
			int treeSize = findSize(ancestor);

			sb.append(String.format("#%d %d %d\n", tc, ancestor, treeSize));
		}
		
		System.out.println(sb);
	}

	private static int findAncestor(int n1, int n2, int root) {
		if(root == 0) return 0;
		if(n1 == n2) return n1;
		
		if(root == n1) return root;
		if(root == n2) return root;

		int left = findAncestor(n1, n2, nodes[root].left);
		int right = findAncestor(n1, n2, nodes[root].right);
		
		if(left != 0 && right != 0) return root;
		if(left == 0 && right != 0) return right;
		if(left != 0 && right == 0) return left;
		
		return 0;
	}

	private static int findSize(int idx) {
		if(idx == 0) return 0;
		
		int sum = 1;

		if(nodes[idx].left != 0) sum += findSize(nodes[idx].left);
		if(nodes[idx].right != 0) sum += findSize(nodes[idx].right);
		
		return sum;
	}
}