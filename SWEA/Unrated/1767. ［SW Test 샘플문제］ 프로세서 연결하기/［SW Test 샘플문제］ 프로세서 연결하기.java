import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static int answer;
	public static int maxCore;
	public static int N;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int testCase = Integer.parseInt(br.readLine().trim());
    	
    	for(int tc=1; tc<=testCase; tc++) {
    		N = Integer.parseInt(br.readLine().trim());
    		
    		int[][] map = new int[N][N];
    		ArrayList<Pair> list = new ArrayList<>();
    		
    		for(int i=0; i<N; i++) {
    			StringTokenizer st = new StringTokenizer(br.readLine().trim());
    			for(int j=0; j<N; j++) {
    				map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 0 || i == 0 || i == N-1 || j == 0 || j == N-1) continue;

					list.add(new Pair(i, j));						
    			}
    		}
    		maxCore = 0;
    		answer = Integer.MAX_VALUE;
    		backTracking(map, list, 0, 0, 0, list.size());
    		
    		sb.append(String.format("#%d %d\n", tc, answer));
    	}
    	System.out.println(sb);
    }
    
	private static void backTracking(int[][] map, ArrayList<Pair> list, int depth, int line, int core, int size) {
		// 남은 코어 + 고른 코어가 maxCore보다 작은 경우 필요없음
		if(size - depth + core < maxCore) return;

		// 모든 코어를 골랐을 때, 최소의 길이 고르기
		if(depth == size) {
			if(maxCore < core) {
				maxCore = core;
				answer = line;
			} else if (maxCore == core) {
				answer = answer > line ? line : answer;
			}
			return;
		}
		
		
		Pair p = list.get(depth);
		
		// 연결하고 넘어가기
		for(int i=0; i<4; i++) {
			if(!check(map, p.x, p.y, i)) continue;
			
			int length = draw(map, p.x, p.y, i, 1);
			backTracking(map, list, depth+1, line + length, core+1, size);
			draw(map, p.x, p.y, i, -1);
		}

		// 연결 안하고 넘어가기
		backTracking(map, list, depth+1, line, core, size);
	}

	private static int draw(int[][] map, int x, int y, int dir, int num) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		int cnt = 0;
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			map[nx][ny] += num;
			cnt++;
			nx += dx[dir];
			ny += dy[dir];
		}
		
		return cnt;
	}

	private static boolean check(int[][] map, int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if(map[nx][ny] == 0) {
				nx += dx[dir];
				ny += dy[dir];
			} else {
				return false;
			}
		}
		return true;
	}
}