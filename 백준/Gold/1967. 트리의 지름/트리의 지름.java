import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int value;
		int max;
		
		List<Integer> children = new ArrayList<>();
		
		public Node() {}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Node[] nodes = new Node[N+1];
		
		for(int i=0; i<=N; i++) {
			nodes[i] = new Node();
		}
				
		for(int i=0; i<N-1; i++) {		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			nodes[a].children.add(b);
			nodes[b].value = c;			
		}
		
		findLength(nodes, 1);
		
		Arrays.sort(nodes, (o1, o2) -> {
			return o2.max - o1.max;
		});
		
		
		System.out.println(nodes[0].max);		
	}
	private static int findLength(Node[] nodes, int idx) {
		int max1 = 0;
		int max2 = 0;
		
		for(int i : nodes[idx].children) {
			int nxt = findLength(nodes, i);
			
			if(nxt > max1) {
				max2 = max1;
				max1 = nxt;
				continue;
			}
			if(nxt > max2) {
				max2 = nxt;
			}
		}
		
		nodes[idx].max = max1 + max2;
		
		return max1 > max2 ? nodes[idx].value + max1 : nodes[idx].value + max2;
	}
}