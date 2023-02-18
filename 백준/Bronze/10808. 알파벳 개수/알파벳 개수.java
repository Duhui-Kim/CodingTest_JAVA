import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		int[] abc = new int[26];
		for(char c : str.toCharArray()) {
			abc[c-'a']++;
		}
		for(int i : abc) {
			bw.write(i + " ");
		}
		bw.close();
	}
}
