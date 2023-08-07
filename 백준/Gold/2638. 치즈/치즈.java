import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int cheeseCnt = 0;
		int[][] cheese = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				if(st.nextToken().equals("1")) {
					cheese[i][j] = -1;
					cheeseCnt++;
				}
			}
		}
		if(cheeseCnt == 0) {
			System.out.println(0);
			return;
		}
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int timeCnt = 0;
		boolean[][] check = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		
		while(!queue.isEmpty()) {
			int[] nxt = queue.poll();
			
			for(int i=0; i<4; i++) {
				int nx = nxt[0] + dx[i];
				int ny = nxt[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(check[nx][ny]) continue;
				if(cheese[nx][ny] != 0) continue;
				
				check[nx][ny] = true;
				queue.offer(new int[] {nx, ny});
			}
		}
		
		
		while(cheeseCnt > 0) {
			timeCnt++;
			
			while(!queue.isEmpty()) {
				int[] arr = queue.poll();
				
				for(int i=0; i<4; i++) {
					int nx = arr[0] + dx[i];
					int ny = arr[1] + dy[i];
					
					if(cheese[nx][ny] != 0) continue;
					if(check[nx][ny]) continue;
					
					check[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(cheese[i][j] == -1) {
						int air = 0;
						for(int k=0; k<4; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
							if(!check[nx][ny]) continue;
							if(cheese[nx][ny] == -1 || cheese[nx][ny] == timeCnt) continue;
							
							air++;
						}
						if(air >= 2) {
							cheese[i][j] = timeCnt;
							cheeseCnt--;
							check[i][j] = true;
							queue.offer(new int[] {i, j});
						}
					}
				}
			}
		}
		System.out.println(timeCnt);
	}
}