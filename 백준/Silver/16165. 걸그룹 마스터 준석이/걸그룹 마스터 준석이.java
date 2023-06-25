import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, List<String>> teams = new HashMap<>();
		HashMap<String, String> members = new HashMap<>();		
		
		for(int i=0; i<N; i++) {
			String team = br.readLine();
			teams.put(team, new ArrayList<>());
			
			int K = Integer.parseInt(br.readLine());
			
			for(int j=0; j<K; j++) {
				String member = br.readLine();
				teams.get(team).add(member);
				members.put(member, team);
			}
			Collections.sort(teams.get(team));
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String quiz = br.readLine();
			int cmd = Integer.parseInt(br.readLine());
			
			if(cmd == 1) {
				sb.append(members.get(quiz)).append("\n");
			} else {
				for(String m : teams.get(quiz)) {
					sb.append(m).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}