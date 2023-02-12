import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n, m, v;
	private static int[][] bfsNodes;
	private static int[][] dfsNodes;
	private static boolean[] visited;
	private static Queue<Integer> queue = new ArrayDeque<>();
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 값을 입력받는다.
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		
		// DFS에서 방문한 숫자를 체크할 n+1 크기의 boolean 배열과
		// 정점의 개수 n+1 * n+1 크기의 2차 배열을 만든다.
		visited = new boolean[n+1];
		bfsNodes = new int[n+1][n+1];
		dfsNodes = new int[n+1][n+1];
		
		// 간선을 입력한다. (1->2) 이면 (2->1)도 가능
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			bfsNodes[x][y] = 1;
			bfsNodes[y][x] = 1;
			dfsNodes[x][y] = 1;
			dfsNodes[y][x] = 1;
		}
		DFS(v);
		bw.write("\n");
		
		queue.offer(v);
		
		// BFS 진행
		visited = new boolean[n+1];
		while(!queue.isEmpty()) {
			int x = queue.poll();
			if(visited[x] == false) {
				bw.write(x+" ");
				visited[x] = true;
				for(int i=1; i<=n; i++) {
					if(bfsNodes[x][i] == 1) {
						bfsNodes[x][i] = 0;
						bfsNodes[i][x] = 0;
						queue.offer(i);
					}
				}
			}
		}
		bw.close();
	}
	private static void DFS(int a) throws IOException {
		// 입력된 숫자가 처음 들어온 경우에만 진행
		if(visited[a] == false) {
			// 해당 숫자를 입력하고 true로 바꿈
			bw.write(a+" ");
			visited[a] = true;
			
			// 해당 숫자에 연결된 노드를 확인하여 DFS 진행
			for(int i=1; i<=n; i++) {
				if(dfsNodes[a][i] == 1) {
					dfsNodes[a][i] = 0;
					dfsNodes[i][a] = 0;
					DFS(i);
				}
			}
		}
	}
}
