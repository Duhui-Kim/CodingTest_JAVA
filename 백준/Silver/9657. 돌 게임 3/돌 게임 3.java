import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static String[] names;
	private static String[] tmp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[] arr = new boolean[1001];
		
		arr[1] = true;
		arr[3] = true;
		arr[4] = true;
		
		for(int i=5; i<=N; i++) {
			if(!arr[i-1] || !arr[i-3] || !arr[i-4]) {
				arr[i] = true;
			}
		}
		
		if(arr[N]) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}
}