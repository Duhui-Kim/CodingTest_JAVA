import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        // site + pw의 개수 N과
        // pw를 원하는 개수 M을 입력받음
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
        
        // site, pw를 저장할 map 선언
		Map<String, String> sitePassword = new HashMap<>(N);
		
        // map에 site와 pw를 저장
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			sitePassword.put(st.nextToken(), st.nextToken());
		}
		
        // pw를 원하는 site를 입력받아 key값에 넣어 value값을 출력
		for(int i=0; i<M; i++) {
			bw.write(sitePassword.get(br.readLine()) + "\n");
		}
		bw.close();
	}
}
