import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 1. 회전하는 method
	//  => 1, 1, 2, 2, 3, 3, 3 => 마지막 숫자만 3번 이동 / 시계 반대 방향으로 회전
	// 2. 먼지 흩날리는 method
	//  => 범위 밖으로 넘어가는 경우 저장한다.
	
	// 0 좌, 1 하, 2 우, 3 상
	private static int[] dx = {0, 1, 0, -1};
	private static int[] dy = {-1, 0, 1, 0};
	
	private static int answer = 0;
	private static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];

		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = N / 2;
		int y = N / 2;

		// dir : 방향, dist : 이동거리, cnt : 카운트
		int dir = 0;
		int dist = 1;
		int cnt = 0;
		
		loop:
		while(!(x < 0 || y < 0 || x >= N || y >= N)) {
			
			for(int i=0; i<dist; i++) {
				x += dx[dir];
				y += dy[dir];
				
				if(x < 0 || y < 0 || x >= N || y >= N) break loop;
				
				tornado(map, x, y, dir);
			}
			cnt++;
			if(cnt == 2) {
				cnt = 0;
				dist++;
			}
			dir++;
			dir %= 4;
		}
		System.out.println(answer);
	}
	
	// 토네이도 method
	private static void tornado(int[][] map, int x, int y, int dir) {
		int mid = dir % 4;
		int right = (dir + 3) % 4;
		int left = (dir + 1) % 4;
		int back = (dir + 2) % 4;
		
		int num = map[x][y];

		// 우하단
		int nx = x + dx[right] + dx[back];
		int ny = y + dy[right] + dy[back];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.01;
		} else {
			map[nx][ny] += map[x][y] * 0.01;
		}
		num -= (int) (map[x][y] * 0.01)/1;
		
		
		// 좌하단
		nx = x + dx[left] + dx[back];
		ny = y + dy[left] + dy[back];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.01;
		} else {
			map[nx][ny] += map[x][y] * 0.01;
		}
		num -= (int) ((map[x][y] * 0.01)/1);
		
		// 우측
		nx = x + dx[right];
		ny = y + dy[right];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.07;
		} else {
			map[nx][ny] += map[x][y] * 0.07;
		}
		num -= (int) (map[x][y] * 0.07)/1;
		
		// 우우측
		nx += dx[right];
		ny += dy[right];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.02;
		} else {
			map[nx][ny] += map[x][y] * 0.02;
		}
		num -= (int) (map[x][y] * 0.02)/1;

		// 좌측
		nx = x + dx[left];
		ny = y + dy[left];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.07;
		} else {
			map[nx][ny] += map[x][y] * 0.07;
		}
		num -= (int) (map[x][y] * 0.07)/1;
		
		// 좌좌측
		nx += dx[left];
		ny += dy[left];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.02;
		} else {
			map[nx][ny] += map[x][y] * 0.02;
		}
		num -= (int) (map[x][y] * 0.02)/1;
				
		// 상상단
		nx = x + 2 * dx[mid];
		ny = y + 2 * dy[mid];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.05;
		} else {
			map[nx][ny] += map[x][y] * 0.05;
		}
		num -= (int) (map[x][y] * 0.05)/1;
		
		// 상우단
		nx = x + dx[mid] + dx[right];
		ny = y + dy[mid] + dy[right];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.1;
		} else {
			map[nx][ny] += map[x][y] * 0.1;
		}
		num -= (int) (map[x][y] * 0.1)/1;
		
		// 상좌단
		nx = x + dx[mid] + dx[left];
		ny = y + dy[mid] + dy[left];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += map[x][y] * 0.1;
		} else {
			map[nx][ny] += map[x][y] * 0.1;
		}
		num -= (int) (map[x][y] * 0.1)/1;
		
		// 상단
		nx = x + dx[mid];
		ny = y + dy[mid];
		
		if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
			answer += num;
		} else {
			map[nx][ny] += num;
		}
		map[x][y] = 0;
		
	}
}