import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Collections;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Integer[] N_list = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			N_list[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(N_list, Collections.reverseOrder());
		System.out.println(N_list[k-1]);
	}	
}