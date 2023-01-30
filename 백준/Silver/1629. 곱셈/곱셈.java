import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;



public class Main {
	private static long C;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//곱할 수 A, 곱셈횟수 B, 나눌 수 C가 주어진다
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		//(A*A*A)%C 는 ((A%c)*(A%c)*(A%c))%C와 같다. 몫의 경우 C의 배수이기 때문에 항상 나누어떨어짐
		// A의 B승은 A의 B/2승의 제곱과 같다. (재귀횟수 반으로 줄어듦)
		
		// B가 1보다 작거나 같을 경우(재귀 종료)
		// A % C를 return;
		
		// B가 짝수일 경우,
		// (A, B, C) = (A, B/2, C) * (A, B/2, C)
		
		// B가 홀수일 경우,
		// (A, B, C) = A * (A, (B-1)/2, C) * (A, (B-1)/2, C)
		
		bw.write((multiCycle(A, B)%C)+"");
		bw.flush();
		bw.close();
	}
	
	
	private static long multiCycle(long A, long B) {
		if(B == 1) {
			return A;
		} else if (B==2) {
			return A*A%C;
		}
		
		long temp = multiCycle(A, B/2) % C;
		
		if(B%2 == 0) {
			return temp * temp % C;

		} else {
			return A%C * (temp * temp % C);
		}
	}
}
