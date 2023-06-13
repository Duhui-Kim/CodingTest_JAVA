import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N+1];
		int[] r_map = new int[N+1];
		Arrays.fill(map, Integer.MAX_VALUE);
		Arrays.fill(r_map, Integer.MAX_VALUE);
		
		ArrayList<int[]>[] roads = new ArrayList[N+1];
		ArrayList<int[]>[] reverse = new ArrayList[N+1];
		
		for(int i=1; i<=N; i++) {
			roads[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			roads[a].add(new int[] {b, c});
			reverse[b].add(new int[] {a, c});
		}
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		queue.offer(X);
		map[X] = 0;
		
		// X에서 각 지점까지의 거리 구하기
		while(!queue.isEmpty()) {
			int nxt = queue.poll();
			
			for(int[] road : roads[nxt]) {
				if(map[road[0]] < map[nxt] + road[1]) continue;
				map[road[0]] = map[nxt] + road[1];
				
				queue.add(road[0]);
			}
		}
		
		queue.offer(X);
		r_map[X] = 0;
		
		// 모든 점에서 X까지의 거리 구하기 (간선의 방향을 반대로)
		while(!queue.isEmpty()) {
			int nxt = queue.poll();
			
			for(int[] road : reverse[nxt]) {
				if(r_map[road[0]] < r_map[nxt] + road[1]) continue;
				r_map[road[0]] = r_map[nxt] + road[1];
				
				queue.add(road[0]);
			}
		}
		int maxLength = 0;
		
		for(int i=1; i<=N; i++) {
			maxLength = maxLength < map[i] + r_map[i] ? map[i] + r_map[i] : maxLength;
		}
		
		System.out.println(maxLength);
	}
}