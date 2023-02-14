
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[x][y];
			
			for(int i=0; i<x; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			Queue<int[]> Uqueue = new ArrayDeque<>();
			Queue<int[]> Fqueue = new ArrayDeque<>();
			Queue<int[]> Utmp = new ArrayDeque<>();
			Queue<int[]> Ftmp = new ArrayDeque<>();
			
			int[] dx = {1, -1, 0, 0};
			int[] dy = {0, 0, -1, 1};
	
			for(int i=0; i<x; i++) {
				for(int j=0; j<y; j++) {
					if(map[i][j] == '@') {
						int[] arr = new int[2];
						arr[0] = i;
						arr[1] = j;
						Uqueue.offer(arr);
					} else if(map[i][j] == '*') {
						int[] arr2 = new int[2];
						arr2[0] = i;
						arr2[1] = j;
						Fqueue.offer(arr2);
					}
				}
			}
			
			int cnt=0;
			boolean isEscape = false;
			loop:
			while(!Uqueue.isEmpty()) {
				cnt++;
				while(!Fqueue.isEmpty()) {
					int fx = Fqueue.peek()[0];
					int fy = Fqueue.poll()[1];
					for(int i=0; i<4; i++) {
						int fnx = fx + dx[i];
						int fny = fy + dy[i];
						if(fnx>=0 && fny>=0 && fnx<x && fny<y) {
							if(map[fnx][fny] == '.' || map[fnx][fny] == '@') {
								map[fnx][fny] = '*';
								int[] arr3 = new int[2];
								arr3[0] = fnx;
								arr3[1] = fny;
								Ftmp.offer(arr3);
							}
						}
					}
				}
				
				while(!Uqueue.isEmpty()) {
					int ux = Uqueue.peek()[0];
					int uy = Uqueue.poll()[1];
					for(int i=0; i<4; i++) {
						int unx = ux + dx[i];
						int uny = uy + dy[i];
						if(unx<0 || uny<0 || unx>=x || uny>=y) {
							isEscape = true;
							break loop;
						}
						if(unx>=0 && uny>=0 && unx<x && uny<y) {
							if(map[unx][uny] == '.') {
								map[unx][uny] = '@';
								int[] arr4 = new int[2];
								arr4[0] = unx;
								arr4[1] = uny;
								Utmp.offer(arr4);
							}
						}
					}
				}
				while(!Utmp.isEmpty()) Uqueue.offer(Utmp.poll());
				while(!Ftmp.isEmpty()) Fqueue.offer(Ftmp.poll());
				
			}
			if(isEscape) {
				sb.append(cnt + "\n");
			} else {
				sb.append("IMPOSSIBLE\n");
			}
			
		}
		System.out.println(sb);
	}
}
