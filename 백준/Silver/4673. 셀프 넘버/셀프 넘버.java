import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 0~10001 크기의 boolean 배열을 만든다.
		// 2. n이 1부터 10000까지 커질 때 10000이하의 값인 배열 인덱스를 true로 바꿈
		// 3. false인 배열 출력
		
		boolean[] selfNum = new boolean[10001];
		int self; int n;
		
		for(int i=1; i<10001; i++) {
			self = i;
			n = i;
			while(true) {
				self += n%10;
				n = n/10;
				if(n==0) break;
			}
            if(self <= 10000){
                selfNum[self] = true;
            }
		}
		
		for(int i=1; i<10001; i++) {
			if(selfNum[i] == false) {
				bw.write(i+"\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
