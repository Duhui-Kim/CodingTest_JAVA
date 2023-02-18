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
		
		String str = br.readLine();
		int[] abc = new int[10];
		for(char c : str.toCharArray()) {
			if(c == '9') c = '6';
			abc[c - '0']++;
		}
		abc[6] = abc[6]%2 == 0 ? abc[6]/2 : abc[6]/2+1;
		
		int max = 0;
		for(int i=0; i<10; i++) {
			max = abc[i] > max ? abc[i]: max;
		}
		bw.write(max + "");
		bw.close();
	}
}
