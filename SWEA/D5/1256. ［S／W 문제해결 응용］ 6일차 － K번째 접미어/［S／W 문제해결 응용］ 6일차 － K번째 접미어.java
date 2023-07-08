import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static class Trie {
		String str = "";
		boolean isEnded = false;
		int cnt = 0;
		Map<Character, Trie> child = new HashMap<>();
		
		public Trie() {}
	}
	
	public static int K;
	public static String answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			K = Integer.parseInt(br.readLine());
			answer = null;
			char[] input = br.readLine().toCharArray();
			
			if(K > input.length) {
				sb.append(String.format("#%d %s\n", tc, "none"));
				continue;
			}
			
			Trie Head = new Trie();
			
			for(int i=0; i<input.length; i++) {
				Trie indexTrie = Head;
				
				for(int j=i; j<input.length; j++) {
					if(!indexTrie.child.containsKey(input[j])) {
						Trie next = new Trie();
						next.str = indexTrie.str + input[j];
						indexTrie.child.put(input[j], next);
					}
					indexTrie.cnt++;
					indexTrie = indexTrie.child.get(input[j]);
				}
				indexTrie.cnt++;
				indexTrie.isEnded = true;
			}
			dfs(Head);
			
			sb.append(String.format("#%d %s\n", tc, answer));
		}
		System.out.println(sb);
	}

	private static void dfs(Trie next) {
		if(answer != null) return;
		
		if(next.cnt < K) {
			K -= next.cnt;
			return;
		}
		
		if(next.isEnded) K--;
		if(K == 0) {
			answer = next.str;
			return;
		}
		
		for(char i='a'; i<='z'; i++) {
			if(next.child.containsKey(i)) {
				dfs(next.child.get(i));
			}
		}
	}
}