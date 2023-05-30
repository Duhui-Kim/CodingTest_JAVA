import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		// 1. 맵 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 2. 인구이동 BFS
		int moveCnt = 0;
		
		while(BFS(map, N, L, R)) {
			moveCnt++;
		}
		
		// 3. 이동횟수 반환
		System.out.println(moveCnt);
	}
	
	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};

	private static boolean BFS(int[][] map, int N, int L, int R) {
		int[][] check = new int[N][N];
		int[] people = new int[N * N];
		int idx = 0;
		boolean moved = false;
		Queue<Pair> queue = new LinkedList<>();
		
		// 국경선 열기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(check[i][j] > 0) continue;
				
				int sum = 0;
				int block = 0;
				idx++;
				
				queue.offer(new Pair(i, j));
				
				while(!queue.isEmpty()) {
					Pair pair = queue.poll();					
					
					for(int k=0; k<4; k++) {
						int nx = pair.x + dx[k];
						int ny = pair.y + dy[k];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						if(check[nx][ny] > 0) continue;
						
						if(Math.abs(map[pair.x][pair.y] - map[nx][ny]) >= L && Math.abs(map[pair.x][pair.y] - map[nx][ny]) <= R) {
							moved = true;
							
							if(check[i][j] == 0) {
								sum += map[i][j];
								block++;
								check[i][j] = idx;
							}
							
							if(check[nx][ny] == 0) {
								sum += map[nx][ny];
								block++;
								check[nx][ny] = idx;
								queue.offer(new Pair(nx, ny));
							}
						}
					}
				}
				if(block > 0) people[idx] = sum / block;
			}
		}
		
		if(!moved) return false;
		
		// 인구이동
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(check[i][j] == 0) continue;
				
				map[i][j] = people[check[i][j]];
			}
		}
		
		return true;
	}
}