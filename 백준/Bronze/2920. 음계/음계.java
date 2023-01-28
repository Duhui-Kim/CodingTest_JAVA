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
		
		
		// 8음계 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		String answer = "";
		
		int b = Integer.parseInt(st.nextToken());
		// 1로 시작할 경우 다음 입력받는 숫자들이 1씩 커지는지 확인함
		if(b == 1) {
			for(int j=1; j<8; j++) {
				if(b+j != Integer.parseInt(st.nextToken())) {
					answer = "mixed";
					break;
				} else {
					answer = "ascending";
				}
			}
			
		} else if (b == 8) {
			for(int j=1; j<8; j++) {
				if(b-j != Integer.parseInt(st.nextToken())) {
					answer = "mixed";
					break;
				} else {
					answer = "descending";
				}
			}
		} else {
			answer = "mixed";
		}
		
		bw.write(answer);
		bw.flush();
		bw.close();	
	}
}