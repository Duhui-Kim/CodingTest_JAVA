import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			String abc = br.readLine();
			char[] alist = abc.toCharArray();
			int s = 0;
			int e = abc.length()-1;
			bw.write(check(alist, s, e) + "\n");
		}
		bw.flush();
		bw.close();
	}
	private static int check(char[] list, int s, int e) {
		while(s<=e) {
			if(list[s] == list[e]) {
				s++; e--;
			} else {
				if(reCheck(list, s+1, e) || reCheck(list, s, e-1)) {
					return 1;
				} else {
					return 2;
				}
			}
		}
		return 0;
	}
	
	private static boolean reCheck(char[] list, int s, int e) {
		while(s<=e) {
			if(list[s] == list[e]) {
				s++; e--;
			} else {
				return false;
			}
		}
		return true;
	}
}