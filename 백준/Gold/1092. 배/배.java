import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
				
		int[] crane = new int[N];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
			max = max < crane[i] ? crane[i] : max;
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(num)) {
				map.replace(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		
		if(map.lastKey() > max) {
			System.out.println(-1);
			return;
		}
		
		int time = 0;
		while(!map.isEmpty()) {
			time++;
			
			for(int i=0; i<N; i++) {
				Integer key = map.floorKey(crane[i]);
				if(key == null) continue;
				
				if(map.get(key) == 1) {
					map.remove(key);
				} else {
					map.replace(key, map.get(key) - 1);
				}
				
				if(map.isEmpty()) break;
			}
		}
		System.out.println(time);
	}
}