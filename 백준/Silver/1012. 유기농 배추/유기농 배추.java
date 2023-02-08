
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	private static int[][] field;	
	private static Queue<int[]> queue = new ArrayDeque<>();
	private static int testCase;
	private static int n;
	private static int m;
	private static int k;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		testCase = Integer.parseInt(br.readLine());
		
		for(int l=0; l<testCase; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			field = new int[n][m];
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				field[x][y] = 1;
			}
			
			int warm = 0;
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(field[i][j] == 1) {
						warm++;
						field[i][j] = 0;
						int[] array = new int[2];
						array[0] = i;
						array[1] = j;
						queue.offer(array);
						
						while(!queue.isEmpty()) {
							int nx = queue.peek()[0];
							int ny = queue.poll()[1];
							for(int k=0; k<4; k++) {
								int cx = nx + dx[k];
								int cy = ny + dy[k];
								if(cx<0 || cy<0 || cx>=n || cy>=m) continue;
								if(field[cx][cy] == 1) {
									field[cx][cy] = 0;
									int[] newArray = new int[2];
									newArray[0] = cx;
									newArray[1] = cy;
									queue.offer(newArray);
								}
							}						
						}
					}
				}
			}
			bw.write(warm + "\n");
		}
		bw.close();
	}
}
