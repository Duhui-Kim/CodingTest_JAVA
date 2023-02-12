import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		hanoi(N, 1, 2, 3);
		System.out.println(cnt);
		System.out.println(sb);
	}
	private static void hanoi(int n, int a, int b, int c) {
		if(n==1) {
			cnt++;
			sb.append(a + " " + c + "\n");
			return;
		}
		cnt++;
		
		// 하노이 탑의 경우, N번째 값은 N-1까지의 값을 b번으로 옮기고 N을 c으로 옮긴 뒤, 다시 N-1까지의 값을 c으로 옮기는 과정임
		// 1의 경우 a -> c
		// 2의 경우 1의 경우에서 시작점이 a, 도착점이 b인 케이스 + 2를 c으로 옮김 + 1의 경우에서 시작점이 b, 도착점이 c인 케이스
		// 3의 경우 2의 경우에서 시작점이 a, 도착점이 b인 케이스 + 3을 c으로 옮김 + 2의 경우에서 시작점이 b, 도착점이 a인 케이스
		// ...
		
		hanoi(n-1, a, c, b);
		sb.append(a + " " + c + "\n");
		hanoi(n-1, b, a, c);
	}
}