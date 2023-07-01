import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class	Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static List<Pair> virus = new ArrayList<>();
	public static int minTime = Integer.MAX_VALUE;
	public static int N;
	public static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new Pair(i, j));
				if(map[i][j] != 1) cnt++;
			}
		}
		
		if(virus.size() == cnt) {
			System.out.println(0);
			return;
		}
		
		int[] arr = new int[M];
		
		backTracking(map, arr, 0, 0, M, virus.size());
		
		if(minTime == Integer.MAX_VALUE) {
			System.out.println(-1);			
		} else {
			System.out.println(minTime);
		}
	}

	private static void backTracking(int[][] map, int[] arr, int pre, int k, int M, int size) {
		if(minTime == 0) return;
		if(k == M) {
			Queue<Pair> queue = new LinkedList<>();
			boolean[][] check = new boolean[N][N];
			for(int i=0; i<M; i++) {
				Pair p = virus.get(arr[i]);
				queue.offer(p);
				check[p.x][p.y] = true;
			}
			bfs(map, queue, check);
			
			return;
		}
		
		for(int i=pre; i<size; i++) {
			arr[k] = i;
			backTracking(map, arr, i+1, k+1, M, size);
		}
	}

	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, 1, -1};
	
	private static void bfs(int[][] map, Queue<Pair> queue, boolean[][] check) {
		int[][] time = new int[N][N];
		int tmp = 0;
		int tmpCnt = queue.size();
		
		while(!queue.isEmpty()) {
			Pair p = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(map[nx][ny] == 1) continue;
				if(check[nx][ny]) continue;
				
				check[nx][ny] = true;
				time[nx][ny] = time[p.x][p.y] + 1;
				tmpCnt++;
				
				queue.offer(new Pair(nx, ny));
				
				if(map[nx][ny] != 2) {
					tmp = time[nx][ny];
				}
				
				if(tmp > minTime) return;
			}
		}
		
		if(cnt == tmpCnt) {	
			minTime = minTime > tmp ? tmp : minTime;
		}
	}
}