import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = (int) (str.charAt(j)-'0');
			}
		}

		boolean[][] bfs = new boolean[n][m];
		Queue<int[]> queue = new ArrayDeque<>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(map[x][y] > 0 && !bfs[x][y]) {
					int[] array = new int[2];
					array[0] = x;
					array[1] = y;
					bfs[x][y] = true;
					queue.offer(array);
					while(!queue.isEmpty()) {
						int cx = queue.peek()[0];
						int cy = queue.poll()[1];
						for(int i=0; i<4; i++) {
							int nx = cx + dx[i];
							int ny = cy + dy[i];
							if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
							if(!bfs[nx][ny] && map[nx][ny] > 0) {
								array = new int[2];
								array[0] = nx;
								array[1] = ny;
								bfs[nx][ny] = true;
								queue.offer(array);
								map[nx][ny] = map[cx][cy] + 1;
							}
						}
					}
				}
			}
		}
		System.out.println(map[n-1][m-1]);
	}
}

