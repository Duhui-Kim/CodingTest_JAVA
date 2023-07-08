import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public static class Trie {
		char c;
		String str = "";
		boolean isEnded = false;
		int cnt = 0;
		Map<Character, Trie> child = new HashMap<>();
		
		public Trie(char c) {
			this.c = c;
		}
	}
	
	public static int K;
	public static String answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=testCase; tc++) {
			K = Integer.parseInt(br.readLine());
			answer = "none";
			char[] input = br.readLine().toCharArray();
			
			Trie Head = new Trie('\u0000');
			
			for(int i=0; i<input.length; i++) {
				Trie indexTrie = Head;
				
				for(int j=i; j<input.length; j++) {
					if(!indexTrie.child.containsKey(input[j])) {
						Trie next = new Trie(input[j]);
						next.str = indexTrie.str + next.c;
						indexTrie.child.put(input[j], next);
					}
					indexTrie = indexTrie.child.get(input[j]);
					indexTrie.isEnded = true;
				}
				indexTrie.isEnded = true;
			}
			dfs(Head);
			
			sb.append(String.format("#%d %s\n", tc, answer));
		}
		System.out.println(sb);
	}

	private static void dfs(Trie next) {
		if(!answer.equals("none")) return;
		
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