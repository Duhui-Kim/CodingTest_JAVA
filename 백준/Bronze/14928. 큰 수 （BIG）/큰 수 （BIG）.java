import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		int answer = 0;
		
		for(int i=0; i<input.length(); i++) {
			answer = (answer * 10 + input.charAt(i) - '0') % 20000303;
		}
		
		System.out.println(answer);
	}
}