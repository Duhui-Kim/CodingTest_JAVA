import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		
		StringBuilder sb = new StringBuilder();
		while(!(input = br.readLine()).equals(".")) {
			char[] arr = input.toCharArray();
			int[] failure = new int[arr.length];
			
			int j=0;
			for(int i=1; i<arr.length; i++) {
				while(j > 0 && arr[i] != arr[j]) j = failure[j-1];
				if(arr[i] == arr[j]) {
					failure[i] = ++j;
				}
			}
			
			int length = arr.length - failure[arr.length-1];
						
			if(arr.length % length != 0) 
				length = arr.length;
			
			sb.append(arr.length / length).append("\n");
		}
		System.out.println(sb);
	}
}