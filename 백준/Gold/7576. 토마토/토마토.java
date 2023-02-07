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
	private static int[][] tomatoes;
	private static Queue<int[]> queue = new ArrayDeque<>();
	private static int n;
	private static int m;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		// 토마토 밭을 만듦
		tomatoes = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				tomatoes[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 토마토 밭에서 시작점을 모두 찾아 queue에 넣음
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(tomatoes[x][y] == 1) {
					int[] array = new int[2];
					array[0] = x;
					array[1] = y;
					queue.offer(array);
				}
			}
		}
		
		// queue가 빌 때까지 bfs 실행
		while(!queue.isEmpty()) {
			int nx = queue.peek()[0];
			int ny = queue.poll()[1];
			for(int i=0; i<4; i++) {
				int cx = nx + dx[i];
				int cy = ny + dy[i];
				if(cx < 0 || cy < 0 || cx >= n || cy >= m) continue;
				if(tomatoes[cx][cy] == 0) {
					int[] array = new int[2];
					array[0] = cx;
					array[1] = cy;
					queue.offer(array);
					tomatoes[cx][cy] = tomatoes[nx][ny]+1;
				}
			}
		}
		
		// queue에 0이 있으면 불가능하므로 -1을 출력하고
		// 없으면, 모두 진행 완료되었으므로 최대값-1을 출력함
		int max = 0;
		boolean isSucceed = true;
		loop:
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(tomatoes[x][y] == 0) {
					isSucceed = false;
					break loop;
				} else if(tomatoes[x][y] > max) {
					max = tomatoes[x][y];
				}
			}
		}
		
		if(isSucceed) {
			bw.write(max-1+"");
		} else {
			bw.write(-1+"");
		}

		bw.close();		
	}
}
