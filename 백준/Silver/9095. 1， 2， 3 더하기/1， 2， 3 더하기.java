import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] list = new int[12];
		list[1] = 1; list[2] = 2; list[3] = 4;
		
		for(int i=4; i<12; i++) {
			list[i] = list[i-1] + list[i-2] + list[i-3];
		}
		for(int i=0; i<n; i++) {
			int m = Integer.parseInt(br.readLine());
			bw.write(list[m]+"\n");
		}
		
		bw.flush();
		bw.close();
	}
}
