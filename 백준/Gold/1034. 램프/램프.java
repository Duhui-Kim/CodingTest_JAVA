import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			
			if(map.containsKey(input)) {
				map.put(input, map.get(input) + 1);
			} else {
				map.put(input, 1);
			}
		}
		int k = Integer.parseInt(br.readLine());
		
		int answer = 0;
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			if(answer >= entry.getValue()) continue;
			
			char[] cur = entry.getKey().toCharArray();
			
			int zero = 0;
			for(int i=0; i<M; i++) {
				if(cur[i] == '0') zero++;				
			}
			
			if(zero > k) continue;
			if(zero%2 != k%2) continue;
			
			answer = entry.getValue();
		}
		System.out.println(answer);
	}
}