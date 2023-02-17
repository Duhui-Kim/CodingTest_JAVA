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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		// 사방탐색을 위한 dx, dy를 선언한다.
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		// map을 입력받는다.
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 숫자를 세기 위한 road 배열도  만든다.
		// 벽을 부순 상태, 아닌 상태를 나눠서 저장하기 위해 3차원 배열로 만든다.
		int[][][] road = new int[2][N][M];
		
		// BFS를 진행하기 위해 Queue도 만든다.
		Queue<int[]> queue = new ArrayDeque<>();
		
		// 0, 0에서 시작하여 N-1, M-1까지 가는 것으로 생각하고 진행한다.
		// 벽은 한 번 부술 수 있기 때문에, (x, y) 좌표 뒤에 1을 부쉈는지 여부도 표시한다. 
		// 부술 수 없으면 0번에 저장 / 있으면 1번에 저장 
		int[] arr = {0, 0, 1};		
		queue.offer(arr);
		road[0][0][0] = 1;
		road[1][0][0] = 1;
		
		boolean isEscape = false;

		loop:
		while(!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.peek()[1];
			int z = queue.poll()[2];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z;
				if(nx>=0 && ny>=0 && nx<N && ny<M) {
					// 벽을 부술 수 있을 때는 road 1에서 이동
					// 벽을 만나면 road 0이 비어있는지 확인 후 비어있다면 이동
					if(nx==N-1 && ny==M-1) {
						road[0][nx][ny] = road[nz][x][y] + 1;						
						isEscape = true;
						break loop;
					}
					if(nz == 1) {
						if(map[nx][ny] == '0' && road[nz][nx][ny] == 0) {
							road[nz][nx][ny] = road[nz][x][y] + 1;
							arr = new int[3];
							arr[0] = nx;
							arr[1] = ny;
							arr[2] = nz;
							queue.offer(arr);
						} else if (map[nx][ny] == '1' && road[0][nx][ny] == 0) {
							road[0][nx][ny] = road[nz][x][y] + 1;
							arr = new int[3];
							arr[0] = nx;
							arr[1] = ny;
							arr[2] = 0;
							queue.offer(arr);
						}
					// 벽을 부술 수 없다면 road 0에서 이동
					} else {
						if(map[nx][ny] == '0' && road[nz][nx][ny] == 0) {
							road[nz][nx][ny] = road[nz][x][y] + 1;
							arr = new int[3];
							arr[0] = nx;
							arr[1] = ny;
							arr[2] = nz;
							queue.offer(arr);
						}
					}
				}
			}
		}
		if(isEscape) {
			System.out.println(road[0][N-1][M-1]);
		} else {
			System.out.println(-1);
		}
	}
}
