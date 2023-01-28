
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		// 테스트케이스 N을 입력받음
		int N = Integer.parseInt(br.readLine());
		
		// N번의 테스트 진행
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 반복횟수 R과 문자열 S를 입력받고
			int R = Integer.parseInt(st.nextToken());
			String S = st.nextToken();
			
			// 문자열 하나씩 꺼내서 반복횟수만큼 BufferedWriter에 입력함
			for(int j=0; j<S.length(); j++) {
				for(int r=0; r<R; r++) {
					bw.write(S.charAt(j));
				}
			}
			// 한 문장이 끝나면 줄바꿈을 입력해줌
			bw.write("\n");
		}
		bw.flush();
		bw.close();	
	}
}