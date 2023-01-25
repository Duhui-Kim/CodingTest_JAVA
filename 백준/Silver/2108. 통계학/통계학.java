import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		int avg = 0;
		int median = 10000;
		int mode = 10000;
		
		int[] array = new int[8001];
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			avg += num;
			array[num + 4000]++;
			if(num > max) {
				max = num;
			}
			if(num < min) {
				min = num;
			}
		}
		
		int mode_max = 0;
		int count = 0;
		
		boolean flag = false;
		
		for(int i=min + 4000; i<=max + 4000; i++) {
			if(array[i] > 0) {
				if(count < (N+1) / 2) {
					count += array[i];
					median = i - 4000;
				}
			
				if(mode_max < array[i]) {
					mode_max = array[i];
					mode = i - 4000;
					flag = true;
				} 
				
				else if (mode_max == array[i] && flag == true) {
					mode = i - 4000;
					flag = false;
				}
			}
		}
		System.out.println((int)Math.round((double)avg/N));
		System.out.println(median);
		System.out.println(mode);
		System.out.println(max-min);
	}
}