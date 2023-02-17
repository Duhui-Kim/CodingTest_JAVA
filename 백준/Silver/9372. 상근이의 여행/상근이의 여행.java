import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static boolean[] check;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// map과 check를 입력받음
			// check는 중복지점 통과하지 않도록
			map = new int[N+1][N+1];
			check = new boolean[N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
				map[b][a] = 1;
			}
			
			// BFS로 최적의 값을 찾음
			BFS();
		}
	}
	private static void BFS() {
		// queue에 1을 넣고 1부터 시작
		Queue<Integer> queue = new LinkedList<>();
		check[1] = true;
		queue.offer(1);
		
		int cnt = 0;
		
		// 반복은 2부터 시작해서, 이미 표시된 곳은 가지 않고, 표시되지 않는 곳을 방문하며 cnt
		while(!queue.isEmpty()) {
			int x = queue.poll();
			for(int i=2; i<=N; i++) {
				if(!check[i] && map[x][i] == 1) {
					check[i] = true;
					cnt++;
					queue.offer(i);
				}
			}
		}
		System.out.println(cnt);
	}
}