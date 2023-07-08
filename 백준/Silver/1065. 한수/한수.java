import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N < 100) {
			System.out.println(N);
			return;
		} else {
			int cnt = 99;
			
			for(int i=N; i>99; i--) {
				int a = (i/10) % 10;
				int b = i % 10;
				int gap = a - b;
				int num = i;
				
				boolean check = false;
				
				while(num >= 10) {
					int c = (num/10) % 10;
					int d = num % 10;
					num /= 10;
					
					if(gap != c - d) {
						check = true;
						break;
					}
				}
				if(!check) cnt++;
			}
			System.out.println(cnt);
		}
	}
}