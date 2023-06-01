import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};
    private static int answer;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[N][N];
    	int[][] A = new int[N][N];
    	ArrayDeque<Integer>[][] trees = new ArrayDeque[N][N];
    	answer = M;
    	
    	// map 초기 세팅
    	for(int i=0; i<N; i++) {
    		Arrays.fill(map[i], 5);
    	}
    	
    	// 땅의 양분 세팅
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<N; j++) {
    			A[i][j] = Integer.parseInt(st.nextToken());
    			trees[i][j] = new ArrayDeque<>();
    		}
    	}
 
    	PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
    		return o1[2] - o2[2];
    	});
    	
    	// 나무 세팅
    	for(int i=0; i<M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken()) - 1;
    		int y = Integer.parseInt(st.nextToken()) - 1;
    		int z = Integer.parseInt(st.nextToken());

    		queue.offer(new int[] {x, y, z});
    	}
    	
    	while(!queue.isEmpty()) {
    		int[] tmp = queue.poll();
    		
    		trees[tmp[0]][tmp[1]].offer(tmp[2]);
    	}
    	 
    	
    	int year = 0;
    	while(year < K) { 
    		spring(trees, map, N);
    		autumn(trees, N);
    		winter(map, A, N);
    		year++;
    	}
    	
    	System.out.println(answer);
    }

	private static void autumn(ArrayDeque<Integer>[][] trees, int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int size = trees[i][j].size();
				
				for(int k=0; k<size; k++) {
					if(trees[i][j].peek() % 5 == 0) {
						
						for(int m=0; m<8; m++) {
							int nx = i + dx[m];
							int ny = j + dy[m];
							
							if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

							trees[nx][ny].offerFirst(1);
							answer++;
						}					
						
						trees[i][j].offer(trees[i][j].poll());
					} else {
						trees[i][j].offer(trees[i][j].poll());
					}
				}
			}
		}
	}

	private static void winter(int[][] map, int[][] A, int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

	private static void spring(Queue<Integer>[][] trees, int[][] map, int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 각 나무별로 진행
				int size = trees[i][j].size();
				int cnt = 0;
				
				for(int k=0; k<size; k++) {
					if(map[i][j] >= trees[i][j].peek()) {
						map[i][j] -= trees[i][j].peek();
						trees[i][j].offer(trees[i][j].poll() + 1);
					} else {
						cnt += trees[i][j].poll() / 2;
						answer--;
					}
				}
				map[i][j] += cnt;				
			}
		}		
	}
}