import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n, m짜리 배열 만들기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		// dx, dy 4방탐색 배열 만들기
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		// queue 만들기
		Queue<int[]> queue = new ArrayDeque<>();
		
		
		// 그림의 개수 = cnt / 그림의 크기 = size
		int cnt = 0; int max_size = 0;
		// x, y가 1인 지점을 만나면 0으로 바꾸고 queue에 x, y를 넣음 -> number +1 ; count는 0으로 바꿀때마다 ++;
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				if(map[x][y] == 1) {
					cnt++;
					int size = 1;
					map[x][y] = 0;
					
					int[] array = new int[2];
					array[0] = x; 
					array[1] = y;
					queue.offer(array);
					
					// queue에서 꺼낸 cx, cy에서 cx+dx, cy+dy 중 1을 만나면 0으로 바꾸고 queue에 해당 값 넣음 (queue가 빌 때까지)
					while(!queue.isEmpty()) {
						array = queue.poll();
						int cx = array[0];
						int cy = array[1];
						for(int i=0; i<4; i++) {
							if(cx+dx[i] < 0 || cy+dy[i] < 0 || cx+dx[i] >= n || cy+dy[i] >= m) continue;
							if(map[cx+dx[i]][cy+dy[i]] == 1) {
								map[cx+dx[i]][cy+dy[i]] = 0;
								size++;
								
                                // 새로 선언 안해주면 참조형이어서 다른 값들도 바뀜
                                // 초기화 필수
								array = new int[2];
								array[0] = cx+dx[i]; 
								array[1] = cy+dy[i];
								queue.offer(array);
							}
						}
						
					}
					if(size > max_size) {
						max_size = size;
					}
				}
			}
		}
		if(cnt != 0) System.out.println(cnt + "\n" + max_size);
		else System.out.println(0 + "\n" + 0);
		
	}
}