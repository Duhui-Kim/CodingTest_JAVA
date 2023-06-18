import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Map<Character, Integer>[] index = new HashMap[N * 10 + 1];
		int[] check = new int[N*10 + 1];
		
		for(int i=0; i<=N*10; i++) {
			index[i] = new HashMap<>();
		}
		
		int ROOT = 0;
		int idx = 1;
				
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			boolean prefix = false;
			char[] input = br.readLine().toCharArray();
			
			int length = input.length;
			int cur = ROOT;
			for(int j=0; j<length; j++) {
				// 아직 접두사가 없으면 단어 더해주기
				if(!prefix) sb.append(input[j]);

				// 아무도 없는 단어이면 접두사 종료
				if(!prefix && index[cur].get(input[j]) == null) {
					sb.append("\n");
					prefix = true;
					index[cur].put(input[j], idx);
					cur = idx++;
				} else if (index[cur].get(input[j]) == null) {
					index[cur].put(input[j], idx);
					cur = idx++;
				} else {
					cur = index[cur].get(input[j]);
				}	
			}
			// 접두사가 입력되었다면
			if(prefix) {
				check[cur]++;
			}
			// 입력되지 않았다면 입력해주기
			else {
				if(check[cur] == 0) {
					check[cur]++;
					sb.append("\n");
				} else {
					sb.append(++check[cur]).append("\n");					
				}
			}
		}
		System.out.println(sb);
	}
}