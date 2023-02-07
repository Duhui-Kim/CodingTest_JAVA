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
	private static char[][] maze;
	private static int[][] mazeCnt;	
	private static Queue<int[]> jihoon = new ArrayDeque<>();
	private static Queue<int[]> fire = new ArrayDeque<>();
	private static Queue<Integer> tmpFire = new ArrayDeque<>();
	private static Queue<Integer> tmpjihoon = new ArrayDeque<>();
	private static int n;
	private static int m;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 미로를 만듦
		maze = new char[n][m];
		mazeCnt = new int[n][m];
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<m; j++) {
				maze[i][j] = str.charAt(j);
				if(str.charAt(j) == 'J') {
					mazeCnt[i][j] = 1;
					int[] array = new int[2];
					array[0] = i;
					array[1] = j;
					jihoon.offer(array);
				} else if(str.charAt(j) == 'F') {
					int[] array = new int[2];
					array[0] = i;
					array[1] = j;
					fire.offer(array);
				}
			}
		}
		
		// 지훈이의 queue가 empty 될 떄 탈출 못하면 impossible
		// 지훈이는 그 자리가 F가 아니고, .를 만날 떄만 queue에 값을 넣고, fire는 F나 #이 아닐 때 주변 fire >> 동시에 진행해도 될 듯?
		boolean isPossible = false;
		int min = 0;
		loop:
		while(!jihoon.isEmpty()) {
			while(!jihoon.isEmpty()) {
				int x = jihoon.peek()[0];
				int y = jihoon.poll()[1];
				for(int i=0; i<4; i++) {
					int cx = x + dx[i];
					int cy = y + dy[i];
					if((cx<0 || cy<0 || cx>=n || cy>=m) && maze[x][y] != 'F') {
						min = mazeCnt[x][y];
						isPossible = true;
						break loop;
					}
					if(maze[x][y] != 'F' && maze[cx][cy] == '.') {
						int[] array = new int[2];
						tmpjihoon.offer(cx);
						tmpjihoon.offer(cy);
						maze[cx][cy] = 'J';
						mazeCnt[cx][cy] = mazeCnt[x][y] + 1;
					}	
				}
			}
			
			while(!tmpjihoon.isEmpty()) {
				int[] array = new int[2];
				array[0] = tmpjihoon.poll();
				array[1] = tmpjihoon.poll();
				jihoon.offer(array);
			}
			
			while(!fire.isEmpty()) {
				int fx = fire.peek()[0];
				int fy = fire.poll()[1];
				for(int i=0; i<4; i++) {
					int fcx = fx + dx[i];
					int fcy = fy + dy[i];
					if(fcx<0 || fcy<0 || fcx>=n || fcy>=m) continue;
					if(maze[fcx][fcy] != 'F' && maze[fcx][fcy] != '#') {
						tmpFire.offer(fcx);
						tmpFire.offer(fcy);
						maze[fcx][fcy] = 'F';
					}
					
				}
			}
			
			while(!tmpFire.isEmpty()) {
				int[] farray = new int[2];
				farray[0] = tmpFire.poll();
				farray[1] = tmpFire.poll();
				fire.offer(farray);
			}
		}
		if(isPossible) {
			System.out.println(min);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
}
