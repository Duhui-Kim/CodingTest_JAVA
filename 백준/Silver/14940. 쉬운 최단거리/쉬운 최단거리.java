import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Pair {
		int x, y, moved;
		
		public Pair(int x, int y, int moved) {
			this.x = x;
			this.y = y;
			this.moved = moved;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		boolean[][] check = new boolean[N][M];
		
		Queue<Pair> queue = new LinkedList<>();
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					queue.add(new Pair(i, j, 0));
					map[i][j] = 0;
					check[i][j] = true;
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Pair p = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(check[nx][ny]) continue;
				if(map[nx][ny] == 0) continue;
				
				check[nx][ny] = true;
				map[nx][ny] = p.moved + 1;
				queue.offer(new Pair(nx, ny, p.moved+1));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0 || check[i][j]) 
					sb.append(map[i][j]).append(" ");
				else sb.append(-1).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}