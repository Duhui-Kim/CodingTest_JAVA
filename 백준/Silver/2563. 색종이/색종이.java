
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
		
		int N = Integer.parseInt(br.readLine());
		int[] x = new int[N];
		int[] y = new int[N];
		int[][] drawingPaper = new int[100][100];
		
		int count = 0;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			count += area(drawingPaper, x[i], y[i]);
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}
	
	public static int area(int[][] array, int x, int y) {
		int count = 0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(array[x+i][y+j] == 0) {
					array[x+i][y+j] = 1;
					count++;
				}
			}
		}
		return count;
	}
}