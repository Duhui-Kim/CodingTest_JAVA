import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		
		int N = Integer.parseInt(br.readLine());
		
		// 입력받은 수 중 최대값을 저장할 인수와
		// 입력받은 수 리스트를 저장하기 위한 배열 생성
		int max = 0;
		int[] numlist = new int[N];
		
		for (int i=0; i<N; i++) {
			numlist[i] = Integer.parseInt(br.readLine());
			if(numlist[i]>max) max = numlist[i];
		}
		
		// 소수를 판별할 boolean 배열 생성
		// 입력받은 숫자 중 가장 큰 수를 배열의 크기로 선언
		// true == 합성수
		// false == 소수
		boolean[] Prime = new boolean[max+1];
		Prime[0] = Prime[1] = true;
		
		// 2부터 max의 제곱근까지 소수 본인을 제외한 배수들을 true로 바꿈
		// if문을 추가하여 Prime[i]가 소수가 아니면 계산 안함
		for(int i=0; i<=Math.sqrt(max); i++) {
			if(Prime[i] == false) {
				for(int j=i*2; j<=max; j += i) {
					Prime[j] = true;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			// 입력받은 수는 짝수이고 두 수 사이의 차이가 최소여야 하므로
			// 중간부터 시작하여 a는 아래로, b는 위로 올라가며 체크
			int a = numlist[i]/2;
			int b = numlist[i]/2;
			
			while(true) {
				// a는 아래로, b는 위로 올라가며 소수인 수를 찾음
				while(Prime[a] == true) a--;
				while(Prime[b] == true) b++;
				
				// a와 b가 모두 소수이고 합계가 입력받은 수와 같으면
				// 해당 숫자를 기록하고 while문 종료
				if(a+b == numlist[i]) {
					bw.write(a + " " + b + "\n");
					break;
				// a+b값이 입력받은 수보다 크면 
				// a가 다음 소수를 찾고,
				// 작으면 b가 다음 소수를 찾음
				} else if(a+b > numlist[i]) {
					a--;
					while(Prime[a] == true) a--;
				} else if(a+b < numlist[i]) {
					b++;
					while(Prime[b] == true) b++;
				}
			}
		}
		bw.flush();
		bw.close();
		
	}
}