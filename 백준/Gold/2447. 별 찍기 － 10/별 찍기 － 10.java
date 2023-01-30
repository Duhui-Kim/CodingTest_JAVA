import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 숫자 n을 입력받는다.
		// n은 3의 제곱수
		int n = Integer.parseInt(br.readLine());
		
		// 2. 리스트의 초기 값을 정하고 메서드를 통해 나오는 값을 새롭게 리스트로 정의함
		//	list = 메서드(list, n);
			// 예를 들어 
			// String[] list = {"*"};로 만들고 시작했을 경우
			// list = method(list, n);
			
			// n이 3일 경우 리턴되는 list의 값이 
			// {"***", "* *", "***"}로 출력되어야 함.
		String[] list = {"*"};
		list = drawing(list, n);
		
		// n이 9일 경우 9줄로 출력해야함
		for(int i=0; i<n; i++) {
			bw.write(list[i]+"\n");
		}
		bw.flush();
		bw.close();
	}
	// 3. 메서드 만들기
		// 3-1 재귀
			// n이 3보다 작으면, 리턴하고 
			// n이 27이면 list는 9일때의 값이고 n이 9일때는 n이 3일때 리턴된 값이므로
			// n이 1일 때 만들어진 값을 3으로 리턴, 3일때 만들어진 값을 9로 리턴
			// list = method(list, n/3)

		// 3-2 실행
			// n이 1일 경우 그대로 아래 함수 실행
				// list의 값과 정수 n을 받아
				// 3*3*n 크기의 String[][][] 배열 stars를 만듦
				// list는 n/3크기의 배열, blank로 n/3개의 공백이 들어있는 n/3크기의 배열
				// (list의 값들을 하나씩 꺼내서 공백으로 replace!!)
	
				// stars[1][1][??]에는 공백을 넣고
				// 나머지 stars[?][?][n/3] = list[n/3]을 대입
				// 새로운 list[n]을 만든 뒤
				// 완성된 stars[*][0~3][n]를 한줄로 묶은 값을 list[n]에 대입
				// 새로운 list 리턴
	private static String[] drawing(String[] list, int n) {
		// n이 1보다 크면, 리스트를 n이 1일 때 나온 결과값으로 함.
		if(n<3) return list;
		
		// 1칸짜리 배열 list로 시작(재귀 맨 끝) 이때 n=3
		list = drawing(list, n/3);
		
		// 3*3*n/3 크기의 String[][][] 배열 stars를 만듦
		String[][][] stars = new String[3][3][n/3];
		
		// list는 n/3크기의 배열이므로
		// n/3개의 공백이 들어있는 n크기의 배열 blank를 선언
		String[] blank = new String[n/3];
		
		// list의 값들을 하나씩 꺼내서 공백으로 replace!!
		for(int i=0; i<list.length; i++) {
			blank[i] = list[i].replace("*", " ");
		}
		
		// stars[1][1][??]에는 공백을 넣고
		// 나머지 stars[*][0~2][n/3] = list[n/3]을 대입
		for(int i=0; i<3; i++) {
			for(int m=0; m<n/3; m++) {
				for(int j=0; j<3; j++) {
					if(i == 1 && j == 1) {
						stars[i][j][m] = blank[m];
					} else {
						stars[i][j][m] = list[m];
					}
				}
			}
		}
		
		// 새로운 list[n]을 만든 뒤
		list = new String[n];
		
		// 완성된 stars[*][0~2][n]를 한줄로 묶은 값을 list[n]에 대입
		int idx = 0;
		for(int i=0; i<3; i++) {
			for(int m=0; m<n/3; m++) {
				StringBuilder sb = new StringBuilder();
				for(int j=0; j<3; j++) {
					sb.append(stars[i][j][m]);
				}
				list[idx++] = sb.toString();
			}
		}
		// 새로운 list 리턴
		return list;
	}
}
