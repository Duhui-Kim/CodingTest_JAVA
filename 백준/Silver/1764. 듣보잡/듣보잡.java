import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Hashtable<String, Integer> list = new Hashtable<>();
		List<String> answer = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			list.put(br.readLine(), 1);
		}
		String next = "";
		for(int i=0; i<M; i++) {
			next = br.readLine();
			if(list.containsKey(next)) {
				answer.add(next);
			}
		}

		Collections.sort(answer);
		bw.write(answer.size()+"\n");
		for(String s:answer) {
			bw.write(s+"\n");
		}
		bw.close();
	}
}
