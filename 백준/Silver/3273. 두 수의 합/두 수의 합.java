import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		int x = Integer.parseInt(br.readLine());
		
		Collections.sort(list);
		
		int p = 0;
		int q = list.size()-1;
		
		
		int cnt=0;
		
		while(p < q) {
			if(list.get(p) + list.get(q) > x) {
				q--;
			} else if(list.get(p) + list.get(q) < x) {
				p++;
			} else {
				cnt++;
				p++; q--;
			}
		}
		System.out.println(cnt);
	}
}
