import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Main {    
	public static Set<Integer> set;
	
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        
        int[][][] map = new int[4][H][W];
        
        for(int i=0; i<H; i++) {
        	for(int k=0; k<4; k++) {
        		Arrays.fill(map[k][i], Integer.MAX_VALUE);
        	}        	
        }
        
        int[][] point = new int[2][2];
        
        int idx = 0;
        for(int i=0; i<H; i++) {
        	char[] arr = br.readLine().toCharArray();
        	for(int j=0; j<W; j++) {
        		if(arr[j] == 'C') {
        			point[idx][0] = i;
        			point[idx][1] = j;
        			idx++;
        		} else if (arr[j] == '*') {
        			map[0][i][j] = -1;
        			map[1][i][j] = -1;
        			map[2][i][j] = -1;
        			map[3][i][j] = -1;
        		}
        	}
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<4; i++) {
        	queue.add(new int[] {point[0][0], point[0][1], i});        	
        	map[i][point[0][0]][point[0][1]] = 0;
        }
        
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while(!queue.isEmpty()) {
        	int[] nxt = queue.poll();
        	
        	for(int i=0; i<4; i++) {
        		int nx = nxt[0] + dx[i];
        		int ny = nxt[1] + dy[i];
        		
        		if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
        		if(map[nxt[2]][nx][ny] < 0) continue;
        		
        		if (nxt[2] == i) {
        			if(map[i][nx][ny] <= map[nxt[2]][nxt[0]][nxt[1]]) continue;
        			map[i][nx][ny] = map[nxt[2]][nxt[0]][nxt[1]];
        		} else {
        			if(map[i][nx][ny] <= map[nxt[2]][nxt[0]][nxt[1]] + 1) continue;
        			map[i][nx][ny] = map[nxt[2]][nxt[0]][nxt[1]] + 1;
        		}
        		queue.add(new int[] {nx, ny, i});      
        	}
        }        
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<4; i++) {
        	min = Math.min(map[i][point[1][0]][point[1][1]], min);
        }
        
        System.out.println(min);
    }
}