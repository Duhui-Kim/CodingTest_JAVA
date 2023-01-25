import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[] N_list = new int[5];
		int[] n_list = new int[5];
		for(int i=0; i<5; i++) {
			N_list[i]=Integer.parseInt(br.readLine());
		}
		
		int avg = 0;
		for(int i=0; i<5; i++) {
			int cnt=0; int twin = 0;
			for(int j=0; j<5; j++) {
				if(N_list[i]>N_list[j]) {
					cnt++;					
				} else if (N_list[i]==N_list[j]) {
					twin++;
				}
			}
			for(int j=0; j<twin; j++) {
				n_list[cnt+j] = N_list[i];
			}
			avg += N_list[i];
		}
		bw.write(avg/5 + "\n" + n_list[2]);
		bw.flush();
		bw.close();
	}
}
