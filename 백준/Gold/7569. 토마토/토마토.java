
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<int[]> queue = new ArrayDeque<>();
		
		// x, y, z를 입력받고 3차원 배열을 만든다.
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] tomato = new int[H][N][M];
		boolean[][][] check = new boolean[H][N][M];		
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<M; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 6방탐색을 해야하넹
		int[] dx = {-1, 1, 0, 0, 0, 0};
		int[] dy = {0, 0, -1, 1, 0, 0};
		int[] dz = {0, 0, 0, 0, -1, 1};
		
		// for 0~z
		// for 0~y
		// for 0~x
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					// 1을 찾으면 BFS 시작!
					if (tomato[i][j][k] == 1){
						// queue에 x, y, z를 넣고 방문 표시
						int[] arr = new int[3];
						arr[0] = i;
						arr[1] = j;
						arr[2] = k;
						queue.offer(arr);
						check[i][j][k] = true;
					}
				}
			}
		}
		
		// queue가 빌 때까지 반복
		while(!queue.isEmpty()) {
			int cz = queue.peek()[0];
			int cy = queue.peek()[1];
			int cx = queue.poll()[2];
			for(int m=0; m<6; m++) {
				int nz = cz + dz[m];
				int ny = cy + dx[m];
				int nx = cx + dy[m];
				
				// 0을 만나면 x, y, z의 값+1로 바꾸고
				if(nz>=0 && ny>=0 && nx>=0 && nz<H && ny<N && nx<M) {
					if(!check[nz][ny][nx] && tomato[nz][ny][nx] == 0) {
						int[] arr2 = new int[3];
						arr2[0] = nz;
						arr2[1] = ny;
						arr2[2] = nx;
						// queue에 넣음
						queue.offer(arr2);
						check[nz][ny][nx] = true;
						tomato[nz][ny][nx] = tomato[cz][cy][cx]+1;
					}
				}
			}
		}
		
		// 완료되면 최대값을 찾는데, 0이 있을 경우 -1 반환
		// 없을 경우 최대값 반환
		int cnt = -1;
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(tomato[i][j][k] == 0) {
						System.out.println(-1);
						return;
					} else if(tomato[i][j][k] > cnt) {
						cnt = tomato[i][j][k];
					}
				}
			}
		}
		System.out.println(cnt-1);
	}
}
