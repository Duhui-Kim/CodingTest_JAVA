import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[][] intList = new int[9][9];
		int max = 0;
		int maxI = 0, maxJ = 0;
		
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				intList[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if (max < intList[i][j]) {
					max = intList[i][j];
					maxI = i; maxJ = j;
				}
			}
		}
		System.out.println(max);
		System.out.printf("%d %d",maxI+1,maxJ+1);
	}	
}