import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	private static class Node {
		String word;
		int left, right;
		
		public Node(String word, int left, int right) {
			this.word = word;
			this.left = left;
			this.right = right;
		}
	}
	
	private static Node[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			nodes = new Node[N+1];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String word = st.nextToken();
				
				int left = 0;
				int right = 0;
				
				if(st.hasMoreTokens()) left = Integer.parseInt(st.nextToken());
				if(st.hasMoreTokens()) right = Integer.parseInt(st.nextToken());
							
				nodes[idx] = new Node(word, left, right);
			}
			
			sb.append("#" + tc + " ");
			print(sb, 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void print(StringBuilder sb, int idx) {
		if(idx == 0) return;
		
		print(sb, nodes[idx].left);
		sb.append(nodes[idx].word);
		print(sb, nodes[idx].right);
	}
}