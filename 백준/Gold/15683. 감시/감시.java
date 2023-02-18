import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static int n;
	private static int m;
	private static int min = Integer.MAX_VALUE;
	private static int[][] map;
	private static int[][] tmpMap;
	private static List<int[]> camera = new ArrayList<>();
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		tmpMap = new int[n][m];
		
		// map 입력받기
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				// 카메라의 위치정보와 종류를 저장
				if(num != 0 && num != 6) {
					int[] arr = new int[4];
					arr[0] = i;
					arr[1] = j;
					arr[2] = num;
					camera.add(arr);
				}
			}
		}
		backTracking(0);
		System.out.println(min);
	}

	private static void backTracking(int k) {
		// 모든 카메라에 방향을 넣었을 때,
		if(k == camera.size()) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					tmpMap[i][j] = map[i][j];
				}
			}
			
			for(int i=0; i<k; i++) {
				int x = camera.get(i)[0];
				int y = camera.get(i)[1];
				
				switch(camera.get(i)[2]) {
				case 1:
					int cx = x + dx[camera.get(i)[3]];
					int cy = y + dy[camera.get(i)[3]];
					while(cx >= 0 && cy >= 0 && cx < n && cy < m) {
						if(tmpMap[cx][cy] == 0) {
							tmpMap[cx][cy] = -1;
						}
						if(tmpMap[cx][cy] == 6) break;
						cx += dx[camera.get(i)[3]];
						cy += dy[camera.get(i)[3]];
					}
					break;
				case 2:
					for(int a=0; a<=2; a += 2) {
						cx = x + dx[camera.get(i)[3] + a];
						cy = y + dy[camera.get(i)[3] + a];
						while(cx >= 0 && cy >= 0 && cx < n && cy < m) {
							if(tmpMap[cx][cy] == 0) {
								tmpMap[cx][cy] = -1;
							}
							if(tmpMap[cx][cy] == 6) break;
							cx += dx[camera.get(i)[3] + a];
							cy += dy[camera.get(i)[3] + a];
						}
					}
					break;
				case 3:
					for(int a=0; a<2; a++) {
						cx = x + dx[(camera.get(i)[3] + a)%4];
						cy = y + dy[(camera.get(i)[3] + a)%4];
						while(cx >= 0 && cy >= 0 && cx < n && cy < m) {
							if(tmpMap[cx][cy] == 0) {
								tmpMap[cx][cy] = -1;
							}
							if(tmpMap[cx][cy] == 6) break;
							cx += dx[(camera.get(i)[3] + a)%4];
							cy += dy[(camera.get(i)[3] + a)%4];
						}
					}
					break;
				case 4:
					for(int a=0; a<3; a++) {
						cx = x + dx[(camera.get(i)[3] + a)%4];
						cy = y + dy[(camera.get(i)[3] + a)%4];
						while(cx >= 0 && cy >= 0 && cx < n && cy < m) {
							if(tmpMap[cx][cy] == 0) {
								tmpMap[cx][cy] = -1;
							}
							if(tmpMap[cx][cy] == 6) break;
							cx += dx[(camera.get(i)[3] + a)%4];
							cy += dy[(camera.get(i)[3] + a)%4];
						}
					}
					break;
				case 5:
					for(int a=0; a<4; a++) {
						cx = x + dx[camera.get(i)[3] + a];
						cy = y + dy[camera.get(i)[3] + a];
						while(cx >= 0 && cy >= 0 && cx < n && cy < m) {
							if(tmpMap[cx][cy] == 0) {
								tmpMap[cx][cy] = -1;
							}
							if(tmpMap[cx][cy] == 6) break;
							cx += dx[camera.get(i)[3] + a];
							cy += dy[camera.get(i)[3] + a];
						}
					}
					break;
				}
			}
			int cnt = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(tmpMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			if(min > cnt) min = cnt;
			return;
		}
		
		switch(camera.get(k)[2]) {
		case 1: case 3: case 4: 
			for(int i=0; i<4; i++) {
				camera.get(k)[3] = i;
				backTracking(k+1);
			}
			break;
		case 2:
			for(int i=0; i<2; i++) {
				camera.get(k)[3] = i;
				backTracking(k+1);
			}
			break;
		case 5:
			backTracking(k+1);
			break;
		}
	}
}
