import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char[] input = sc.next().toCharArray();
		
		boolean OneCheck = false;
		boolean ZeroCheck = false;
		
		int OneCnt = 0;
		int ZeroCnt = 0;
		
		for(int i=0; i<input.length; i++) {
			if(!OneCheck && input[i] != '1') {
				OneCnt++;
				OneCheck = true;
			} else if (input[i] == '1') {
				OneCheck = false;
			}
			
			if(!ZeroCheck && input[i] != '0') {
				ZeroCnt++;
				ZeroCheck = true;
			} else if (input[i] == '0') {
				ZeroCheck = false;
			}
		}
		
		System.out.println(Math.min(OneCnt, ZeroCnt));
	}
}