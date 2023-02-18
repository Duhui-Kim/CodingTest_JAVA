import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 *  n과 m, min : method에서 사용하기 위해 선언해둔다.
	 *  tmpMap : 마지막에 감시영역을 체크할 때, map을 깊은복사해서 사용한다.
	 *  		계속해서 새롭게 생성되면 메모리가 많이 필요하기 때문에 static으로 선언한다.
	 *  camera : 카메라의 위치정보와 종류, 방향을 저장한다.
	 *  dx, dy : 탐색 방향이 정해졌을 때 사용한다.
	 */
 
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
		// backTracking 시작
		backTracking(0);
		System.out.println(min);
	}

	private static void backTracking(int k) {	
		
		/*
		 * 1. 모든 카메라에 방향 넣어주기
		 */
		if(k != camera.size()) {
			// list에 k번 인덱스의 카메라에 차례로 방향을 넣어준다.
			// 카메라 번호에 따라 방향을 다르게 넣어준다. (1, 3, 4의 경우엔 네 방향 / 2번 카메라는 두 방향 / 5번 카메라는 한 방향)
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
		
		
		/*
		 * 2. 카메라에 방향을 모두 넣어주었다면 map에 감시영역을 표시하고 0 개수 세기
		 */
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		// map에 감시 영역을 그린다.
		// list에서 하나씩 빼서 몇 번 카메라인지 확인한다.
		for(int i=0; i<k; i++) {
			int x = camera.get(i)[0];
			int y = camera.get(i)[1];
			
			switch(camera.get(i)[2]) {
			
			// 1번 카메라의 경우, 입력된 방향으로만 뻗어나간다
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
				
			// 2번 카메라의 경우, 입력된 방향 + 반대방향으로 뻗어나간다.
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
				
			// 3번 카메라의 경우 입력된 방향 + 입력된방향+1로 뻗어나간다.
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
				
			// 4번 카메라의 경우 입력된 방향 + 입력된방향+1 + 입력된방향+2로 뻗어나간다.
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
				
			// 5번 카메라의 경우 모든 방향으로 뻗어나간다.
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
		
		// 그려진 map에서 0의 개수를 센다.
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(tmpMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		// 기존 최솟값과 비교해서 cnt가 더 작으면 cnt를 반환한다.
		if(min > cnt) min = cnt;
		return;
	}
}
