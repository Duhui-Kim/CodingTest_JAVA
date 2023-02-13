import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<int[]> queue = new ArrayDeque<>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		// 일단 표를 입력받음
		int n = Integer.parseInt(br.readLine());
		char[][] array = new char[n][n];
		for(int i=0; i<n; i++) {
			array[i] = br.readLine().toCharArray();
		}

		// 똑같은 크기의 방문 불리안 표를 만듦
		boolean[][] check = new boolean[n][n];
		int cnt = 0;
		
		// for문 돌려서 방문하지 않은 값을 만나면 cnt 증가하고 BFS 시작
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(check[i][j] == false) {
					cnt++;
					check[i][j] = true;
					int[] gps = new int[2];
					gps[0] = i;
					gps[1] = j;
					queue.offer(gps);
					while(!queue.isEmpty()) {
						int cx = queue.peek()[0];
						int cy = queue.poll()[1];
						for(int k=0; k<4; k++) {
							int nx = cx+dx[k];
							int ny = cy+dy[k];
							// nx, ny가 범위 안이고 방문하지 않은 곳일 시
							if(nx>=0 && ny>=0 && nx<n && ny<n && !check[nx][ny]) {
								// 시작점 값이랑 같을 때 queue에 넣고 방문표시
								if(array[nx][ny] == array[i][j]) {
									check[nx][ny] = true;
									int[] a = new int[2];
									a[0] = nx;
									a[1] = ny;
									queue.offer(a);
								}
							}
						}
					}
				}
			}
		}
		bw.write(cnt + " ");
		// 적록의 경우 G를 만나면 R로 바꾸고 시작점 값이랑 비교
		// 시작점도 G이면 R로 바꾸고 시작해야함
		// 똑같은 크기의 방문 불리안 표를 만듦
		
		check = new boolean[n][n];
		cnt = 0;
		// for문 돌려서 방문하지 않은 값을 만나면 cnt 증가하고 BFS 시작
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(check[i][j] == false) {
					// 시작점이 G이면 R로 바꿈
					if(array[i][j] == 'G') array[i][j] = 'R';
					
					cnt++;
					check[i][j] = true;
					int[] gps = new int[2];
					gps[0] = i;
					gps[1] = j;
					queue.offer(gps);
					while(!queue.isEmpty()) {
						int cx = queue.peek()[0];
						int cy = queue.poll()[1];
						for(int k=0; k<4; k++) {
							int nx = cx+dx[k];
							int ny = cy+dy[k];
							// nx, ny가 범위 안이고 방문하지 않은 곳일 시
							// 일단 array[nx][ny]가 G이면 R로 바꿈
							if(nx>=0 && ny>=0 && nx<n && ny<n && !check[nx][ny]) {
								if(array[nx][ny] == 'G') array[nx][ny] = 'R';
								// 시작점 값이랑 같을 때 queue에 넣고 방문표시
								if(array[nx][ny] == array[i][j]) {
									check[nx][ny] = true;
									int[] a = new int[2];
									a[0] = nx;
									a[1] = ny;
									queue.offer(a);
								}
							}
						}
					}
				}
			}
		}
		bw.write(cnt + "");
		bw.close();
	}
}
