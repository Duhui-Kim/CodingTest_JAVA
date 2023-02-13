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
		Queue<int[]> queue = new ArrayDeque<>();
		
		// 나이트가 이동할 수 있는 8개의 델타좌표
		int[] dx = {-1, -1, 1, 1, 2, -2, 2, -2};
		int[] dy = {2, -2, 2, -2, -1, -1, 1, 1};
		
		int testCase = Integer.parseInt(br.readLine());
		
		// testCase 수만큼 진행
		for(int i=0; i<testCase; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] start = new int[2];
			int[] end = new int[2];
			
			// start, end에 값을 넣고 시작한다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			// 시작점이 끝점과 같으면 0을 반환하고 그렇지 않을 경우 BFS를 시작한다.
			if(start[0] == end[0] && start[1] == end[1]) {
				bw.write("0\n");
				continue;
			}
			
			int[][] chess = new int[n][n];
			boolean[][] check = new boolean[n][n];
			queue.offer(start);
			loop:
			while(!queue.isEmpty()) {
				int x = queue.peek()[0];
				int y = queue.poll()[1];
				check[x][y] = true;
				for(int j=0; j<8; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if(nx>=0 && ny>=0 && nx<n && ny<n) {
						if(!check[nx][ny]) {
							chess[nx][ny] = chess[x][y] + 1;
							int[] array = new int[2];
							array[0] = nx;
							array[1] = ny;
							check[nx][ny] = true;
							queue.offer(array);
						}
					}
				}
			}
			bw.write(chess[end[0]][end[1]] + "\n");
		}
		bw.close();
	}
}
