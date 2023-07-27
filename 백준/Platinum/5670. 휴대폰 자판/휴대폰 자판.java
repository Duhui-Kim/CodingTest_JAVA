import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static class Trie {
		int cnt;
		Map<Character, Trie> children = new HashMap<>();
		
		public Trie() {}
	}
	public static double answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String next = null;
		
		while((next = br.readLine()) != null) {
			double N = Double.parseDouble(next);	
			
			answer = 0;
			
			Trie ROOT = new Trie();
			
			for(int i=0; i<N; i++) {
				Trie cur = ROOT;
				char[] input = br.readLine().toCharArray();
				
				for(int j=0; j<input.length; j++) {
					if(!cur.children.containsKey(input[j])) {
						Trie trie = new Trie();
						cur.children.put(input[j], trie);
					}
					
					cur = cur.children.get(input[j]);
					cur.cnt++;
				}
			}
			check(ROOT, -1);
			
			System.out.println(String.format("%.2f", answer/N));
		}		
	}

	private static void check(Trie cur, int cnt) {
		if (cur.cnt == 1) {
			answer++;
			return;
		} 
		if (cur.cnt != cnt)
			answer += cur.cnt;
		
		for(Map.Entry<Character, Trie> entry : cur.children.entrySet()) {
			check(entry.getValue(), cur.cnt);
		}
	}
}